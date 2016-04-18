package mx.edu.utng.gestbaby.dbsemana;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import mx.edu.utng.gestbaby.pack.Semana;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSemana {
    private Context context;
    private SQLiteDatabase db;
    private DataBaseHelperSemana dbHelper;

    public DatabaseSemana(Context context) {
        this.context = context;
    }

    public DatabaseSemana open() throws SQLException {
        this.dbHelper = new DataBaseHelperSemana(this.context);
        this.db = this.dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        this.dbHelper.close();
    }

    public List<Semana> getSemanas() {
        List<Semana> semanas = new ArrayList();
        try {
            this.dbHelper = new DataBaseHelperSemana(this.context);
            this.db = this.dbHelper.getReadableDatabase();
            Cursor cur = this.db.rawQuery("SELECT semana,tamano_bebe,peso_bebe,tu_bebe,tu_cuerpo,consejos_salud,frase_final,imagenes FROM SemanaASemana ORDER BY semana", new String[0]);
            if (cur.moveToFirst()) {
                do {
                    Semana semana = new Semana();
                    semana.setSemana(cur.getInt(0));
                    semana.setTamano(cur.getString(1));
                    semana.setPeso(cur.getString(2));
                    semana.setEstadoBebe(cur.getString(3));
                    semana.setEstadoMadre(cur.getString(4));
                    semana.setConejos(cur.getString(5));
                    semana.setPregunta(cur.getString(6));
                    String imagenes = cur.getString(7);
                    if (imagenes != null) {
                        String[] cantImagenes = imagenes.split(";");
                        List<String> listaImagenes = new ArrayList();
                        for (String s : cantImagenes) {
                            listaImagenes.add(s);
                        }
                        semana.setImagenes(listaImagenes);
                    }
                    semanas.add(semana);
                } while (cur.moveToNext());
            }
            if (false) {
                if (this.db != null) {
                    this.db.close();
                }
                return null;
            }
            if (cur != null) {
                cur.close();
            }
            if (this.db == null) {
                return semanas;
            }
            this.db.close();
            return semanas;
        } catch (SQLException e) {
            if (this.db != null) {
                this.db.close();
            }
            return null;
        }
    }
}
