package mx.edu.utng.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by qas on 17/02/16.
 */
public class SQLController {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLController(Context context) {
        this.context = context;
    }

    public SQLController openDB() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void closeDB(){
        dbHelper.close();
    }

    public void add(String mark, String model, int year){
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAR_MARK, mark);
        values.put(DBHelper.CAR_MODEL, model);
        values.put(DBHelper.CAR_YEAR, year);
        database.insert(DBHelper.TABLE_NAME, null,
                values);
    }
    public int edit(long carId, String mark, String model, int year){
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAR_MARK, mark);
        values.put(DBHelper.CAR_MODEL, model);
        values.put(DBHelper.CAR_YEAR, year);
        int actualizados = database.update(DBHelper.TABLE_NAME,
                values,
                DBHelper.CAR_ID + " = "+carId,
                null);
        return  actualizados;
    }

    public void delete(long carId){
        database.delete(DBHelper.TABLE_NAME,
                DBHelper.CAR_ID + " = " + carId,
                null);
    }

    public Cursor readAll(){
        String[] columnas = {
                DBHelper.CAR_ID,
                DBHelper.CAR_MARK,
                DBHelper.CAR_MODEL,
                DBHelper.CAR_YEAR
        };
        Cursor cursor = database.query(
                DBHelper.TABLE_NAME,
                columnas, null, null, null, null,
                DBHelper.CAR_ID, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return  cursor;
    }

}
