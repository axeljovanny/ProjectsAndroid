package mx.edu.utng.maestria1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by qas on 6/02/16.
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);
        Bundle bundle = getIntent().getExtras();
        TextView txv_saludo = (TextView)findViewById(R.id.txv_saludo);
        txv_saludo.setText("Saludo "+bundle.getString("nombre"));

    }
}
