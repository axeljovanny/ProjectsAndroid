package mx.edu.utng.sharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by qas on 1/03/16.
 */
public class PreferenciasActivity extends Activity implements
        View.OnClickListener{
    private Button btnPreferencias;
    private Button btnListar;
    private SharedPreferences preferences;
    private TextView txvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias_layout);
        initComponents();
    }

    private void initComponents(){
        preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        txvTitulo = (TextView)findViewById(R.id.txv_titulo);
        txvTitulo.setText(preferences.getString("nombre_aplicacion","Aplicacion sin preferencia"));
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

            String mensaje =
                    preferences.getBoolean("mayor_18", false)==true?"SI":"NO";
            mensaje+="\nIdioma: "+preferences.getString("idioma","")
                    +"\nIP base de datos: "+preferences.getString("ip_bd","")
                    +"\nNombre de la aplicación: "+preferences.getString("nombre_aplicacion","");

            Toast.makeText(this, "Mayores de 18: "+mensaje,
                    Toast.LENGTH_LONG).show();

        }
    }
}
