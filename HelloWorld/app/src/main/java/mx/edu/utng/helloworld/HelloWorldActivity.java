package mx.edu.utng.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by qas on 20/01/16.
 */
public class HelloWorldActivity extends Activity{
    private EditText edtNumero1;
    private EditText edtNumero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);
        edtNumero1 = (EditText)findViewById(R.id.edt_numero_uno);
        edtNumero2 = (EditText)findViewById(R.id.edt_numero_dos);

    }

    public void sumar(View view){
        int resultado = Integer.parseInt(edtNumero1.getText().toString())
                + Integer.parseInt(edtNumero2.getText().toString());

        Toast.makeText(this, "El resultado es: "+resultado,
                Toast.LENGTH_SHORT).show();

    }
}
