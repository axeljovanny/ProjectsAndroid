package mx.edu.utng.database2.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.utng.database2.model.Bebida;

/**
 * Created by qas on 23/02/16.
 */
public class BebidaDAOImpl implements BebidaDAO {


    @Override
    public void agregar(Bebida bebida, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, bebida.getNombre());
        values.put(DBHelper.PRICE, bebida.getPrecio());
        values.put(DBHelper.DUE_DATE, bebida.getFechaCaducidad().toString());
        values.put(DBHelper.DISPONIBLE, bebida.isDisponible());
        values.put(DBHelper.CUSTOMER_ID, bebida.getCliente());
        db.insert(DBHelper.TABLE_NAME_2, null, values);
    }

    @Override
    public void modificar(Bebida bebida, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, bebida.getNombre());
        values.put(DBHelper.PRICE, bebida.getPrecio());
        values.put(DBHelper.DUE_DATE, bebida.getFechaCaducidad().toString());
        values.put(DBHelper.DISPONIBLE, bebida.isDisponible());
        values.put(DBHelper.CUSTOMER_ID, bebida.getCliente());
        db.update(DBHelper.TABLE_NAME_2, values, DBHelper.ID + "=" + bebida.getIdBebida(),
                null);
    }

    @Override
    public void eliminar(Bebida bebida, SQLiteDatabase db) {
        db.delete(DBHelper.TABLE_NAME_2, DBHelper.ID + " = " + bebida.getIdBebida(), null);
    }

    @Override
    public Cursor listar(SQLiteDatabase db) {

        return db.query(
                DBHelper.TABLE_NAME_2, null, null, null, null, null, null);
    }

    public Cursor autenticar(SQLiteDatabase db, String usuario, String clave){
        Cursor findEntry = db.query(
                DBHelper.TABLE_NAME_2, null,
                "usuario=? and clave=?",
                new String[] { usuario , clave}, null, null, null);
        return findEntry;
    }
}
