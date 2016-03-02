package mx.edu.utng.basedatos2.controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

import mx.edu.utng.basedatos2.R;
import mx.edu.utng.basedatos2.dao.CuestionarioDAOImpl;
import mx.edu.utng.basedatos2.dao.PublicoDAOImpl;
import mx.edu.utng.basedatos2.model.Cuestionario;
import mx.edu.utng.basedatos2.util.PublicoCursorAdapter;

/**
 * Created by qas on 22/02/16.
 */
public class FormCuestionarioActivity extends Activity implements View.OnClickListener{
    private EditText edtNombre;
    private DatePicker dpkFechaCreacion;
    private CheckBox chkActivo;
    private Spinner spiDirigido;
    private Button btnGuardar;
    private SQLiteDatabase db;
    private Cuestionario cuestionario;
    private CuestionarioDAOImpl cuestionarioDAOImpl;
    private Button btnListar;
    private Calendar calendar;
    private int anio, mes, dia;
    private Button btnFechaCreacion;
    private TextView txvFechaCreacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_cuestionario_layout);
        initComponents();
    }
    private void initComponents(){
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
       txvFechaCreacion = (TextView)findViewById(
               R.id.txv_fecha_creacion);
        chkActivo = (CheckBox)findViewById(R.id.chk_activo);
        spiDirigido = (Spinner)findViewById(R.id.spi_dirigido_a);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnListar = (Button)findViewById(R.id.btn_listar);
        btnFechaCreacion = (Button)findViewById(R.id.btn_fecha_creacion);

        cuestionarioDAOImpl = new CuestionarioDAOImpl(this,
                CuestionarioDAOImpl.DATABASE_NAME,
                null,
                CuestionarioDAOImpl.DATABASE_VERSION);
        db = cuestionarioDAOImpl.getWritableDatabase();

        PublicoDAOImpl publicoDAO = new PublicoDAOImpl();
        Cursor cursor = publicoDAO.desplegar(db);
        PublicoCursorAdapter adapter = new PublicoCursorAdapter(
                this, cursor);
        spiDirigido.setAdapter(adapter);

        btnGuardar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnFechaCreacion.setOnClickListener(this);

        calendar = Calendar.getInstance();
        anio = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.btn_guardar:
               cuestionario = new Cuestionario(
                       0, edtNombre.getText().toString(),
                   Date.valueOf(txvFechaCreacion.getText().toString()),
                       chkActivo.isChecked(),
                   spiDirigido.getSelectedItemPosition());
               if(getIntent().getExtras()!=null){
                   cuestionario.setIdCuestionario(
                     getIntent().getExtras().getInt(CuestionarioDAOImpl.ID));
               }

               if(cuestionario.getIdCuestionario()==0){
                   cuestionarioDAOImpl.agregar(cuestionario, db);
               }else{
                   cuestionarioDAOImpl.cambiar(cuestionario, db);
               }
                break;
           case R.id.btn_listar:
                startActivity(new Intent(FormCuestionarioActivity.this,
                        ListaCuestionarioActivity.class));
                break;
           case R.id. btn_fecha_creacion:
                showDialog(1);
               break;
           default:
               break;
       }
    }

    private void mostrarFecha(int anio, int mes, int dia){
        txvFechaCreacion.setText(
                new StringBuilder()
                        .append(anio)
                        .append("-")
                        .append(mes)
                        .append("-")
                        .append(dia));
    }

    DatePickerDialog.OnDateSetListener listener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            mostrarFecha(year, monthOfYear+1,dayOfMonth);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
       if(id==1){
           return new DatePickerDialog(this, listener,
                   anio, mes, dia);
       }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            Bundle cuestionarioBundle = getIntent().getExtras();
            edtNombre.setText(
                    cuestionarioBundle.getString(CuestionarioDAOImpl.NAME));
            txvFechaCreacion.setText(
                    cuestionarioBundle.getString(CuestionarioDAOImpl.CREATION_DATE));
            chkActivo.setChecked(
                    cuestionarioBundle.getBoolean(CuestionarioDAOImpl.ACTIVE));
            spiDirigido.setSelection(
                    cuestionarioBundle.getInt(CuestionarioDAOImpl.FOCUS_TO));
        }catch (Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acciones_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_publico:
                startActivity(new Intent(this, FormPublicoActivity.class));
                return true;
              default:
                startActivity(new Intent(this, FormCuestionarioActivity.class));
                return true;
        }
    }
}
