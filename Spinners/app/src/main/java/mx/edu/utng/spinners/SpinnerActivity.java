package mx.edu.utng.spinners;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by qas on 2/02/16.
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{

    private Spinner spiMaestrasGuapas;
    private ImageView imvMiMaestra;
    private TextView txvMaestras;
    private String hola = " <h1>primera linea</h1><br>\n" +
            "    <font color=\"red\">segunda linea</font><br>\n" +
            "    tercera linea<br><br>\n" +
            "    <p>lorem ipsum dolor sit amet...</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_example_layout);
        initComponents();
    }

    private  void initComponents(){
        spiMaestrasGuapas = (Spinner)findViewById(R.id.spi_maestras_guapas);
        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(
                        getApplicationContext(),
                        R.array.maestras_guapas,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiMaestrasGuapas.setAdapter(adapter);
        imvMiMaestra = (ImageView)findViewById(R.id.imv_mi_maestra);

        spiMaestrasGuapas.setOnItemSelectedListener(this);
        txvMaestras = (TextView)findViewById(R.id.txv_maestras);

        txvMaestras.setText(Html.fromHtml(hola));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                imvMiMaestra.setImageResource(R.drawable.dorismar);
                break;
            case 2:
                imvMiMaestra.setImageResource(R.drawable.lichelle);
                break;
            case 3:
                imvMiMaestra.setImageResource(R.drawable.mia);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
