package mx.edu.utng.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by qas on 26/01/16.
 */
public class ExampleRelativeActivity extends Activity
        implements View.OnClickListener{
    private EditText edtUsuario;
    private EditText edtClave;
    private Button btnAcceder;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_relative_layout);
        initComponents();
    }

    private void initComponents(){
        edtUsuario = (EditText)findViewById(R.id.edt_usuario);
        edtClave = (EditText)findViewById(R.id.edt_clave);
        btnAcceder = (Button)findViewById(R.id.btn_acceder);
        btnCancelar = (Button)findViewById(R.id.btn_cancelar);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUsuario.getText().toString().equals(
                        getString(R.string.login_valido))&&
                        edtClave.getText().toString().equals(
                                getString(R.string.clave_valida))){
                    startActivity(new Intent(ExampleRelativeActivity.this,
                            ExampleTableActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),
                            R.string.error_autenticar,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_cancelar){
            startActivity(new Intent(
                    ExampleRelativeActivity.this,
                    ExampleFrameActivity.class
            ));
        }
    }
}
