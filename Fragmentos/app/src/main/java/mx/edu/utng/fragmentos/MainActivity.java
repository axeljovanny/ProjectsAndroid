package mx.edu.utng.fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements ListaCorreoFragment.CorreosListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ListaCorreoFragment listaCorreoFragment
                =(ListaCorreoFragment)getSupportFragmentManager()
                .findFragmentById(R.id.frg_listado);

        listaCorreoFragment.setListener(this);
    }



    @Override
    public void onCorreoSeleccionado(Correo c) {
        boolean hayDetalle =
                (getSupportFragmentManager()
                        .findFragmentById(R.id.frg_detalle) != null);

        if(hayDetalle) {
            ((DetalleCorreoFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.frg_detalle)).mostrarDetalle(c.getTexto());
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.TEXTO, c.getTexto());
            startActivity(i);
        }
    }
}