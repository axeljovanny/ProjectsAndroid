package mx.edu.utng.basedatos2.controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import mx.edu.utng.basedatos2.model.Publico;

/**
 * Created by qas on 22/02/16.
 */
public class FormPublicoActivity extends Activity implements View.OnClickListener{
    private EditText edtDescripcion;
    private Button btnGuardar;
    private SQLiteDatabase db;
    private Publico publico;
    private CuestionarioDAOImpl cuestionarioDAOImpl;
    private PublicoDAOImpl publicoDAOImpl;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_publico_layout);
        initComponents();
    }
    private void initComponents(){
        edtDescripcion = (EditText)findViewById(R.id.edt_descripcion);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnListar = (Button)findViewById(R.id.btn_listar);
       cuestionarioDAOImpl = new CuestionarioDAOImpl(this,
                CuestionarioDAOImpl.DATABASE_NAME,
                null,
                CuestionarioDAOImpl.DATABASE_VERSION);
        db = cuestionarioDAOImpl.getWritableDatabase();
        publicoDAOImpl = new PublicoDAOImpl();

        btnGuardar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.btn_guardar:
               publico = new Publico(
                       0, edtDescripcion.getText().toString());
               if(getIntent().getExtras()!=null){
                   publico.setIdPublico(
                           getIntent().getExtras().getInt(PublicoDAOImpl.ID));
               }

               if(publico.getIdPublico()==0){
                   publicoDAOImpl.agregar(publico, db);
               }else{
                   publicoDAOImpl.cambiar(publico, db);
               }
                break;
           case R.id.btn_listar:
                startActivity(new Intent(FormPublicoActivity.this,
                        ListaPublicoActivity.class));
                break;
           default:
               break;
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            Bundle publicoBundle = getIntent().getExtras();
            edtDescripcion.setText(
                    publicoBundle.getString(PublicoDAOImpl.DESCRIPTION));
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
            case R.id.itm_cuestionarios:
                startActivity(new Intent(this, FormCuestionarioActivity.class));
                return true;
            default:
                startActivity(new Intent(this, FormPublicoActivity.class));
                return true;
        }
    }
}
