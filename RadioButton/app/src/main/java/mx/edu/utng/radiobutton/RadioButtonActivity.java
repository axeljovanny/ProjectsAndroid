package mx.edu.utng.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by qas on 27/01/16.
 */
public class RadioButtonActivity extends Activity {
    private RadioGroup grbCuatrimestre;
    private RadioButton radioSeleccionado;
    private ImageButton btnInscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_button_layout);
        initComponents();
    }

    private void initComponents() {
        grbCuatrimestre = (RadioGroup)findViewById(R.id.grb_cuatrimestre);
        btnInscribir = (ImageButton)findViewById(R.id.btn_inscribir);
        btnInscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int elementoSeleccionado = grbCuatrimestre.getCheckedRadioButtonId();
                radioSeleccionado = (RadioButton) findViewById(elementoSeleccionado);
                Toast.makeText(getApplicationContext(),
                        "Te inscribiste en el cuatrimestre "
                                +radioSeleccionado.getText(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

}