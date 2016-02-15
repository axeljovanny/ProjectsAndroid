package mx.edu.utng.factory;

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

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Figura figura = factory.crearFigura(
                       edtFigura.getText().toString(), MainActivity.this);
                LinearLayout layPrincipal = (LinearLayout)
                        findViewById(R.id.lay_principal);
                layPrincipal.addView((View) figura);



            }
        });

    }
}
