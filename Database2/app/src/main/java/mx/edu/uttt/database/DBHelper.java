package mx.edu.uttt.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qas on 23/08/16.
 */
public class DBHelper extends SQLiteOpenHelper{

    public  static final String TABLE_EMPLOYEE = "empleados";
    public  static final String EMPLOYEE_ID = "_id";
    public  static final String EMPLOYEE_NAME = "nombre";
    public  static final String EMPLOYEE_RFC = "rfc";
    public  static final String EMPLOYEE_PHONE = "phone";
    public  static final int DB_VERSION = 1;
    public  static final String DB_NAME = "bd_empleados";

    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_EMPLOYEE +
            "("+ EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_NAME +
            " TEXT NOT NULL, " + EMPLOYEE_RFC + " TEXT,  "+ EMPLOYEE_PHONE + " TEXT);";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_EMPLOYEE);
        onCreate(db);
    }
}
