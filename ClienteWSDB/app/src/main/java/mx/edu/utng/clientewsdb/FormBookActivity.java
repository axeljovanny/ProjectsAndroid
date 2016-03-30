package mx.edu.utng.clientewsdb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by qas on 8/03/16.
 */
public class FormBookActivity extends Activity
        implements View.OnClickListener{
    private EditText edtTitle;
    private EditText edtAuthor;
    private EditText edtPrice;
    private EditText edtEditorial;
    private RadioButton rbtComedia;
    private RadioButton rbtDrama;
    private RadioButton rbtNovela;
    private Button btnSave;
    private Button btnList;
    private RadioGroup rgpCategories;
    private Libro libro =  null;
    public static final String NAME_SPACE = "http://ws.utng.edu.mx";
    public static SoapSerializationEnvelope envelope =
            new SoapSerializationEnvelope(SoapEnvelope.VER11);
    public final static String URL = "http://172.16.2.104:8080/WS/services/LibrosDB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_book_layout);
        initComponents();
    }
    private void initComponents(){
       edtTitle = (EditText)findViewById(R.id.edt_title);
       edtAuthor = (EditText)findViewById(R.id.edt_author);
       edtEditorial = (EditText)findViewById(R.id.edt_editorial);
       edtPrice = (EditText)findViewById(R.id.edt_price);
       rbtComedia = (RadioButton)findViewById(R.id.rbt_comedia);
       rbtDrama = (RadioButton)findViewById(R.id.rbt_drama);
       rbtNovela = (RadioButton)findViewById(R.id.rbt_novela);
       rgpCategories = (RadioGroup)findViewById(R.id.rgp_categories);
       btnSave = (Button)findViewById(R.id.btn_save);
       btnList = (Button)findViewById(R.id.btn_list);
       btnSave.setOnClickListener(this);
       btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                try {
                    if(getIntent().getExtras()
                      .getString("accion")
                            .equals("modificar")) {
                        TareaWSActualizacion actualizacion =
                                new TareaWSActualizacion();
                        actualizacion.execute();
                    }

                }catch (Exception e){
                    TareaWSInsercion insercion = new TareaWSInsercion();
                    insercion.execute();
                }
                break;
            case R.id.btn_list:
                startActivity(new Intent(this,
                        ListBooksActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                break;
            default:
                break;
        }
    }

    private void getData(){
       libro.setProperty(1, edtTitle.getText().toString());
       libro.setProperty(2, edtAuthor.getText().toString());
       libro.setProperty(3, edtEditorial.getText().toString());
       libro.setProperty(4, Float.parseFloat(
               edtPrice.getText().toString()));
        int selecionado = 0;
        switch (rgpCategories.getCheckedRadioButtonId()){
            case R.id.rbt_comedia:
                selecionado = 1;
                break;
            case R.id.rbt_drama:
                selecionado = 2;
                break;
            case R.id.rbt_novela:
                selecionado = 3;
                break;
        }
        libro.setProperty(5, selecionado);
    }

    private class TareaWSInsercion extends
            AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "insertar";
            final String SOAP_ACTION = NAME_SPACE+"/"+METHOD_NAME;
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
            libro = new Libro();
            libro.setProperty(0, 0);
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("libro");
            info.setValue(libro);
            info.setType(Libro.class);

            request.addProperty(info);

            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAME_SPACE, "Libro", Libro.class);

            MarshalFloat marshalFloat = new MarshalFloat();
            marshalFloat.register(envelope);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                String strResponse = response.toString();
                if(!strResponse.equals("1")){
                    result = false;
                }

            }catch (Exception e){
                Log.e("Error", e.toString());
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(),
                        getString(R.string.record_success),
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),
                        getString(R.string.record_failed),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class TareaWSActualizacion extends
            AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "modificar";
            final String SOAP_ACTION = NAME_SPACE+"/"+METHOD_NAME;
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
            libro = new Libro();
            libro.setProperty(0, getIntent()
                    .getExtras().getString("valor0"));
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("libro");
            info.setValue(libro);
            info.setType(Libro.class);

            request.addProperty(info);

            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAME_SPACE, "Libro", Libro.class);

            MarshalFloat marshalFloat = new MarshalFloat();
            marshalFloat.register(envelope);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                String strResponse = response.toString();
                if(!strResponse.equals("1")){
                    result = false;
                }

            }catch (Exception e){
                Log.e("Error", e.toString());
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(),
                        getResources().getString(
                                R.string.success_update),
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),
                        getResources().getString(
                                R.string.update_failed),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Bundle libroModificar = this.getIntent().getExtras();
        try{
            edtTitle.setText(
                    libroModificar.getString("valor1"));
            edtAuthor.setText(
                    libroModificar.getString("valor2"));
            edtPrice.setText(
                    libroModificar.getString("valor4"));
            edtEditorial.setText(
                    libroModificar.getString("valor3"));
            switch (libroModificar.getString("valor5")){
                case "1":
                    rbtComedia.setChecked(true);
                    break;
                case "2":
                    rbtDrama.setChecked(true);
                    break;
                case "3":
                    rbtNovela.setChecked(true);
                    break;
                default:
                    rgpCategories.clearCheck();
                    break;
            }


        }catch (Exception e){
            Log.e("Error al cargar", e.toString());
        }
    }
}
