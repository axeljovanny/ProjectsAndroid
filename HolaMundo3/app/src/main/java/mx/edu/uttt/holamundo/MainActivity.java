package mx.edu.uttt.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombre = (EditText) findViewById(R.id.edt_nombre);
    }

    public void saludar(View v){
        Toast.makeText(this, "Hola "+edtNombre.getText().toString(),
                Toast.LENGTH_SHORT).show();

        Bundle data = new Bundle();
        String saludo = "Hola "+edtNombre.getText().toString();
        data.putString("saludo", saludo);
        Intent intent = new Intent(MainActivity.this, SegundaActividad.class);
        intent.putExtras(data);
        startActivity(intent);
        finish();
    }
}
