package mx.edu.utng.basedatosinterna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by qas on 15/02/16.
 */
public class SQLController {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLController(Context context) {
        this.context = context;
    }

    public SQLController openDB() throws SQLException{
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void closeDB(){
        dbHelper.close();
    }
    
    public void insert(String name, int students){
        ContentValues values = new ContentValues();
        values.put(DBHelper.UNIVERSITY_NAME, name);
        values.put(DBHelper.UNIVERSITY_STUDENTS, students);
        database.insert(DBHelper.TABLE_UNIVERSITIES, null, values);
    }

    public int update(long id, String name, int students){
        ContentValues values = new ContentValues();
        values.put(DBHelper.UNIVERSITY_NAME, name);
        values.put(DBHelper.UNIVERSITY_STUDENTS, students);
        int i = database.update(DBHelper.TABLE_UNIVERSITIES,
                values, DBHelper.UNIVERSITY_ID +" = "+id, null);
        return  i;
    }

    public void delete(long id){
        database.delete(DBHelper.TABLE_UNIVERSITIES,
                DBHelper.UNIVERSITY_ID +" = "+id, null);
    }

    public Cursor read(){
        String[] allColumns = new String[]{
                DBHelper.UNIVERSITY_ID,
                DBHelper.UNIVERSITY_NAME,
                DBHelper.UNIVERSITY_STUDENTS
        };

        Cursor cursor = database.query(
                DBHelper.TABLE_UNIVERSITIES,
                allColumns, null,null,null,null,
                DBHelper.UNIVERSITY_NAME,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

}
