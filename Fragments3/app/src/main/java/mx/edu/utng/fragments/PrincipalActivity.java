package mx.edu.utng.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


/**
 * Created by qas on 1/03/16.
 */
public class PrincipalActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new SelectorAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PrincipalActivity.this,
                        "Seleccionado el elemento: "+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
