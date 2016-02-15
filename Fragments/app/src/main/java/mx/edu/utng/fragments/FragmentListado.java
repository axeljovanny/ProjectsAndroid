package mx.edu.utng.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by qas on 8/02/16.
 */
public class FragmentListado extends Fragment {

    private Correo[] correos = new Correo[]{
            new Correo("Aleida Nuñez", "Necesito de tu apoyo", "Estoy sola, y no me funciona la maquina."),
            new Correo("Dorismar","Urgente","Presentate en mi departamento, solicito tu apoyo"),
            new Correo("Thor", "Ven pronto","Se me cayo el martillo")
    };

    private ListView lstListado;
    private CorreosListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lstListado = (ListView)getView().findViewById(R.id.lst_listado);
        lstListado.setAdapter(new CorreosAdapter(this));
        lstListado.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                if(listener!=null){
                    listener.onCorreoSeleccionado(
                            (Correo)lstListado.getAdapter()
                                    .getItem(position));
                }
            }
        });

    }

  class CorreosAdapter extends ArrayAdapter<Correo>{

      Activity context;

      public CorreosAdapter(Fragment context) {
          super(context.getActivity(),
                  R.layout.list_item_correo,
                  correos);
          this.context = context.getActivity();
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
          LayoutInflater inflater = context.getLayoutInflater();
          View item = inflater.inflate(R.layout.list_item_correo, null);
          TextView txvRemitente = (TextView)
                  item.findViewById(R.id.txv_remitente);
          txvRemitente.setText(correos[position].getRemitente());
          TextView txvAsunto= (TextView)
                  item.findViewById(R.id.txv_asunto);
          txvAsunto.setText(correos[position].getAsunto());
          return item;
      }
  }

    public interface CorreosListener{
        void onCorreoSeleccionado(Correo c);
    }

    public void setListener(CorreosListener listener) {
        this.listener = listener;
    }
}
