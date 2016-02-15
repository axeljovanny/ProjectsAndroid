package mx.edu.utng.spinner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by qas on 2/02/16.
 */
public class SpinnerExampleActivity  extends Activity
        implements AdapterView.OnItemSelectedListener{
    private Spinner cboCarreras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_example_layout);
        initComponents();
    }
    private void initComponents(){
        cboCarreras = (Spinner)findViewById(R.id.cbo_carrera);
        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.carreras,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        cboCarreras.setAdapter(adapter);
        cboCarreras.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                startActivity(new Intent(this,
                        SistemasInformaticosActivity.class));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
