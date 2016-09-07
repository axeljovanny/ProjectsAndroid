package mx.edu.uttt.snackbar;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.BitSet;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private Button btnSimple;
    private Button btnCustom;
    private Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        btnSimple = (Button)findViewById(R.id.btn_simple);
        btnCustom = (Button)findViewById(R.id.btn_custom);
        btnAction = (Button)findViewById(R.id.btn_action);

        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(coordinatorLayout,
                        "Hola soy un Snackbar", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

       btnAction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Snackbar snackbar = Snackbar.make(coordinatorLayout, "El mensaje es borrado",
                       Snackbar.LENGTH_LONG).setAction("Deshacer",
                       new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Snackbar.make(coordinatorLayout, "Mensaje recuperado.",
                                       Snackbar.LENGTH_SHORT).show();
                           }
                       });
               snackbar.show();
           }
       });

       btnCustom.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Snackbar snackbar = Snackbar.make(coordinatorLayout, "No hay conexion de internet",
                       Snackbar.LENGTH_INDEFINITE).setAction("Reintentar",
                       new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Snackbar.make(coordinatorLayout, "Conexion exitosa.",
                                       Snackbar.LENGTH_SHORT).show();
                           }
                       });
               snackbar.setActionTextColor(Color.RED);
               View view = snackbar.getView();

               TextView textView = (TextView)
                       view.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
               snackbar.show();
           }
       });
    }
}
