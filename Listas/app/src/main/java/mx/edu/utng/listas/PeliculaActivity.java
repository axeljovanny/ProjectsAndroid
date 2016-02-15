package mx.edu.utng.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PeliculaActivity extends AppCompatActivity {

    private ImageView imvPelicula;
    private TextView txvSinopsis;

    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pelicula_layout);
        bundle = getIntent().getExtras();
        imvPelicula = (ImageView)findViewById(R.id.imv_pelicula);
        txvSinopsis = (TextView)findViewById(R.id.txv_sinopsis);
        txvSinopsis.setText("Pelicula: "+
        bundle.getString("elegida")+"\n"+
        "Descripcion: \n"+
        bundle.getString("descripcion"));
        switch (bundle.getInt("posicion")){
            case 0:
                imvPelicula.setImageResource(R.drawable.boy);
                break;
            case 1:
                imvPelicula.setImageResource(R.drawable.renacido);
                break;
            case 2:
                imvPelicula.setImageResource(R.drawable.ola);
                break;
            case 3:
                imvPelicula.setImageResource(R.drawable.creed);
                break;
            case 4:
                imvPelicula.setImageResource(R.drawable.papas);
                break;
            default:
                break;
        }

    }
}
