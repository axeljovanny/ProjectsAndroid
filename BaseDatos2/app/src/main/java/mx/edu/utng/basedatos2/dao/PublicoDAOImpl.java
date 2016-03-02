package mx.edu.utng.basedatos2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.utng.basedatos2.model.Cuestionario;
import mx.edu.utng.basedatos2.model.Publico;

/**
 * Created by qas on 22/02/16.
 */
public class PublicoDAOImpl implements PublicoDAO {

    private static final String TABLE_NAME = "tbl_publico";
    public static final String DESCRIPTION = "descripcion";
    public static final String ID = "_id";


    @Override
    public void agregar(Publico publico, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DESCRIPTION, publico.getDescripcion());
        db.insert(TABLE_NAME, null, values);
        }

    @Override
    public void borrar(Publico publico, SQLiteDatabase db) {
        db.delete(TABLE_NAME, ID+" = "
                +publico.getIdPublico(), null);
    }

    @Override
    public void cambiar(Publico publico, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DESCRIPTION, publico.getDescripcion());
        db.update(TABLE_NAME,values, ID+" = "
                +publico.getIdPublico(), null);
    }

    @Override
    public Cursor desplegar(SQLiteDatabase db) {
        return db.query(TABLE_NAME,
                null, null, null, null, null, null);
    }


}
