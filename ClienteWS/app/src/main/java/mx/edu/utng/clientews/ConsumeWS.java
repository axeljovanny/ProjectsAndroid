package mx.edu.utng.clientews;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by qas on 7/03/16.
 */
public class ConsumeWS extends Activity{
    private EditText edtNombre;
    private Button btnConsumir;
    private final String NAME_SPACE = "http://ws.utng.edu.mx";
    private final SoapSerializationEnvelope envelope =
            new SoapSerializationEnvelope(SoapEnvelope.VER11);
    private final String URL =
            "http://172.16.2.33:8080/WS/services/HolaMundo";
    private final String METHOD_NAME = "saludo";
    private final String SOAP_ACTION = NAME_SPACE+"/"+METHOD_NAME;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consume_layout);
        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        btnConsumir = (Button)findViewById(R.id.btn_consumir);

        StrictMode.ThreadPolicy policy =
          new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnConsumir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoapObject peticion = new SoapObject(NAME_SPACE,METHOD_NAME);
                envelope.setOutputSoapObject(peticion);
                peticion.addProperty("nombre",
                        edtNombre.getText().toString());
                HttpTransportSE transporte = new HttpTransportSE(URL);
                try{
                    transporte.call(SOAP_ACTION, envelope);
                    SoapPrimitive respuesta =
                            (SoapPrimitive) envelope.getResponse();
                    String resultado = respuesta.toString();
                    Toast.makeText(ConsumeWS.this, resultado,
                            Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("Error Webservice",e.toString());
                }
            }
        });
    }
}
