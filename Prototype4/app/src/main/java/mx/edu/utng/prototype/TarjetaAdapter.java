package mx.edu.utng.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qas on 5/09/16.
 */
public class TarjetaAdapter extends ArrayAdapter<TarjetaCredito>{

    public TarjetaAdapter(Context context,
                          ArrayList<TarjetaCredito> tarjetas){
        super(context, 0, tarjetas);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
       TarjetaCredito tarjetaCredito = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.tarjeta_layout, parent, false);
        }

        TextView txvTarjeta = (TextView)
                convertView.findViewById(R.id.txv_tarjeta);
        txvTarjeta.setText(tarjetaCredito.getNumero()+" "+
        tarjetaCredito.getTitular());

        return convertView;
    }
}