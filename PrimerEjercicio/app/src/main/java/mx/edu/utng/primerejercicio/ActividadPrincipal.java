package mx.edu.utng.primerejercicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by qas on 13/02/16.
 */
public class ActividadPrincipal extends Activity {
    private Button btnIrSegundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        btnIrSegundo = (Button)findViewById(R.id.btn_ir_segundo);
        btnIrSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActividadPrincipal.this,
                        ActividadSecundaria.class));
            }
        });

    }
}
