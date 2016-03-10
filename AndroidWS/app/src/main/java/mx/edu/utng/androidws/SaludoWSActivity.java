package mx.edu.utng.androidws;

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
 * Created by qas on 8/03/16.
 */
public class SaludoWSActivity extends Activity implements View.OnClickListener{
    private EditText edtNombre;
    private Button btnConsume;
    private EditText edtNumero1;
    private EditText edtNumero2;
    private Button btnConsumePlus;

    private final String NAME_SPACE =
            "http://webservice1.utng.edu.mx";
    private final SoapSerializationEnvelope envelope =
            new SoapSerializationEnvelope(SoapEnvelope.VER11);
    private final String URL =
            "http://172.16.2.33:8080/WebService1/services/HolaMundo";
    private  String METHOD_NAME = "saludar";
    private  String SOAP_ACTION = NAME_SPACE+"/"+METHOD_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saludo_ws_layout);
        initComponents();
    }

    private void initComponents(){
        edtNombre = (EditText)findViewById(R.id.edt_name);
        btnConsume = (Button)findViewById(R.id.btn_consume);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        btnConsume.setOnClickListener(this);

        edtNumero1 = (EditText)findViewById(R.id.edt_number_1);
        edtNumero2 = (EditText)findViewById(R.id.edt_number_2);
        btnConsumePlus = (Button)findViewById(R.id.btn_consume_plus);
        btnConsumePlus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SoapObject request = null;
        HttpTransportSE transporte = null;
        SoapPrimitive response = null;
        String strResponse ="";
        switch (v.getId()) {
            case R.id.btn_consume:
                METHOD_NAME = "saludar";
                request = new SoapObject(NAME_SPACE, METHOD_NAME);
                envelope.setOutputSoapObject(request);
                transporte = new HttpTransportSE(URL);
                request.addProperty("nombre", edtNombre.getText().toString());
                try {
                   transporte.call(SOAP_ACTION, envelope);
                    response = (SoapPrimitive) envelope.getResponse();
                    strResponse = response.toString();
                    Toast.makeText(this, strResponse, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(getString(R.string.error_ws), e.toString());
                }
                break;
            case R.id.btn_consume_plus:
                METHOD_NAME = "sumar";
                request = new SoapObject(NAME_SPACE, METHOD_NAME);
                envelope.setOutputSoapObject(request);
                request.addProperty(getString(R.string.number1), edtNumero1.getText().toString());
                request.addProperty(getString(R.string.number2), edtNumero2.getText().toString());

                transporte = new HttpTransportSE(URL);
                try {
                    transporte.call(SOAP_ACTION, envelope);
                    response =
                            (SoapPrimitive) envelope.getResponse();
                    strResponse = response.toString();
                    Toast.makeText(this, strResponse, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(getString(R.string.error_ws), e.toString());
                }
                break;
            default:
                break;
        }
    }
}
