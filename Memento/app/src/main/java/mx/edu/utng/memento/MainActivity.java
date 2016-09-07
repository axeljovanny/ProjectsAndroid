package mx.edu.utng.memento;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity{

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
    private int ultimo = 0;
    private int actual = 0;

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
        careTaker = new CareTaker();
        automovil = new Automovil();
        lienzo = new Lienzo(this, automovil);
        layPrincipal.addView(lienzo);
        btnDeshacer.setEnabled(false);
        btnRehacer.setEnabled(false);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (rgpColores.getCheckedRadioButtonId()){
                   case R.id.rbt_rojo:
                       setMemento(Color.RED);
                       break;
                   case R.id.rbt_verde:
                       setMemento(Color.GREEN);
                       break;
                   case R.id.rbt_azul:
                       setMemento(Color.BLUE);
                       break;
                   case R.id.rbt_amarillo:
                       setMemento(Color.YELLOW);
                       break;
                   case R.id.rbt_negro:
                       setMemento(Color.BLACK);
                       break;
                   default:
                       break;
               }
               validaBoton();
            }

        });

        btnDeshacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual>0)
                    --actual;
                automovil.restaurarMemento(careTaker.getMemento(actual));
                lienzo.setAutomovil(automovil);
                lienzo.invalidate();
            }
        });

        btnRehacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual<ultimo)
                    ++actual;
                automovil.restaurarMemento(careTaker.getMemento(actual));
                lienzo.setAutomovil(automovil);
                lienzo.invalidate();
            }
        });
    }

   
    public void setMemento(int color) {
        automovil.setColor(color);
        lienzo.setAutomovil(automovil);
        lienzo.invalidate();
        careTaker.addMemento(automovil.guardarMemento());
        ultimo = careTaker.getMementos().size()-1;
        actual = ultimo;

    }

    private void validaBoton(){
        if(actual>0){
            btnDeshacer.setEnabled(true);
        }else{
            btnDeshacer.setEnabled(false);
        }
        if(ultimo>actual){
            btnRehacer.setEnabled(true);
        }else{
            btnRehacer.setEnabled(false);
        }

    }

}
