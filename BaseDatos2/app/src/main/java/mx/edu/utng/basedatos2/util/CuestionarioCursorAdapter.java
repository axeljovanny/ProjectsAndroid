package mx.edu.utng.basedatos2.util;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mx.edu.utng.basedatos2.R;
import mx.edu.utng.basedatos2.dao.CuestionarioDAOImpl;

/**
 * Created by qas on 23/02/16.
 */
public class CuestionarioCursorAdapter extends CursorAdapter {

    public CuestionarioCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imvFoto = (ImageView)view.findViewById(R.id.imv_foto);
        TextView txvNombre = (TextView)view.findViewById(R.id.txv_nombre);
        TextView txvActivo = (TextView)view.findViewById(R.id.txv_activo);

        imvFoto.setImageResource(android.R.drawable.ic_menu_gallery);
        txvNombre.setText(cursor.getString(cursor.getColumnIndex(CuestionarioDAOImpl.NAME)));
       if(cursor.getString(cursor.getColumnIndex(CuestionarioDAOImpl.ACTIVE)).equals("1")){
            txvActivo.setText("Activo");
      }else if(cursor.getString(cursor.getColumnIndex(CuestionarioDAOImpl.ACTIVE)).equals("0")){
           txvActivo.setText("Inactivo");
       }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout,
                null, false);
        return view;
    }
}
