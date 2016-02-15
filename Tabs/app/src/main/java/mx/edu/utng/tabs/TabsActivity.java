package mx.edu.utng.tabs;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

/**
 * Created by qas on 10/02/16.
 */
public class TabsActivity extends Activity {

    private ListView lsvProfesores;
    private ListView lsvEstudiantes;
    private ListView lsvDirectivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);
        initComponents();
    }

    private void initComponents(){
        //Resources resources = getResources();

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Profesores");
        spec.setContent(R.id.tabProfesores);
        spec.setIndicator("Profesores");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Estudiantes");
        spec.setContent(R.id.tabEstudiantes);
        spec.setIndicator("Estudiantes");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Directivos");
        spec.setContent(R.id.tabDirectivos);
        spec.setIndicator("Directivos");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        lsvProfesores = (ListView)findViewById(R.id.lsv_profesores);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.profesores,
                android.R.layout.simple_list_item_1);
        lsvProfesores.setAdapter(adapter);
    }


}
