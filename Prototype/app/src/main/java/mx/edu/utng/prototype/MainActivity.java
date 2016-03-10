package mx.edu.utng.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    private EditText edtPeso;
    private EditText edtColor;
    private EditText edtRaza;
    private Button btnClonar;
    private GridView grvOvejas;
    private List<Oveja> ovejas;
    private Oveja oveja;
    private OvejaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        edtColor = (EditText)findViewById(R.id.edt_color);
        edtPeso = (EditText)findViewById(R.id.edt_peso);
        edtRaza = (EditText)findViewById(R.id.edt_raza);
        btnClonar = (Button)findViewById(R.id.btn_clonar);
        grvOvejas = (GridView)findViewById(R.id.grv_ovejas);
        ovejas = new ArrayList<Oveja>();
        oveja = new Oveja();

        btnClonar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
         oveja.setColor(edtColor.getText().toString());
         oveja.setPeso(
                 Float.parseFloat(edtPeso.getText().toString()));
         oveja.setRaza(edtRaza.getText().toString());
         Oveja ovejaClonada = (Oveja)oveja.clonar();

         ovejas.add(ovejaClonada);


        adapter = new OvejaAdapter(
                MainActivity.this, (ArrayList)ovejas);
        grvOvejas.setAdapter(adapter);

    }
}
