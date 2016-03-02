package mx.edu.utng.basedatos2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.utng.basedatos2.model.Cuestionario;

/**
 * Created by qas on 22/02/16.
 */
public class CuestionarioDAOImpl extends SQLiteOpenHelper implements CuestionarioDAO {

    private static final String TABLE_NAME_1 = "tbl_publico";
    private static final String TABLE_NAME_2 = "tbl_cuestionario";
    public static final String NAME = "nombre";
    public static final String CREATION_DATE = "fecha_creacion";
    public static final String ACTIVE = "activo";
    public static final String FOCUS_TO = "id_publico";
    public static final String ID = "id";
    public static final String DESCRIPTION = "descripcion";

    public static final String DATABASE_NAME = "utng.db";
    public static final int DATABASE_VERSION = 1;


    public CuestionarioDAOImpl(Context context, String name,
                               SQLiteDatabase.CursorFactory factory,
                               int version) {
        super(context, name, factory, version);
    }

    @Override
    public void agregar(Cuestionario cuestionario, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(NAME, cuestionario.getNombre());
        values.put(CREATION_DATE,
                cuestionario.getFechaCreacion().toString());
        values.put(ACTIVE, cuestionario.isActivo());
        values.put(FOCUS_TO, cuestionario.getDirigido());
        db.insert(TABLE_NAME_2, null, values);
        }

    @Override
    public void borrar(Cuestionario cuestionario, SQLiteDatabase db) {
        db.delete(TABLE_NAME_2, "_id = "
                +cuestionario.getIdCuestionario(), null);
    }

    @Override
    public void cambiar(Cuestionario cuestionario, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(NAME, cuestionario.getNombre());
        values.put(CREATION_DATE,
                cuestionario.getFechaCreacion().toString());
        values.put(ACTIVE, cuestionario.isActivo());
        values.put(FOCUS_TO, cuestionario.getDirigido());
        db.update(TABLE_NAME_2,values, "_id = "
                +cuestionario.getIdCuestionario(), null);
    }

    @Override
    public Cursor desplegar(SQLiteDatabase db) {
        return db.query(TABLE_NAME_2,
                null, null, null, null, null, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_1+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DESCRIPTION +" TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_2+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT, "+
                "fecha_creacion TEXT, "+
                "activo TEXT, "+
                "id_publico INTEGER, " +
                "FOREIGN KEY (id_publico) REFERENCES "+
                TABLE_NAME_1+"(_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2+";");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1+";");
        onCreate(db);
    }
}
