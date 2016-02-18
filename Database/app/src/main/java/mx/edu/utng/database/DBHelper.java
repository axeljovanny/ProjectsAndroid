package mx.edu.utng.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qas on 17/02/16.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "tbl_automovil";
    public static final String CAR_ID = "_id";
    public static final String CAR_MARK = "_marca";
    public static final String CAR_MODEL = "_modelo";
    public static final String CAR_YEAR = "_anio";

    private static final String DB_NAME = "AGENCIA";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE =
            "CREATE TABLE "+TABLE_NAME+"("
            + CAR_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAR_MARK + " TEXT, "
            + CAR_MODEL + " TEXT, "
            + CAR_YEAR +" INTEGER NOT NULL);";




    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
               int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
