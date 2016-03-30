package mx.edu.utng.composite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qas on 11/03/16.
 */
public class PowerRangerAdapter extends ArrayAdapter{
    public PowerRangerAdapter(Context context,
              ArrayList<PowerRanger> powerRangers) {
        super(context, 0, powerRangers);

    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        PowerRanger powerRanger = (PowerRanger) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_layout, parent, false);
        }

        TextView txvPowerRanger = (TextView) convertView
                .findViewById(R.id.txv_power_ranger);

        txvPowerRanger.setText(powerRanger.toString());
        switch (powerRanger.toString()){
            case "Rosa":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.ROSA));
                break;
            case "Azul":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.AZUL));
                break;
            case "Amarillo":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.AMARILLO));
                break;
            case "Verde":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.VERDE));
                break;
            case "Negro":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.NEGRO));
                break;
            case "Rojo":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.ROJO));
                break;
            case "Plateado":
                txvPowerRanger.setBackgroundColor(
                        convertView.getResources().getColor(Zord.PLATEADO));
                break;

        }
        return convertView;
    }
}
