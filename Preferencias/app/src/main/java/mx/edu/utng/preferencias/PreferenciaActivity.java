package mx.edu.utng.preferencias;

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
 * Created by qas on 3/03/16.
 */
public class PreferenciaActivity extends Activity
        implements View.OnClickListener{

    private Button btnUdapte;
    private Button btnShow;
    private SharedPreferences preferences;
    private TextView txvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencia_layout);
        initComponents();
    }

    private void initComponents(){
         preferences =
             PreferenceManager.getDefaultSharedPreferences(this);
        btnShow = (Button)findViewById(R.id.btn_show);
        btnUdapte = (Button)findViewById(R.id.btn_update);
        btnShow.setOnClickListener(this);
        btnUdapte.setOnClickListener(this);
        txvUserName = (TextView)findViewById(R.id.txv_user_name);
        txvUserName.setText(preferences.getString("edp_user_name",
                "No hay usuario."));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.user_name_pref));
                stringBuilder.append(" ");
                stringBuilder.append(
                        preferences.getString("edp_user_name",
                                getString(R.string.no_user)));
                stringBuilder.append("\n");
                stringBuilder.append(getString(R.string.ip_server));
                stringBuilder.append(" ");
                stringBuilder.append(
                        preferences.getString("edp_ip_server",getString(R.string.ip)));
                stringBuilder.append("\n");
                stringBuilder.append(getString(R.string.language_title));
                stringBuilder.append(" ");
                stringBuilder.append(
                        preferences.getString("lsp_languages",getString(R.string.no_language)));
                stringBuilder.append("\n");
                stringBuilder.append(getString(R.string.sound_enabled_title));
                stringBuilder.append(" ");
                stringBuilder.append(preferences.getBoolean(
                        "chp_sound_enabled",false)==true?"SI":"NO");




                Toast.makeText(getApplicationContext(),
                        stringBuilder.toString(),
                        Toast.LENGTH_SHORT).show();
             break;
            case R.id.btn_update:
                startActivity(new Intent(this,
                        SeleccionPreferencia.class));
                break;
            default:

                break;
        }
    }
}
