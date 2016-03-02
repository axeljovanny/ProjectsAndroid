package mx.edu.utng.database2.util;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import mx.edu.utng.database2.R;
import mx.edu.utng.database2.dao.BebidaDAOImpl;
import mx.edu.utng.database2.dao.DBHelper;

/**
 * Created by qas on 23/02/16.
 */
public class BebidaAdapter extends CursorAdapter {

    public BebidaAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        View view =
                inflater.inflate(R.layout.item_layout, null, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txvNombre = (TextView)view.findViewById(R.id.txv_nombre);
        TextView txvPrecio = (TextView)view.findViewById(R.id.txv_precio);
        TextView txvFechaCaducidad = (TextView)view.findViewById(R.id.txv_fecha_caducidad);
        TextView txvDisponible = (TextView)view.findViewById(R.id.txv_disponible);
        txvNombre.setText(cursor.getString(
                cursor.getColumnIndex(DBHelper.NAME)));
        txvPrecio.setText(
                String.format("%.2f",
                        cursor.getFloat(
                                cursor.getColumnIndex(DBHelper.PRICE))));
        txvFechaCaducidad.setText(cursor.getString(
                cursor.getColumnIndex(DBHelper.DUE_DATE)));
        if(cursor.getString(
                cursor.getColumnIndex(DBHelper.DISPONIBLE)).equals("1")) {
            txvDisponible.setText("Si");
        }else{
            txvDisponible.setText("No");
        }

    }
}
