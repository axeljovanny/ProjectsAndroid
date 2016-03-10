package mx.edu.utng.prototype;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qas on 4/03/16.
 */
public class OvejaAdapter extends ArrayAdapter<Oveja>{

    public OvejaAdapter(Context context, ArrayList<Oveja> ovejas) {
        super(context,0, ovejas);
    }

    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Oveja oveja = getItem(position);

        if(convertView==null){
            convertView =
                    LayoutInflater.from(getContext()).inflate(
                    R.layout.oveja_layout, parent, false);
        }

        TextView txvOveja = (TextView)
                convertView.findViewById(R.id.txv_oveja);
        txvOveja.setText(oveja.getRaza()+" "+oveja.getColor());

        return convertView;

    }

}