package mx.edu.utng.fragments;

import android.content.Intent;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements FragmentListado.CorreosListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentListado frgListado = (FragmentListado)
                getSupportFragmentManager()
                        .findFragmentById(R.id.frg_listado);
        frgListado.setListener(this);

    }

    @Override
    public void onCorreoSeleccionado(Correo c) {
        boolean hayDetalle = (
                getSupportFragmentManager()
                        .findFragmentById(R.id.frg_detalle)!=null);
        if(hayDetalle){
            ((FragmentDetalle)getSupportFragmentManager()
                    .findFragmentById(R.id.frg_detalle))
                    .mostrarDetalle(c.getTexto());

        }else{
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.TEXTO, c.getTexto());
            startActivity(i);


        }
    }
}

