package mx.edu.utng.memento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements {

    private RadioGroup rgpColores;
    private RadioButton rbtRojo;
    private RadioButton rbtVerde;
    private RadioButton rbtAzul;
    private RadioButton rbtAmarillo;
    private RadioButton rbtNegro;
    private Button btnGuardar;
    private Button btnDeshacer;
    private Button btnRehacer;
    private LinearLayout layPrincipal;
    private Lienzo lienzo;
    private Automovil automovil;
    private CareTaker careTaker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgpColores = (RadioGroup) findViewById(R.id.rgp_colores);
        rbtRojo = (RadioButton)findViewById(R.id.rbt_rojo);
        rbtAmarillo = (RadioButton)findViewById(R.id.rbt_amarillo);
        rbtVerde = (RadioButton)findViewById(R.id.rbt_verde);
        rbtAzul = (RadioButton)findViewById(R.id.rbt_azul);
        rbtNegro = (RadioButton)findViewById(R.id.rbt_negro);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnDeshacer = (Button)findViewById(R.id.btn_deshacer);
        btnRehacer = (Button)findViewById(R.id.btn_rehacer);
        layPrincipal = (LinearLayout)findViewById(R.id.lay_principal);
        automovil = new Automovil();
        lienzo = new Lienzo(this, automovil);
        layPrincipal.addView(lienzo);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (rgpColores.getCheckedRadioButtonId()){
                   case R.id.rbt_rojo:

                       break;
                   case R.id.rbt_verde:
                       break;
                   case R.id.rbt_azul:
                       break;
                   case R.id.rbt_amarillo:
                       break;
                   case R.id.rbt_negro:
                       break;
                   default:
                       break;
               }
            }
        });
    }

    public void setMemento(int color){
        automovil.setColor(color);
        lienzo.setAutomovil(automovil);
        lienzo.invalidate();
        careTaker.addMemento(automovil.guardarMemento());
        
    }
}
