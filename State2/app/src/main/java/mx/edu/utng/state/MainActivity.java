package mx.edu.utng.state;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnEncender;
    private TV tv;
    private Apagado estadoInicial;
    private LinearLayout layPrincipal;
    private Lienzo lienzo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEncender = (ImageButton)findViewById(R.id.btn_encendido);
        layPrincipal = (LinearLayout)findViewById(R.id.lay_principal);
        estadoInicial = new Apagado();
        tv = new TV(estadoInicial);
        lienzo = new Lienzo(MainActivity.this,
                estadoInicial, tv);
        layPrincipal.addView(lienzo);

        btnEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(lienzo.toString());
                System.out.println(tv.toString());
                System.out.println(tv.getEstado().toString());
                lienzo.setEstado(tv.getEstado());
                tv.oprimirBoton();
                lienzo.invalidate();
            }
        });

    }
}
