package mx.edu.utng.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by qas on 26/01/16.
 */
public class ExampleFrameActivity extends Activity{
    private ImageView imvMegan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_frame_layout);
        imvMegan = (ImageView)findViewById(R.id.imv_megan);
        imvMegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExampleFrameActivity.this,
                        ExampleLinearActivity.class);
                startActivity(intent);
            }
        });
    }

}
