package mx.edu.utng.checkboxes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by qas on 28/01/16.
 */
public class CheckboxActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
    private TextView txvSeleccionados;
    private CheckBox chkSoftek;
    private CheckBox chkTata;
    private CheckBox chkIBM;
    private CheckBox chkGoogle;
    private CheckBox chkCades;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkboxes_layout);
        initComponents();
    }

    private void initComponents(){
        resultado = "";
        txvSeleccionados = (TextView)findViewById(R.id.txv_seleccionados);
        chkCades = (CheckBox)findViewById(R.id.chk_cades);
        chkGoogle = (CheckBox)findViewById(R.id.chk_google);
        chkIBM = (CheckBox)findViewById(R.id.chk_ibm);
        chkSoftek = (CheckBox)findViewById(R.id.chk_softek);
        chkTata = (CheckBox)findViewById(R.id.chk_tata);

        chkCades.setOnCheckedChangeListener(this);
        chkGoogle.setOnCheckedChangeListener(this);
        chkIBM.setOnCheckedChangeListener(this);
        chkSoftek.setOnCheckedChangeListener(this);
        chkTata.setOnCheckedChangeListener(this);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String textoSeleccionado =
                buttonView.getText().toString()+" ";
           if(isChecked){
               resultado+=textoSeleccionado;
           }else{
               resultado = resultado.replace(textoSeleccionado, "");
           }
        txvSeleccionados.setText(resultado);
    }
}
