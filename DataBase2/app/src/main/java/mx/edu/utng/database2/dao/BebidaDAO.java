package mx.edu.utng.database2.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.utng.database2.model.Bebida;

/**
 * Created by qas on 23/02/16.
 */
public interface BebidaDAO {
    void agregar(Bebida bebida, SQLiteDatabase db);
    void modificar(Bebida bebida, SQLiteDatabase db);
    void eliminar(Bebida bebida, SQLiteDatabase db);
    Cursor listar(SQLiteDatabase db);
 }
