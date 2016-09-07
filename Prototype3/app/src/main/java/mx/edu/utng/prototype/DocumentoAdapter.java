package mx.edu.utng.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qas on 2/09/16.
 */
public class DocumentoAdapter  extends ArrayAdapter<Documento>{

    public DocumentoAdapter(Context context, ArrayList<Documento> documentos){
        super(context, 0, documentos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Documento documento = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.document_layout, parent, false);
        }
        TextView txvDocumento = (TextView)convertView.findViewById(R.id.txv_documento);
        txvDocumento.setText(documento.getNombre()+""+documento.getExtension()+ " - "+documento.getTamanio());

        return convertView;
    }
}
