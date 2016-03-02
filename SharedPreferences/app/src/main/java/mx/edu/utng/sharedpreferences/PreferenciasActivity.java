package mx.edu.utng.sharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by qas on 1/03/16.
 */
public class PreferenciasActivity extends Activity implements
        View.OnClickListener{
    private Button btnPreferencias;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias_layout);
        initComponents();
    }

    private void initComponents(){
        btnPreferencias = (Button)findViewById(
                R.id.btn_preferencias);
        btnListar = (Button)findViewById(R.id.btn_listar);

        btnPreferencias.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.btn_preferencias) {
           startActivity(new Intent(
                   this, SeleccionPreferenciasActivity.class));
       }
        if(v.getId()==R.id.btn_listar){
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(this);

            String mensaje =
                    preferences.getBoolean("mayor_18", false)==true?"SI":"NO";
            Toast.makeText(this, "Mayores de 18: "+mensaje,
                    Toast.LENGTH_LONG).show();

        }
    }
}
