package mx.edu.utng.database2.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.utng.database2.model.Bebida;
import mx.edu.utng.database2.model.Cliente;

/**
 * Created by qas on 23/02/16.
 */
public interface ClienteDAO {
    void agregar(Cliente cliente, SQLiteDatabase db);
    void modificar(Cliente cliente, SQLiteDatabase db);
    void eliminar(Cliente cliente, SQLiteDatabase db);
    Cursor listar(SQLiteDatabase db);
 }
