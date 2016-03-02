package mx.edu.utng.database2.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import mx.edu.utng.database2.R;
import mx.edu.utng.database2.dao.ClienteDAOImpl;
import mx.edu.utng.database2.dao.DBHelper;
import mx.edu.utng.database2.model.Cliente;
import mx.edu.utng.database2.util.ClienteAdapter;

/**
 * Created by qas on 23/02/16.
 */
public class ListaClientesActivity extends Activity{

    private ListView lsvClientes;
    private DBHelper dbHelper;
    private ClienteDAOImpl dao;
    private Cliente cliente;
    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cliente_layout);
        initComponents();

    }

    private void initComponents(){
        lsvClientes = (ListView) findViewById(R.id.lsv_clientes);
        dbHelper = new DBHelper(this, DBHelper.DATABASE_NAME, null,
                DBHelper.DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();
        dao = new ClienteDAOImpl();
       listar();
        registerForContextMenu(lsvClientes);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        cursor.moveToPosition(info.position);
        cliente = new Cliente(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));

        menu.setHeaderTitle(
                getResources()
                        .getString(R.string.opciones) + cliente.getNombre());
        menu.add(0, 1, 1, getString(R.string.modificar));
        menu.add(0, 2, 1, getString(R.string.eliminar));


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==1){
             Bundle clienteBundle = new Bundle();
             clienteBundle.putInt(DBHelper.ID, cliente.getIdCliente());
             clienteBundle.putString(DBHelper.NAME, cliente.getNombre());
             clienteBundle.putString(DBHelper.LASTNAME, cliente.getApellidos());
            startActivity(new Intent(ListaClientesActivity.this,
                    FormClientesActivity.class)
                    .putExtras(clienteBundle));
        }
        if(item.getItemId()==2){
            dao.eliminar(cliente, db);
            listar();
        }
        return super.onContextItemSelected(item);
    }

    private void listar(){
        cursor = dao.listar(db);
        ClienteAdapter adapter = new ClienteAdapter(this, cursor);
        lsvClientes.setAdapter(adapter);
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
