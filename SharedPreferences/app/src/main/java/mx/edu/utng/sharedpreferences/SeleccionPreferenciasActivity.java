package mx.edu.utng.sharedpreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by qas on 1/03/16.
 */
public class SeleccionPreferenciasActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones_preferencia);
    }
}

