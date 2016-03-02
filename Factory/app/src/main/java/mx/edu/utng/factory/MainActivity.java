package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtFigura;
    private Button btnCrear;
    private FiguraFactory factory;
    private LinearLayout layPrincipal;
    private Figura figura;
    private Lienzo lienzo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        edtFigura = (EditText)findViewById(R.id.edt_figura);
        btnCrear = (Button)findViewById(R.id.btn_crear);
        factory = new FiguraFactory();

        layPrincipal = (LinearLayout)
                findViewById(R.id.lay_principal);

        lienzo = new Lienzo(MainActivity.this,
                figura);

        layPrincipal.addView(lienzo);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figura = factory.crearFigura(
                       edtFigura.getText().toString());

               lienzo.setFigura(figura);
                lienzo.invalidate();
               // lienzo.draw(new Canvas());
            }
        });

    }
}
