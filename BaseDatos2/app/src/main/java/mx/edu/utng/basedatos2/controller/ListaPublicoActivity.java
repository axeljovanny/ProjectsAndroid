package mx.edu.utng.basedatos2.controller;

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

import mx.edu.utng.basedatos2.R;
import mx.edu.utng.basedatos2.dao.CuestionarioDAOImpl;
import mx.edu.utng.basedatos2.dao.PublicoDAOImpl;
import mx.edu.utng.basedatos2.model.Cuestionario;
import mx.edu.utng.basedatos2.model.Publico;
import mx.edu.utng.basedatos2.util.CuestionarioCursorAdapter;
import mx.edu.utng.basedatos2.util.PublicoCursorAdapter;

/**
 * Created by qas on 22/02/16.
 */
public class ListaPublicoActivity extends Activity{
   private ListView lsvPublico;
    private SQLiteDatabase db;
    private Publico publico;
    private Cursor cursor;
    private CuestionarioDAOImpl cuestionarioDAOImpl;
    private PublicoDAOImpl publicoDAOImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_publico_layout);
        initComponents();
    }

    private void initComponents(){
        lsvPublico = (ListView)findViewById(R.id.lsv_publico);
        cuestionarioDAOImpl = new CuestionarioDAOImpl(this,"utng.db", null,
                CuestionarioDAOImpl.DATABASE_VERSION);
        db = cuestionarioDAOImpl.getWritableDatabase();
        publicoDAOImpl = new PublicoDAOImpl();
        listar();
        registerForContextMenu(lsvPublico);
    }

    private void listar(){
        cursor = publicoDAOImpl.desplegar(db);
        PublicoCursorAdapter adapter = new PublicoCursorAdapter(
                this, cursor);
        lsvPublico.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        cursor.moveToPosition(info.position);

        publico = new Publico(
                cursor.getInt(0),
                cursor.getString(1));

        menu.setHeaderTitle("Opciones para " + publico.getDescripcion());
        menu.add(0, 1, 1, getString(R.string.modificar));
        menu.add(0, 2, 1, getString(R.string.eliminar));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       if(item.getItemId()==1){
           Bundle publicoBundle = new Bundle();
           publicoBundle.putInt(
                   PublicoDAOImpl.ID, publico.getIdPublico());
           publicoBundle.putString(PublicoDAOImpl.DESCRIPTION,
                   publico.getDescripcion());
           startActivity(new Intent(
                   this, FormPublicoActivity.class)
                   .putExtras(publicoBundle));

       }
        if(item.getItemId()==2){
            publicoDAOImpl.borrar(publico, db);
            listar();
        }

        return super.onContextItemSelected(item);

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
