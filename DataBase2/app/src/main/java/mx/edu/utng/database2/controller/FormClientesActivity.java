package mx.edu.utng.database2.controller;

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

import mx.edu.utng.database2.R;
import mx.edu.utng.database2.dao.BebidaDAOImpl;
import mx.edu.utng.database2.dao.ClienteDAOImpl;
import mx.edu.utng.database2.dao.DBHelper;
import mx.edu.utng.database2.model.Bebida;
import mx.edu.utng.database2.model.Cliente;

/**
 * Created by qas on 23/02/16.
 */
public class FormClientesActivity extends Activity implements View.OnClickListener{
    private EditText edtNombre;
    private EditText edtApellidos;
    private Button btnGuardar;
    private Button btnListar;
    private SQLiteDatabase db;
    private Cliente cliente;
    private DBHelper dbHelper;
    private ClienteDAOImpl dao;
    private Bundle clienteBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_cliente_layout);
        initComponents();
    }
    private void initComponents(){
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        edtApellidos = (EditText)findViewById(R.id.edt_apellidos);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnGuardar.setOnClickListener(this);
        btnListar = (Button)findViewById(R.id.btn_listar);
        btnListar.setOnClickListener(this);
        dbHelper = new DBHelper(this,
                DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();
        dao = new ClienteDAOImpl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar:
                cliente = new Cliente(0,
                        edtNombre.getText().toString(),
                        edtApellidos.getText().toString());
                if(clienteBundle !=null){
                    cliente.setIdCliente(
                            clienteBundle.getInt(DBHelper.ID));
                }
                if(cliente.getIdCliente()==0){
                    dao.agregar(cliente, db);
                }else{
                    dao.modificar(cliente, db);
                }
                break;
            case R.id.btn_listar:
                startActivity(new Intent(FormClientesActivity.this,
                       ListaClientesActivity.class));
                break;
            default:
                break;
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        try{
            clienteBundle = getIntent().getExtras();
            edtNombre.setText(clienteBundle.getString(DBHelper.NAME));
            edtApellidos.setText(clienteBundle.getString(DBHelper.LASTNAME));
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
            case R.id.mnu_bebidas:
                startActivity(new Intent(this,
                        FormBebidasActivity.class));
                return true;
            default:
                startActivity(new Intent(this,
                        FormClientesActivity.class));
                return true;
        }
    }
}
