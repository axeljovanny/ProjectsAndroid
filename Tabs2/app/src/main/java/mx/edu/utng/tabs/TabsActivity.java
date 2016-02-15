package mx.edu.utng.tabs;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Created by qas on 11/02/16.
 */
public class TabsActivity extends Activity {

    private ListView lsvVideos;
    private ListView lsvAudios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);
        initComponents();
    }

    private void initComponents(){

        TabHost tabHost  = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        Resources resources = getResources();

        TabHost.TabSpec spec =tabHost.newTabSpec("tabVideos");
        spec.setContent(R.id.tab_videos);
        spec.setIndicator("Videos",
                resources.getDrawable(
                        android.R.drawable.ic_media_play));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tabAudios");
        spec.setContent(R.id.tab_audios);
        spec.setIndicator("Audios", resources.getDrawable(
                android.R.drawable.ic_btn_speak_now));
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);

        lsvVideos = (ListView)findViewById(R.id.lsv_videos);
        lsvAudios = (ListView)findViewById(R.id.lsv_audios);

        ArrayAdapter videosAdapter =
                ArrayAdapter.createFromResource(
                this, R.array.videos,
                 android.R.layout.simple_list_item_1);

        ArrayAdapter audiosAdapter =
                ArrayAdapter.createFromResource(
                        this, R.array.audios,
                        android.R.layout.simple_list_item_1);

        lsvVideos.setAdapter(videosAdapter);
        lsvAudios.setAdapter(audiosAdapter);

        tabHost.setOnTabChangedListener(
                new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(),
                        "Pestaña pulsada: "+tabId,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
