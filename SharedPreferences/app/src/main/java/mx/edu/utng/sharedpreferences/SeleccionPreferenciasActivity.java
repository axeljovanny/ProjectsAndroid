package mx.edu.utng.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by qas on 1/03/16.
 */
public class SeleccionPreferenciasActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones_preferencia);
        Preference btnRegresar =
                (Preference) findPreference(getString(R.string.btn_regresar));

        btnRegresar.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(
                        SeleccionPreferenciasActivity.this,
                        PreferenciasActivity.class));
                finish();
                return false;
            }
        });
    }
}

