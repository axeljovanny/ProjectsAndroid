package mx.edu.utng.radiobuttons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by qas on 28/01/16.
 */
public class RadioButtonActivity extends Activity
    implements View.OnClickListener{

    private RadioGroup rgpPreferencia;
    private RadioButton rbtSeleccionado;
    private ImageButton btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_button_layout);
        initComponents();
    }
    private void initComponents(){
        rgpPreferencia =
               (RadioGroup)findViewById(R.id.rgp_preferencia);
        btnEnviar = (ImageButton)findViewById(R.id.btn_enviar);
         btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int selecionado =
                rgpPreferencia.getCheckedRadioButtonId();
        rbtSeleccionado = (RadioButton)findViewById(selecionado);
        Toast.makeText(getApplicationContext(),
                "Eres "+rbtSeleccionado.getText().toString(),
                Toast.LENGTH_SHORT).show();
    }
}
