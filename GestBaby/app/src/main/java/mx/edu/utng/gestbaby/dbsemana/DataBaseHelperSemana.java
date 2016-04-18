package mx.edu.utng.gestbaby.dbsemana;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import mx.edu.utng.gestbaby.pack.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelperSemana extends SQLiteOpenHelper {
    private static String DB_NAME;
    private static String DB_NAME_OUT;
    private static String DB_PATH;
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    static {
        DB_PATH = "/data/data/mx.edu.utng.gestbaby/databases/";
        DB_NAME = "semana1.sqlite";
        DB_NAME_OUT = "semana1.db";
    }

    public DataBaseHelperSemana(Context context) {
        super(context, DB_NAME_OUT, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME_OUT, null, 1);
        } catch (SQLiteException e) {
            checkDB = null;
        }
        if (checkDB != null) {
            checkDB.close();
        }
        if (checkDB != null) {
            return true;
        }
        return false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = this.myContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME_OUT);
        IOUtils.copy(myInput, myOutput);
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME_OUT, null, 1);
    }

    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
