package mx.edu.utng.fragmentos;


import android.app.Activity;

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
 * Created by qas on 9/02/16.
 */
public class ListaCorreoFragment extends Fragment {

    private Correo[] correos = {
            new Correo("Dorismar", "Te necesito", "Arreglame la compu"),
            new Correo("Esperanza Gomez", "Requiero mantenimiento", "Te espero en mi departamento"),
            new Correo("Rea","Reunion","Quiero que resuelvas la existencia"),
            new Correo("Carmen Eugenia","Te amo", "Me asustas pero me gustas")
    };
    private ListView lsvCorreos;

    private CorreosListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado,
                container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lsvCorreos = (ListView)getView().findViewById(R.id.lsv_correos);
        lsvCorreos.setAdapter(new CorreosAdapter(this));

        lsvCorreos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position,
                                    long id) {
                if(listener!=null){
                    listener.onCorreoSeleccionado(
                            (Correo) lsvCorreos
                                    .getAdapter()
                                    .getItem(position));
                }
            }
        });

    }

    class CorreosAdapter extends ArrayAdapter<Correo>{

        Activity context;

        public CorreosAdapter(Fragment context) {
            super(context.getContext(), R.layout.item_layout, correos);
            this.context = context.getActivity();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_layout, null);
            TextView txvRemitente =
                    (TextView)item.findViewById(R.id.txv_remitente);
            TextView txvAsunto = (TextView)item.findViewById(R.id.txv_asunto);
            txvRemitente.setText(correos[position].getRemitente());
            txvAsunto.setText(correos[position].getAsunto());

            return item;

        }
    }

    public interface CorreosListener{
        void onCorreoSeleccionado(Correo c);
    }

    public CorreosListener getListener() {
        return listener;
    }

    public void setListener(CorreosListener listener) {
        this.listener = listener;
    }
}