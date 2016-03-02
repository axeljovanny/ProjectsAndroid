package mx.edu.utng.database2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qas on 23/02/16.
 */
public class DBHelper extends SQLiteOpenHelper{
   public static final String DATABASE_NAME = "utng.db";
   public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_1 = "tbl_cliente";
    public static final String TABLE_NAME_2 = "tbl_bebida";
    public static final String NAME = "nombre";
    public static final String LASTNAME = "apellidos";
    public static final String PRICE = "precio";
    public static final String DUE_DATE = "fecha_caducidad";
    public static final String CUSTOMER_ID = "id_cliente";
    public static final String DISPONIBLE = "disponible";
    public static final String ID = "_id";

    private static final String CREATE_TABLE_1 =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_1
                    +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +NAME+" TEXT, "
                    +LASTNAME+" TEXT);";

    private static final String CREATE_TABLE_2 =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_2
                    +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +NAME+" TEXT, "
                    +PRICE+" TEXT, "
                    +DUE_DATE+" TEXT, "
                    +DISPONIBLE+" TEXT, "
                    +CUSTOMER_ID+" INTEGER, FOREIGN KEY("
                    +CUSTOMER_ID+") REFERENCES "
                    +TABLE_NAME_1+"("
                    +ID+"));";

    public DBHelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_1);
        db.execSQL(CREATE_TABLE_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_2);
        onCreate(db);
    }
}
