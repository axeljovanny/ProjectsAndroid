package mx.edu.utng.visuales;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by qas on 27/01/16.
 */
public class ToggleActivity extends Activity {
    private ToggleButton tbnSalir;
    private TextView txvMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.componentes_layout);
        initComponents();

    }
    private void initComponents(){
        tbnSalir = (ToggleButton)findViewById(R.id.tbn_salir);
        txvMensaje = (TextView)findViewById(R.id.txv_mensaje);
        tbnSalir.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton
                     buttonView, boolean isChecked) {
                if(isChecked){
                    txvMensaje.setText(R.string.aceptado);
                }else{
                    txvMensaje.setText(R.string.chin);
                }
            }
        });
    }
}
