package mx.edu.utng.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity {

    private EditText edtNumero;
    private EditText edtTitular;
    private EditText edtVencimiento;
    private Button btnClonar;
    private GridView grvTarjeta;

    private ArrayList<TarjetaCredito> tarjetas;
    private TarjetaCredito tarjeta;
    private TarjetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumero = (EditText)findViewById(R.id.edt_numero);
        edtTitular = (EditText)findViewById(R.id.edt_titular);
        edtVencimiento = (EditText)findViewById(R.id.edt_vencimiento);
        btnClonar = (Button)findViewById(R.id.btn_clonar);
        grvTarjeta = (GridView) findViewById(R.id.grv_tarjetas);

        tarjetas = new ArrayList<TarjetaCredito>();
        tarjeta = new TarjetaCredito();

        btnClonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarjeta.setNumero(
                        Integer.parseInt(
                                edtNumero.getText().toString()));
                tarjeta.setTitular(edtTitular.getText().toString());
               try {
                   tarjeta.setFechaVencimiento(
                           new SimpleDateFormat().parse(
                                   edtVencimiento.getText().toString()));
               }catch(ParseException e){
                   tarjeta.setFechaVencimiento(new Date());
               }

                TarjetaCredito clon = (TarjetaCredito)tarjeta.clonar();
                tarjetas.add(clon);
                adapter = new TarjetaAdapter(
                        MainActivity.this, tarjetas);
                grvTarjeta.setAdapter(adapter);

            }
        });


    }
}
