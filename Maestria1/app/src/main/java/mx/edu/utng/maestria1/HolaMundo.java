package mx.edu.utng.maestria1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HolaMundo extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNombre;
    private Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola_mundo);
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        btnEnviar = (Button)findViewById(R.id.btn_enviar);
        btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("nombre", edtNombre.getText().toString());
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
