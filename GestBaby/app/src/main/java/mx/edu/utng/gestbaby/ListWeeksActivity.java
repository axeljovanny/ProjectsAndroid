package mx.edu.utng.gestbaby;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import mx.edu.utng.gestbaby.image.ImageLoader;

/**
 * Created by qas on 9/03/16.
 */
public class ListWeeksActivity extends Activity{
    private ListView lsvWeeks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_weeks_layout);

    }

    private void initComponents(){
        lsvWeeks = (ListView)findViewById(R.id.lsv_weeks);
    }



}
