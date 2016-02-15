package mx.edu.utng.togglebutton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by qas on 26/01/16.
 */
public class ToggleActivity extends Activity{

    private ToggleButton tbnPrimero;
    private ToggleButton tbnSegundo;
    private TextView txvPrimero;
    private TextView txvSegundo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_activity);
        initComponents();
    }

    private void initComponents(){
        tbnPrimero = (ToggleButton)findViewById(R.id.tbn_primero);
        tbnSegundo = (ToggleButton)findViewById(R.id.tbn_segundo);
        txvPrimero = (TextView)findViewById(R.id.txv_primero);
        txvSegundo = (TextView)findViewById(R.id.txv_segundo);
        txvPrimero.setText("El boton esta en apagado");
        txvSegundo.setText("El boton esta en OFF");
        tbnPrimero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   txvPrimero.setText("El boton esta encendido.");
                }else{
                    txvPrimero.setText("El boton esta apagado.");
                }
            }
        });
    }

    public void onToggleClick(View view) {
        if(((ToggleButton)view).isChecked()){
            txvSegundo.setText("El boton esta on.");
            Log.i("ToggleActivity", "El boton esta en on");
        }else{
            txvSegundo.setText("El boton esta off.");
            Log.i("ToggleActivity", "El boton esta en off");
        }
    }
}
