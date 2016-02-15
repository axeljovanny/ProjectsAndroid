package mx.edu.utng.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by qas on 25/01/16.
 */
public class RelativeLayoutActivity extends Activity{
    private EditText edtUsuario;
    private EditText edtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout_example);
        initComponents();
    }

    private void initComponents(){
        edtUsuario = (EditText)findViewById(R.id.edt_usuario);
        edtClave = (EditText)findViewById(R.id.edt_clave);

    }

    public void autenticar(View view) {
        if(edtUsuario.getText().toString().equals("utng")&&
                edtClave.getText().toString().equals("123")) {
            startActivity(new Intent(this,
                    EntradaActivity.class));
        }else{
            Toast.makeText(this, "Usuario y/o clave incorrectos",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view) {
        startActivity(new Intent(this,
                FrameLayoutActivity.class));
    }
}
