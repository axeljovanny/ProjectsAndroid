package mx.edu.utng.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtRaza;
    private EditText edtColor;
    private EditText edtPeso;
    private Button btnClonar;
    private GridView grvOvejas;
    private ArrayList<Oveja> ovejas;
    private Oveja oveja;
    private OvejaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtRaza = (EditText)findViewById(R.id.edt_raza);
        edtColor = (EditText)findViewById(R.id.edt_color);
        edtPeso = (EditText)findViewById(R.id.edt_peso);

        btnClonar = (Button)findViewById(R.id.btn_clonar);

        grvOvejas = (GridView)findViewById(R.id.grv_ovejas);

        ovejas = new ArrayList<Oveja>();
        oveja = new Oveja();

        btnClonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               oveja.setRaza(edtRaza.getText().toString());
               oveja.setColor(edtColor.getText().toString());
               oveja.setPeso(
                       Float.parseFloat(
                               edtPeso.getText().toString()));
                Oveja clon = (Oveja) oveja.clonar();
                ovejas.add(clon);

                adapter = new OvejaAdapter(MainActivity.this,
                        (ArrayList)ovejas);
                grvOvejas.setAdapter(adapter);
            }
        });

    }
}
