package mx.edu.utng.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by qas on 26/01/16.
 */
public class ExampleLinearActivity extends Activity {
    private Button btnMas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_linear_layout);
        btnMas = (Button)findViewById(R.id.btn_mas);
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ExampleLinearActivity.this,
                        ExampleRelativeActivity.class
                ));
            }
        });
    }
}
