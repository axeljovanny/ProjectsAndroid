package mx.edu.utng.composite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qas on 11/03/16.
 */
public class CompositeActivity extends Activity implements View.OnClickListener{
    private Spinner spiPowerRangers;
    private Button btnAgregar;
    private Button btnPelear;
    private GridView grvPowerRanges;
    private PowerRanger powerRanger;
    private ArrayList<PowerRanger> powerRangers;
    private PowerRangerAdapter adapterGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composite_layout);
        spiPowerRangers =
                (Spinner)findViewById(R.id.spi_power_rangers);
        btnAgregar = (Button)findViewById(R.id.btn_agregar);
        btnPelear = (Button)findViewById(R.id.btn_pelear);
        grvPowerRanges = (GridView)findViewById(R.id.grv_power_rangers);

        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(
                        this, R.array.colores,
                        android.R.layout.simple_spinner_item
                );
        spiPowerRangers.setAdapter(adapter);

        btnAgregar.setOnClickListener(this);
        btnPelear.setOnClickListener(this);

        powerRangers = new ArrayList<PowerRanger>();

       adapterGrid =
                new PowerRangerAdapter(getApplicationContext(),
                                     powerRangers);
      }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_agregar:
                        powerRanger = new Zord(
                        spiPowerRangers.getSelectedItem().toString());
                        powerRangers.add(powerRanger);

                        grvPowerRanges.setAdapter(adapterGrid);

            break;
            case R.id.btn_pelear:

                break;
            default:
                break;
        }

    }
}
