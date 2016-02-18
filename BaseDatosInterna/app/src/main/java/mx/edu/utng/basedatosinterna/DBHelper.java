package mx.edu.utng.basedatosinterna;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qas on 15/02/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "bd_universidades";
    private final static int DB_VERSION = 1;
    public final static String TABLE_UNIVERSITIES = "tbl_universidad";
    public final static String UNIVERSITY_ID = "_id";
    public final static String UNIVERSITY_NAME = "nombre";
    public final static String UNIVERSITY_STUDENTS = "matricula";

    private static final String CREATE_TABLE =
            "CREATE TABLE "
                    +TABLE_UNIVERSITIES
                    + "(" + UNIVERSITY_ID
                    +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + UNIVERSITY_NAME + " TEXT NOT NULL, "
                    + UNIVERSITY_STUDENTS +" INTEGER);";


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
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_UNIVERSITIES);
        onCreate(db);

    }
}
