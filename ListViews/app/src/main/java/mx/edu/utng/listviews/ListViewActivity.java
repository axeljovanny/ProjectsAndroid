package mx.edu.utng.listviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by qas on 3/02/16.
 */
public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView lsvTransporte;
    private String[] transportes = {"Tren","Automovil",
            "Bicicleta", "Motocicleta", "Submarino", "Barco",
            "Avión"};
    private String[] descripciones = {"El tren es un vehiculo compuesto una serie de vagones " +
            "remolcados a una locomotora.",
            "Es un vehiculo autopropulsado destinado para el transporte de personas, " +
                    "sin necesidad de carriles.",
            "Es un vehiculo de transporte personal, de propulsión humana, con dos ruedas y" +
                    " un sistema de transmisión por pedales.",
            "Es un vehiculo de dos ruedas impulsado por un motor que" +
                    "acciona la rueda trasera.",
            "Es un navio o buque capaz de navegar bajo la superficie del " +
                    "agua del mar o sumergido.",
            "Es un medio de trasporte capaz de flotar en el agua.",
            "Es un vehiculo dotado de alas capas de volar usando motores."};
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_example_layout);
        initComponents();
    }
    private void initComponents(){
        lsvTransporte = (ListView)findViewById(R.id.lsv_transporte);
        ArrayAdapter adapter = new ArrayAdapter(
                getApplication(),
                R.layout.item_layout,
                R.id.txv_item,
                transportes);
        lsvTransporte.setAdapter(adapter);
        lsvTransporte.setOnItemClickListener(this);
        bundle = new Bundle();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bundle.putString("elegido", transportes[position]);
        bundle.putInt("posicion", position);
        bundle.putString("descripcion", descripciones[position]);
        Intent intent = new Intent( ListViewActivity.this,
                SeleccionActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
