package mx.edu.uttt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by qas on 23/08/16.
 */
public class SQLController {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLController(Context context) {
        this.context = context;
    }

    public SQLController openDatabase() throws SQLException{
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(Employee employee){
        ContentValues values = new ContentValues();
        values.put(DBHelper.EMPLOYEE_NAME, employee.getName());
        values.put(DBHelper.EMPLOYEE_RFC, employee.getRfc());
        values.put(DBHelper.EMPLOYEE_PHONE, employee.getPhone());
        database.insert(DBHelper.TABLE_EMPLOYEE, null, values);

    }

    public Cursor select(){
        String[] colums = new String[]{
                DBHelper.EMPLOYEE_ID,
                DBHelper.EMPLOYEE_NAME,
                DBHelper.EMPLOYEE_RFC,
                DBHelper.EMPLOYEE_PHONE};
        Cursor cursor = database.query(DBHelper.TABLE_EMPLOYEE,
                colums, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return  cursor;
  }

  public Cursor selectOne(Employee employee){
      String query = "SELECT * FROM "+ DBHelper.TABLE_EMPLOYEE
              +" WHERE " + DBHelper.EMPLOYEE_ID + " = "+employee.getId();
      Cursor cursor = database.rawQuery(query, null);
      if(cursor!=null){
          cursor.moveToFirst();
      }
      return  cursor;
  }

    public int update(Employee employee){
        System.out.println(employee);
        ContentValues values = new ContentValues();
        values.put(DBHelper.EMPLOYEE_NAME, employee.getName());
        values.put(DBHelper.EMPLOYEE_RFC, employee.getRfc());
        values.put(DBHelper.EMPLOYEE_PHONE, employee.getPhone());
        int i = database.update(DBHelper.TABLE_EMPLOYEE, values,
                DBHelper.EMPLOYEE_ID + " = " + employee.getId(), null);
        return i;
    }

    public void delete(Employee employee){
        database.delete(DBHelper.TABLE_EMPLOYEE,
                DBHelper.EMPLOYEE_ID + " = " + employee.getId(),
                null);
    }


}
