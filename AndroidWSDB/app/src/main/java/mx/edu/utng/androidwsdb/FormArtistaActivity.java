package mx.edu.utng.androidwsdb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by qas on 15/03/16.
 */
public class FormArtistaActivity extends Activity implements
        View.OnClickListener{
    private EditText edtNombre;
    private EditText edtNumExitos;
    private Button btnGuardar;
    private Button btnListar;
    public final static String NAME_SPACE = "http://webservicedb.utng.edu.mx";
    public final static SoapSerializationEnvelope ENVELOPE=
            new SoapSerializationEnvelope(SoapEnvelope.VER11);
    public final static String URL = "http://172.16.2.105:8080/WebServiceDB/services/ArtistasWS";
    private Artista artista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_artista_layout);
        initComponents();
    }
    private void initComponents(){

        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        edtNumExitos = (EditText)findViewById(R.id.edt_numero_exitos);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnListar = (Button)findViewById(R.id.btn_listar);
        btnGuardar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar:
                try{
                    if(getIntent().getExtras().getString("accion").equals("modificar")){
                        TareaWSModificacion modificacion = new TareaWSModificacion();
                        modificacion.execute();
                    }

                }catch (Exception e) {
                    TareaWSInsercion insercion =
                            new TareaWSInsercion();
                    insercion.execute();
                }
                break;
            case R.id.btn_listar:
                startActivity(new Intent(this,
                        ListArtistaActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private class TareaWSInsercion
            extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final  String METHOD_NAME = "insertar";
            final  String SOAP_ACTION = NAME_SPACE +"/"+METHOD_NAME;
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);

            artista = new Artista();
            artista.setProperty(0, 0);
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("artista");
            info.setValue(artista);
            info.setType(Artista.class);

            request.addProperty(info);
            ENVELOPE.setOutputSoapObject(request);
            ENVELOPE.addMapping(NAME_SPACE, "Artista", Artista.class);

            HttpTransportSE trasporte = new HttpTransportSE(URL);
            try{
                trasporte.call(SOAP_ACTION, ENVELOPE);
                SoapPrimitive response = (SoapPrimitive)ENVELOPE.getResponse();
                String strResponse = response.toString();

                if(!strResponse.equals("1")){
                    result = false;
                }

            }catch (Exception e){
                Log.e("Error de Transporte", e.toString());
                result = false;
            }


            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(),
                        "Registro exitoso",
                        Toast.LENGTH_SHORT).show();
               }else{
                Toast.makeText(getApplicationContext(),
                        "Registro fallido",
                        Toast.LENGTH_SHORT).show();
                }
        }
    }

    private class TareaWSModificacion
            extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final  String METHOD_NAME = "modificar";
            final  String SOAP_ACTION = NAME_SPACE +"/"+METHOD_NAME;
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);

            artista = new Artista();
            artista.setProperty(0,
                    getIntent().getExtras().getString("valor0"));
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("artista");
            info.setValue(artista);
            info.setType(Artista.class);

            request.addProperty(info);
            ENVELOPE.setOutputSoapObject(request);
            ENVELOPE.addMapping(NAME_SPACE, "Artista", Artista.class);

            HttpTransportSE trasporte = new HttpTransportSE(URL);
            try{
                trasporte.call(SOAP_ACTION, ENVELOPE);
                SoapPrimitive response = (SoapPrimitive)ENVELOPE.getResponse();
                String strResponse = response.toString();

                if(!strResponse.equals("1")){
                    result = false;
                }

            }catch (Exception e){
                Log.e("Error de Transporte", e.toString());
                result = false;
            }


            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(),
                        "Actualizacion exitosa",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),
                        "Actualización fallida",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getData(){
        artista.setProperty(1, edtNombre.getText().toString());
        artista.setProperty(2, edtNumExitos.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            Bundle bundleArtista = getIntent().getExtras();
            edtNombre.setText(bundleArtista.getString("valor1"));
            edtNumExitos.setText(bundleArtista.getString("valor2"));
        }catch (Exception e){
            Log.e("onResume", e.toString());
        }
    }
}

