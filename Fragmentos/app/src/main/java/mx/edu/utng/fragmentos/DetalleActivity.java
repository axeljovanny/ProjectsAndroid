package mx.edu.utng.fragmentos;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;


/**
 * Created by qas on 9/02/16.
 */
public class DetalleActivity extends FragmentActivity {

    public static final String TEXTO = "texto";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_layout);

        DetalleCorreoFragment detalleCorreoFragment =
                (DetalleCorreoFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.frg_detalle);
        detalleCorreoFragment.mostrarDetalle(getIntent()
                .getStringExtra(TEXTO));


    }
}
