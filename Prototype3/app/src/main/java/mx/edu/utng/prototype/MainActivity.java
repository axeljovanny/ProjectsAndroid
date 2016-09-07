package mx.edu.utng.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtTamanio;
    private EditText edtExtension;
    private Button btnClonar;
    private GridView grvDocumentos;
    private ArrayList<Documento> documentos;
    private Documento documento;
    private DocumentoAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        edtTamanio = (EditText) findViewById(R.id.edt_tamanio);
        edtExtension = (EditText) findViewById(R.id.edt_extension);

        btnClonar = (Button)findViewById(R.id.btn_clonar);
        grvDocumentos = (GridView)findViewById(R.id.grv_documentos);
        documentos = new ArrayList<Documento>();
        documento = new Documento();

        btnClonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documento.setNombre(edtNombre.getText().toString());
                documento.setTamanio(Float.parseFloat(edtTamanio.getText().toString()));
                documento.setExtension(edtExtension.getText().toString());

                Documento clon = (Documento)documento.clonar();

                documentos.add(clon);

                adapter = new DocumentoAdapter(
                        MainActivity.this, documentos);
                grvDocumentos.setAdapter(adapter);
            }
        });
    }
}
