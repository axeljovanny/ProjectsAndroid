package mx.edu.utng.listcustomized;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityLista extends AppCompatActivity {


    private ListView lstCustomized;
    private String[] personas = {
            "Tacho","Emmanuel","Nancy",
            "Veronica","Mario","Juan Pablo",
            "Chesi","Maximo", "Juan Esp"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lista);
        lstCustomized = (ListView)findViewById(R.id.lst_customized);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                personas);
        lstCustomized.setAdapter(adapter);
        lstCustomized.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case  0:
                        Intent intent = new Intent(getApplicationContext(), ActividadMover.class);
                        intent.putExtra("nombre", personas[position]);
                        startActivity(intent);

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "posicion: "+ (position+1) +"|- "+personas[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Intent intento = new Intent(getApplicationContext(), ActividadTercera.class);
                        startActivity(intento);


                    default:
                        Toast.makeText(getApplicationContext(), "posicion: "+ (position+1) +"|- "+personas[position], Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });
    }
}
