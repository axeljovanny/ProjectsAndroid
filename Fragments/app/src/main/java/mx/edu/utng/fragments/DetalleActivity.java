package mx.edu.utng.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by qas on 9/02/16.
 */
public class DetalleActivity extends FragmentActivity {

    public static final String TEXTO = "texto prueba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        FragmentDetalle detalle = (FragmentDetalle)
                getSupportFragmentManager()
                .findFragmentById(R.id.frg_detalle);

        detalle.mostrarDetalle(getIntent().getStringExtra(TEXTO));
    }
}
