package mx.edu.utng.gridviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by qas on 10/02/16.
 */
public class FrutasActivity extends Activity {
    private TextView txvMensaje;
    private GridView grdFrutas;

    private String[] frutas = new String[]{
                "Naranja","Melon","Papaya","Sandia","Pera",
                 "Plantano","Manzana","Guayaba","Fresa","Aguacate",
                "Jitomate","Zapote","Lima","Limón","Piña",
                "Mandarina","Kiwi","Maracuya","Coco","Toronja"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frutas_layout);
        initComponents();
    }

    private void initComponents(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, frutas);
        txvMensaje = (TextView)findViewById(R.id.txv_mensaje);
        grdFrutas = (GridView) findViewById(R.id.grd_frutas);

        grdFrutas.setAdapter(adapter);

        grdFrutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                   View view, int position, long id) {
                txvMensaje.setText("Fruta seleccionada: "
                        +frutas[position]);
            }
        });

    }
}
