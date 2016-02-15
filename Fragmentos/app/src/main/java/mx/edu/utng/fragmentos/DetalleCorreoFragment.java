package mx.edu.utng.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by qas on 9/02/16.
 */
public class DetalleCorreoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_detalle, container, false);
    }

    public void mostrarDetalle(String texto){
        TextView txvDetalle = (TextView)getView().findViewById(
                R.id.txv_detalle);
        txvDetalle.setText(texto);
    }
}
