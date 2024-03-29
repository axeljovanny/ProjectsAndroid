package mx.edu.utng.gestbaby;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import mx.edu.utng.gestbaby.database.Main_Screen;
import mx.edu.utng.gestbaby.gallery_one.GalleryOneFragment;
import mx.edu.utng.gestbaby.gallery_two.GalleryTwoFragment;

public class MainHomeActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();


        if (id == R.id.nav_gallery_one) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new GalleryOneFragment()).commit();
        } else if (id == R.id.nav_gallery_two) {
            manager.beginTransaction().replace(R.id.content_fragment, new GalleryTwoFragment()).commit();
        } else if (id == R.id.nav_feed) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new SplashFragment()).commit();
        } else if (id == R.id.nav_google) {
            startActivity(new Intent(MainHomeActivity.this, SplashActivity.class));
        } else if (id == R.id.nav_registro) {
            startActivity(new Intent(MainHomeActivity.this, Main_Screen.class));
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
