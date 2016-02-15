package mx.edu.utng.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by qas on 25/01/16.
 */
public class FrameLayoutActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout_example);
    }


    public void irOtraActividad(View view) {
        Intent intento = new Intent(
                this,LinearLayoutActivity.class);
        startActivity(intento);
    }
}

