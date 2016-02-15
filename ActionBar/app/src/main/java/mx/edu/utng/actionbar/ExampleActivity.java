package mx.edu.utng.actionbar;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by qas on 11/02/16.
 */
public class ExampleActivity extends AppCompatActivity{

    private String mensaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnu_agregar:
                mensaje = "Clic en agregar";
             break;
            case R.id.mnu_configurar:
                mensaje = "Clic en configurar";
                break;
            case R.id.mnu_eliminar:
                mensaje = "Clic en eliminar";
                break;
            case R.id.mnu_guardar:
                mensaje = "Clic en guardar";
                break;
            default:
                break;
        }
        Toast.makeText(getApplicationContext(), mensaje,
                Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
