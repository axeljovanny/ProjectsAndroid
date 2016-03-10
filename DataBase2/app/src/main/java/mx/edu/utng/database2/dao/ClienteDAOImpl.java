package mx.edu.utng.database2.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.utng.database2.model.Bebida;
import mx.edu.utng.database2.model.Cliente;

/**
 * Created by qas on 23/02/16.
 */
public class ClienteDAOImpl implements ClienteDAO {


    @Override
    public void agregar(Cliente cliente, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, cliente.getNombre());
        values.put(DBHelper.LASTNAME, cliente.getApellidos());
        db.insert(DBHelper.TABLE_NAME_1, null, values);
    }

    @Override
    public void modificar(Cliente cliente, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, cliente.getNombre());
        values.put(DBHelper.LASTNAME, cliente.getApellidos());
        db.update(DBHelper.TABLE_NAME_1, values, DBHelper.ID+"="+cliente.getIdCliente(),
                null);
    }

    @Override
    public void eliminar(Cliente cliente, SQLiteDatabase db) {
        db.delete(DBHelper.TABLE_NAME_1, DBHelper.ID + " = " + cliente.getIdCliente(), null);
    }

    @Override
    public Cursor listar(SQLiteDatabase db) {
        return db.query(DBHelper.TABLE_NAME_1, null, null, null, null, null, null);
    }

    @Override
    public Cliente autenticar(SQLiteDatabase db, String usuario, String clave) {
        Cursor cursor = db.query(DBHelper.TABLE_NAME_1, null,"nombre=? and clave=?",new String[]{usuario,clave}, null, null, null);
        cursor.moveToFirst();
        Cliente cliente = null;
        while (cursor.moveToNext()){
            cliente = new Cliente(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(1),
                    cursor.getString(2));
        }
        return cliente;
    }
}
