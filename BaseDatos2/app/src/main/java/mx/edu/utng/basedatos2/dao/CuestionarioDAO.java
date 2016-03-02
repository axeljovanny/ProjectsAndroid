package mx.edu.utng.basedatos2.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import mx.edu.utng.basedatos2.model.Cuestionario;

/**
 * Created by qas on 22/02/16.
 */
public interface CuestionarioDAO {
    void agregar(Cuestionario cuestionario, SQLiteDatabase db);
    void borrar(Cuestionario cuestionario, SQLiteDatabase db);
    void cambiar(Cuestionario cuestionario, SQLiteDatabase db);
    Cursor desplegar(SQLiteDatabase db);
}
