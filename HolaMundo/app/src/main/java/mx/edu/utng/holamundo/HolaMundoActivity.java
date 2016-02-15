package mx.edu.utng.holamundo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by qas on 20/01/16.
 */
public class HolaMundoActivity extends Activity {
    private EditText edtNombre;
    private TextView txvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hola_mundo_layout);
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        txvSaludo = (TextView)findViewById(R.id.txv_saludo);
    }

    public void saludar(View view){
        Toast.makeText(this.getApplicationContext(),
                "Hola "+edtNombre.getText().toString(),
                Toast.LENGTH_SHORT).show();
        txvSaludo.setText("Hola "+edtNombre.getText().toString());
    }
}
