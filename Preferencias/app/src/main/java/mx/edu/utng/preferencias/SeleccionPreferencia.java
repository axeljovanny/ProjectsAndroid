package mx.edu.utng.preferencias;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by qas on 3/03/16.
 */
public class SeleccionPreferencia
        extends PreferenceActivity {
    Preference btnBack;
    @Override
    protected void onCreate(
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(
                R.xml.configuraciones);
        btnBack = (Preference)findPreference(getString(R.string.btn_back));
        btnBack.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(SeleccionPreferencia.this,
                        PreferenciaActivity.class));
                finish();
                return false;
            }
        });

    }
}
