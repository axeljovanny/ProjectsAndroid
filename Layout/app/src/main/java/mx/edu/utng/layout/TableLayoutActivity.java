package mx.edu.utng.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by qas on 25/01/16.
 */
public class TableLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout_example);
    }

    public void irCuartaActividad(View view){
        startActivity(new Intent(this, RelativeLayoutActivity.class));
    }
}
