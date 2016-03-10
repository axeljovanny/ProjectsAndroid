package mx.edu.utng.database2.controller;

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
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

import mx.edu.utng.database2.R;
import mx.edu.utng.database2.dao.BebidaDAOImpl;
import mx.edu.utng.database2.dao.ClienteDAOImpl;
import mx.edu.utng.database2.dao.DBHelper;
import mx.edu.utng.database2.model.Bebida;
import mx.edu.utng.database2.util.ClienteAdapter;

/**
 * Created by qas on 23/02/16.
 */
public class FormBebidasActivity extends Activity implements View.OnClickListener{
    private EditText edtNombre;
    private EditText edtPrecio;
    private TextView txvFechaCaducidad;
    private CheckBox chkDisponible;
    private Spinner spiCliente;
    private Button btnGuardar;
    private Button btnListar;
    private SQLiteDatabase db;
    private Bebida bebida;
    private DBHelper dbHelper;
    private BebidaDAOImpl dao;
    private ClienteDAOImpl clienteDAO;
    private Calendar calendar;
    private int anio, mes, dia;
    private Button btnFechaCaducidad;
    private Bundle bebidaBundle;
    private Intent intentListaBebidas;
    private Intent intentClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_bebida_layout);
        initComponents();
    }
    private void initComponents(){
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        edtPrecio = (EditText)findViewById(R.id.edt_precio);
        txvFechaCaducidad = (TextView)findViewById(R.id.txv_fecha_caducidad);
        chkDisponible = (CheckBox)findViewById(R.id.chk_disponible);
        spiCliente = (Spinner)findViewById(R.id.spi_cliente);
        calendar = Calendar.getInstance();
        anio = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        btnFechaCaducidad = (Button)findViewById(R.id.btn_fecha_caducidad);
        btnFechaCaducidad.setOnClickListener(this);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnGuardar.setOnClickListener(this);
        btnListar = (Button)findViewById(R.id.btn_listar);
        btnListar.setOnClickListener(this);
        intentListaBebidas = new Intent(FormBebidasActivity.this,
                ListaBebidasActivity.class);
        intentClientes = new Intent(this,
                FormClientesActivity.class);



        dbHelper = new DBHelper(this,
                DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();
        dao = new BebidaDAOImpl();

        clienteDAO = new ClienteDAOImpl();
        Cursor cursorClientes = clienteDAO.listar(db);
        ClienteAdapter adapter = new ClienteAdapter(this, cursorClientes);
        spiCliente.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fecha_caducidad:
                showDialog(999);
                break;
            case R.id.btn_guardar:
                bebida = new Bebida(0,
                        edtNombre.getText().toString(),
                        Float.parseFloat(
                                edtPrecio.getText().toString()),
                        Date.valueOf(
                         txvFechaCaducidad.getText().toString()),
                        chkDisponible.isChecked(),
                        spiCliente.getSelectedItemPosition());
                if(bebidaBundle!=null){
                    bebida.setIdBebida(
                            bebidaBundle.getInt(DBHelper.ID));
                }
                if(bebida.getIdBebida()==0){
                    dao.agregar(bebida, db);
                }else{
                    dao.modificar(bebida, db);
                }
                break;
            case R.id.btn_listar:
                startActivity(intentListaBebidas);
                break;
            default:
                break;
        }
    }



    private void mostrarFecha(int anio, int mes, int dia) {
        txvFechaCaducidad.setText(
                new StringBuilder().append(anio).append("-")
                .append(mes).append("-").append(dia));
    }


    private DatePickerDialog.OnDateSetListener listener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            mostrarFecha(year, monthOfYear + 1, dayOfMonth);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==999){
            return new DatePickerDialog(this, listener,
                    anio, mes, dia);
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            bebidaBundle = getIntent().getExtras();
            edtNombre.setText(bebidaBundle.getString(DBHelper.NAME));
            edtPrecio.setText(String.valueOf(bebidaBundle.getFloat(DBHelper.PRICE)));
            txvFechaCaducidad.setText(bebidaBundle.getString(DBHelper.DUE_DATE));
            chkDisponible.setChecked(bebidaBundle.getBoolean(DBHelper.DISPONIBLE));
            spiCliente.setSelection(bebidaBundle.getInt(DBHelper.CUSTOMER_ID));


        }catch (Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_clientes:
                startActivity(intentClientes);
                finish();
                return true;
            default:
                return true;
        }
    }

}
