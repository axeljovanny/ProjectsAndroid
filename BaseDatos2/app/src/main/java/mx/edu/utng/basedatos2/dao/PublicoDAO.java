package mx.edu.utng.basedatos2.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.utng.basedatos2.model.Publico;


/**
 * Created by qas on 29/02/16.
 */
public interface PublicoDAO {
    void agregar(Publico publico, SQLiteDatabase db);
    void borrar(Publico publico, SQLiteDatabase db);
    void cambiar(Publico publico, SQLiteDatabase db);
    Cursor desplegar(SQLiteDatabase db);

}
