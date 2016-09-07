package mx.edu.utng.factory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private EditText edtFigura;
    private Button btnCrear;
    private FiguraFactory factory;
    private Figura figura;
    private Lienzo lienzo;
    private LinearLayout layPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtFigura = (EditText)findViewById(R.id.edt_figura);
        btnCrear = (Button)findViewById(R.id.btn_crear);
        layPrincipal = (LinearLayout)findViewById(R.id.lay_principal);
        lienzo = new Lienzo(MainActivity.this, figura);

        factory = new FiguraFactory();

        layPrincipal.addView(lienzo);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figura = factory.crearFigura(
                        edtFigura.getText().toString());
                lienzo.setFigura(figura);
                lienzo.invalidate();
            }
        });




    }
}
