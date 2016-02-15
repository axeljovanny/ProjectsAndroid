package mx.edu.utng.listcustomized;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by qas on 13/02/16.
 */
public class ActividadMover extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mover);
        TextView txvNombre = (TextView)findViewById(R.id.txv_nombre);
        txvNombre.setText(getIntent().getStringExtra("nombre"));
    }
}
