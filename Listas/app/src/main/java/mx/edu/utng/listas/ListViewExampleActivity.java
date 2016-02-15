package mx.edu.utng.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewExampleActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    private ListView lsvPeliculas;
    private String[] peliculas = {"El niño", "El renacido",
            "La quinta ola", "Creed", "Guerra de Papás"};
    private String[] descripciones = {
            "Es una pelicula de terror", "Es una pelicula de" +
            " la relacion de un oso y un humano",
            "Pelicula de la extinción de la especie humana.",
            "Es la Rocky 8",
            "Comedia de una pareja que se pelea por el cariño " +
                    "de sus hijos."};
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_example_layout);
        initComponents();

    }

    private void initComponents(){
        lsvPeliculas = (ListView)findViewById(
                R.id.lsv_peliculas);
        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),
                R.layout.item_layout,
                R.id.txv_titulo,
                peliculas);
        lsvPeliculas.setAdapter(adapter);
        bundle = new Bundle();
        lsvPeliculas.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent,
                            View view, int position, long id) {
        bundle.putString("elegida", peliculas[position]);
        bundle.putString("descripcion", descripciones[position]);
        bundle.putInt("posicion", position);
        Intent intent = new Intent(
                ListViewExampleActivity.this,
                PeliculaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}