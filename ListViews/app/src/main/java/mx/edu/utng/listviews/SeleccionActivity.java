package mx.edu.utng.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SeleccionActivity extends AppCompatActivity {

    private ImageView imvTrasporte;
    private TextView txvDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        initComponents();
    }
    private void initComponents(){
        imvTrasporte = (ImageView)findViewById(R.id.imv_transporte);
        txvDescripcion = (TextView)findViewById(R.id.txv_descripcion);
        Bundle valoresRecibidos = getIntent().getExtras();
        switch (valoresRecibidos.getInt("posicion")){
            case 0:
                imvTrasporte.setImageResource(R.drawable.tren);
                break;
            case 1:
                imvTrasporte.setImageResource(R.drawable.automovil);
                break;
            case 2:
                imvTrasporte.setImageResource(R.drawable.bicicleta);
                break;
            case 3:
                imvTrasporte.setImageResource(R.drawable.motocicleta);
                break;
            case 4:
                imvTrasporte.setImageResource(R.drawable.submarino);
                break;
            case 5:
                imvTrasporte.setImageResource(R.drawable.barco);
                break;

        }
        txvDescripcion.setText(
                "Nombre: "
                        +valoresRecibidos.getString("elegido")+"\n\n"+
                        "Descripcion: \n"+
                        valoresRecibidos.getString("descripcion")
        );
        txvDescripcion.setTextColor(R.color.colorPrimaryDark);
        txvDescripcion.setTextSize(20.0f);

    }
}
