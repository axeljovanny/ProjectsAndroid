package mx.edu.utng.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by qas on 1/03/16.
 */
public class SelectorAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private static Vector<BookInfo> bookVector;

    public SelectorAdapter(Activity activity) {
        inflater = (LayoutInflater)
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        iniciarVector();

    }

    public static void iniciarVector(){
        bookVector = new Vector<BookInfo>();
        bookVector.add(new BookInfo("Kappa", "Akutagawa", R.drawable.kappa,
                "http://www.leemp3.com/leemp3/1/Kappa_akutagawa.mp3"));
        bookVector.add(new BookInfo("Avecilla", "Alas Clarín, Leopoldo",
                R.drawable.avecilla,
                "http://www.leemp3.com/leemp3/Avecilla_alas.mp3"));
        bookVector.add(new BookInfo("Divina Comedia", "Dante",
                R.drawable.divinacomedia,
                "http://www.leemp3.com/leemp3/8/Divina%20Comedia_alighier.mp3"));
        bookVector.add(new BookInfo("Viejo Pancho, El",
                "Alonso y Trelles, José", R.drawable.viejo_pancho,
                "http://www.leemp3.com/leemp3/1/viejo_pancho_trelles.mp3"));
        bookVector.add(new BookInfo("Canción de Rolando", "Anónimo",
                R.drawable.cancion_rolando,
                "http://www.leemp3.com/leemp3/1/Cancion%20de%20Rolando_anonimo.mp3"));
                bookVector.add(new BookInfo("Matrimonio de sabuesos","Agata Christie",
                        R.drawable.matrimonio_sabuesos,
                        "http://www.dcomg.upv.es/~jtomas/android/audiolibros/01.%20Matrimonio%20De%20Sabuesos.mp3"));
        bookVector.add(new BookInfo("La iliada","Homero",
                R.drawable.iliada,
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/la-iliada-homero184950.mp3"));
    }

    @Override
    public int getCount() {
        return bookVector.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txvAudioLibro;
        BookInfo bookInfo = bookVector.elementAt(position);
        View view = convertView;
        if(convertView==null) {
            view = inflater.inflate(R.layout.item_layout, null);
        }
        txvAudioLibro = (TextView)view.findViewById(R.id.titulo);
        imageView = (ImageView)view.findViewById(R.id.imageView1);
        imageView.setImageResource(bookInfo.getResourceImage());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        txvAudioLibro.setText(bookInfo.getName());

        return view;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
