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

import java.sql.Date;

import mx.edu.utng.database2.R;
import mx.edu.utng.database2.dao.BebidaDAOImpl;
import mx.edu.utng.database2.dao.DBHelper;
import mx.edu.utng.database2.model.Bebida;
import mx.edu.utng.database2.util.BebidaAdapter;

/**
 * Created by qas on 23/02/16.
 */
public class ListaBebidasActivity extends Activity{

    private ListView lsvBebidas;
    private DBHelper dbHelper;
    private BebidaDAOImpl dao;
    private Bebida bebida;
    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_bebida_layout);
        initComponents();

    }

    private void initComponents(){
        lsvBebidas = (ListView) findViewById(R.id.lsv_bebidas);
        dbHelper = new DBHelper(this, DBHelper.DATABASE_NAME, null,
                DBHelper.DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();
        dao = new BebidaDAOImpl();
       listar();
        registerForContextMenu(lsvBebidas);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        cursor.moveToPosition(info.position);
        boolean disponible = cursor.getString(4).equals("1")?true:false;
        bebida = new Bebida(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getFloat(2),
                Date.valueOf(cursor.getString(3)),
                disponible,
                cursor.getInt(5)
        );

        menu.setHeaderTitle(
                getResources()
                .getString(R.string.opciones)+bebida.getNombre());
        menu.add(0, 1, 1, getString(R.string.modificar));
        menu.add(0, 2, 1, getString(R.string.eliminar));


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==1){
             Bundle bebidaBundle = new Bundle();
             bebidaBundle.putInt(DBHelper.ID, bebida.getIdBebida());
             bebidaBundle.putString(DBHelper.NAME, bebida.getNombre());
             bebidaBundle.putFloat(DBHelper.PRICE, bebida.getPrecio());
             bebidaBundle.putString(DBHelper.DUE_DATE,
                     bebida.getFechaCaducidad().toString());
             bebidaBundle.putBoolean(DBHelper.DISPONIBLE,
                     bebida.isDisponible());
            bebidaBundle.putInt(DBHelper.CUSTOMER_ID, bebida.getCliente());
            startActivity(new Intent(ListaBebidasActivity.this,
                    FormBebidasActivity.class)
                    .putExtras(bebidaBundle));

        }
        if(item.getItemId()==2){
            dao.eliminar(bebida, db);
            listar();
        }
        return super.onContextItemSelected(item);
    }

    private void listar(){
        cursor = dao.listar(db);
        BebidaAdapter adapter = new BebidaAdapter(this, cursor);
        lsvBebidas.setAdapter(adapter);
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
                startActivity(new Intent(this,
                        FormClientesActivity.class));
                return true;
            default:
                startActivity(new Intent(this,
                        FormBebidasActivity.class));
                return true;
        }
    }
}
