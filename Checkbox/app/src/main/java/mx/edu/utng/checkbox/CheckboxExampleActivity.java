package mx.edu.utng.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by qas on 27/01/16.
 */
public class CheckboxExampleActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox chkDA3;
    private CheckBox chkCDS;
    private CheckBox chkIS2;
    private CheckBox chkEOE2;
    private CheckBox chkAP;
    private CheckBox chkI2;
    private CheckBox chkI5;
    private TextView txvSeleccionados;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox_layout);
        initComponents();
        resultado = "";
    }

    private void initComponents(){
        chkDA3 = (CheckBox)findViewById(R.id.chk_da3);
        chkAP = (CheckBox)findViewById(R.id.chk_ap);
        chkCDS = (CheckBox)findViewById(R.id.chk_cds);
        chkEOE2 = (CheckBox)findViewById(R.id.chk_eoe2);
        chkI2 = (CheckBox)findViewById(R.id.chk_i2);
        chkI5 = (CheckBox)findViewById(R.id.chk_i5);
        chkIS2 = (CheckBox)findViewById(R.id.chk_is2);
        txvSeleccionados = (TextView)findViewById(R.id.txv_seleccionados);

        chkDA3.setOnCheckedChangeListener(this);
        chkI5.setOnCheckedChangeListener(this);
        chkIS2.setOnCheckedChangeListener(this);
        chkI2.setOnCheckedChangeListener(this);
        chkEOE2.setOnCheckedChangeListener(this);
        chkCDS.setOnCheckedChangeListener(this);
        chkAP.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            resultado+=buttonView.getText().toString()+"\n";
        }else {
           resultado =
                resultado.replace(buttonView.getText().toString() + "\n", "");
        }

        txvSeleccionados.setText(resultado);
        if(!resultado.equals("".trim())) {
            Toast.makeText(getApplicationContext(),
                    resultado, Toast.LENGTH_SHORT).show();
        }
    }
}
