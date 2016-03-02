package mx.edu.utng.swipeactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by qas on 20/02/16.
 */
public class PedidosFragment extends Fragment{
    View rootView;
    int contador = 0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frm_pedidos, container, false);
        TextView texto = (TextView) rootView.findViewById(R.id.texto_pedidos);
        texto.setText("Incrementador de Pedidos" + "\n\n" + contador);
        contador++;
        return rootView;
    }
}
