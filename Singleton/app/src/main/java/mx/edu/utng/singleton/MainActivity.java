package mx.edu.utng.singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtHost;
    private EditText edtPuerto;
    private Button btnCrear;
    private BaseDatos bd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        edtHost = (EditText)findViewById(R.id.edt_host);
        edtPuerto = (EditText)findViewById(R.id.edt_puerto);
        btnCrear = (Button)findViewById(R.id.btn_crear);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bd==null){
                    bd = BaseDatos.getBd();
                    bd.setNombre(edtNombre.getText().toString());
                    bd.setPuerto(edtPuerto.getText().toString());
                    bd.setHost(edtHost.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this,
                            "La base de datos ya se ha creado " +
                            "no puedes crearla nuevamente",
                            Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(MainActivity.this,
                        "Base de datos: "+ bd.getNombre()+
                        "\nHost: "+ bd.getHost()+
                        "\nPuerto: "+ bd.getPuerto(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

}
