package mx.edu.uttt.holamundo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qas on 22/08/16.
 */
public class SegundaActividad extends AppCompatActivity {

    private TextView txvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_segunda);
        Bundle bundle = getIntent().getExtras();

        txvSaludo = (TextView) findViewById(R.id.txv_saludo);

        txvSaludo.setText(bundle.getString("saludo"));

    }

    public void envioDatos(View v){
        String email = "crackiman@gmail.com";
        String password = "123456";

        Bundle bundle = new Bundle();
        bundle.putString("correo", email);
        bundle.putString("clave", password);
        bundle.putInt("id", 13);
        bundle.putFloat("capital", 123.333f);

        Intent intento = new Intent(this, LoginActivity.class);
        intento.putExtras(bundle);
        intento.putExtra("edad", 18);

        startActivity(intento);
    }
}
