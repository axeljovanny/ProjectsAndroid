package mx.edu.utng.singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtNumAlumnos;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        edtNumAlumnos = (EditText) findViewById(R.id.edt_alumnos);
        btnCrear = (Button) findViewById(R.id.btn_crear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carrera carrera = Carrera.getCarrera();
                carrera.setNombre(edtNombre.getText().toString());
                carrera.setNumAlumnos(
                        Integer.parseInt(
                                edtNumAlumnos.getText().toString()));

                Toast.makeText(MainActivity.this, "Carrera: "
                            +"\nNombre: "+carrera.getNombre()
                            +"\nNumero de Alumnos: "+carrera.getNumAlumnos(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
