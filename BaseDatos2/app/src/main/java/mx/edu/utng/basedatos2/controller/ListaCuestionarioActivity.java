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
import mx.edu.utng.basedatos2.model.Cuestionario;
import mx.edu.utng.basedatos2.util.CuestionarioCursorAdapter;

/**
 * Created by qas on 22/02/16.
 */
public class ListaCuestionarioActivity extends Activity{
   private ListView lsvCuestionarios;
    private SQLiteDatabase db;
    private Cuestionario cuestionario;
    private Cursor cursor;
    private CuestionarioDAOImpl cuestionarioDAOImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cuestionario_layout);
        initComponents();
    }

    private void initComponents(){
        lsvCuestionarios = (ListView)findViewById(R.id.lsv_cuestionarios);
        cuestionarioDAOImpl = new CuestionarioDAOImpl(this,"utng.db", null,
                CuestionarioDAOImpl.DATABASE_VERSION);
        db = cuestionarioDAOImpl.getWritableDatabase();
        listar();
        registerForContextMenu(lsvCuestionarios);
    }

    private void listar(){
        cursor = cuestionarioDAOImpl.desplegar(db);
        CuestionarioCursorAdapter adapter = new CuestionarioCursorAdapter(
                this, cursor);
        lsvCuestionarios.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        cursor.moveToPosition(info.position);

        boolean activo = cursor.getString(3).equals("1")?true:false;

        cuestionario = new Cuestionario(
                cursor.getInt(0),
                cursor.getString(1),
                Date.valueOf(cursor.getString(2)),
                activo,
                cursor.getInt(4));

        menu.setHeaderTitle("Opciones para "+cuestionario.getNombre());
        menu.add(0, 1, 1, getString(R.string.modificar));
        menu.add(0, 2, 1, getString(R.string.eliminar));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       if(item.getItemId()==1){
           Bundle cuestionarioBundle = new Bundle();
           cuestionarioBundle.putInt(
                   CuestionarioDAOImpl.ID, cuestionario.getIdCuestionario());
           cuestionarioBundle.putString(CuestionarioDAOImpl.NAME,
                   cuestionario.getNombre());
           cuestionarioBundle.putString(CuestionarioDAOImpl.CREATION_DATE,
                   cuestionario.getFechaCreacion().toString());
           cuestionarioBundle.putBoolean(CuestionarioDAOImpl.ACTIVE,
                   cuestionario.isActivo());
           cuestionarioBundle.putInt(CuestionarioDAOImpl.FOCUS_TO,
                   cuestionario.getDirigido());
           startActivity(new Intent(
                   this, FormCuestionarioActivity.class)
                   .putExtras(cuestionarioBundle));

       }
        if(item.getItemId()==2){
            cuestionarioDAOImpl.borrar(cuestionario, db);
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
            case R.id.itm_publico:
                startActivity(new Intent(this, FormPublicoActivity.class));
                return true;
            default:
                startActivity(new Intent(this, FormCuestionarioActivity.class));
                return true;
        }
    }
}
