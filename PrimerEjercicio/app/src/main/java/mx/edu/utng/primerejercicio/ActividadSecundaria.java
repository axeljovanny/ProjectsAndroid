package mx.edu.utng.primerejercicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by qas on 13/02/16.
 */
public class ActividadSecundaria extends Activity implements View.OnClickListener{
    private Button btnIrPrimero;
    private Button btnIrTercero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_secundario);
        btnIrPrimero = (Button)findViewById(R.id.btn_ir_primero);
        btnIrTercero = (Button)findViewById(R.id.btn_ir_tercero);
        btnIrPrimero.setOnClickListener(this);
        btnIrTercero.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_ir_primero:
               startActivity(new Intent(ActividadSecundaria.this,
                       ActividadPrincipal.class));
               break;
           case R.id.btn_ir_tercero:
               startActivity(new Intent(ActividadSecundaria.this,
                       ActividadTerciaria.class));
               break;

       }
    }
}
