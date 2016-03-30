package mx.edu.utng.androidwsdb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by qas on 15/03/16.
 */
public class ListArtistaActivity  extends Activity{
    private ListView lsvArtistas;
    private ArrayList<Artista> artistas =
            new ArrayList<Artista>();
    private Artista artista;
    private int idSeleccionado;
    private int posicionSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lst_artista_layout);
        initComponents();
        TareaWSListado listado = new TareaWSListado();
        listado.execute();
    }
    private void initComponents(){
        lsvArtistas = (ListView)findViewById(R.id.lsv_artistas);
        registerForContextMenu(lsvArtistas);

    }



    private class TareaWSListado
            extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final  String METHOD_NAME = "listar";
            final  String SOAP_ACTION = FormArtistaActivity.NAME_SPACE +"/"+METHOD_NAME;
            artistas.clear();

            SoapObject request = new SoapObject(
                    FormArtistaActivity.NAME_SPACE, METHOD_NAME);

            FormArtistaActivity.ENVELOPE.setOutputSoapObject(request);

            HttpTransportSE trasporte = new HttpTransportSE(FormArtistaActivity.URL);
            try {
                trasporte.call(SOAP_ACTION, FormArtistaActivity.ENVELOPE);

                Vector<SoapObject> response =
                        (Vector<SoapObject>) FormArtistaActivity
                                .ENVELOPE.getResponse();


                if (response != null) {
                    for (SoapObject artistaResponse : response) {
                        artista = new Artista();
                        artista.setProperty(0,
                                Integer.parseInt(
                                        artistaResponse.getProperty(
                                                "idArtista").toString()));
                        artista.setProperty(1,
                                artistaResponse.getProperty(
                                        "nombre").toString());
                        artista.setProperty(2,
                                Integer.parseInt(
                                        artistaResponse.getProperty(
                                                "numeroExitos").toString()));
                        artistas.add(artista);

                    }
                }

            } catch (HttpResponseException e){
                Log.e("HTTP Error", e.toString());
                result = false;
            } catch (IOException e){
                Log.e("IO Error", e.toString());
                result = false;
            } catch (XmlPullParserException e){
                Log.e("XML Error", e.toString());
                result = false;
            }catch (ClassCastException e){
                try{

                    SoapObject artistaResponse = (SoapObject)
                            FormArtistaActivity.ENVELOPE.getResponse();
                    artista = new Artista();
                    artista.setProperty(0,
                            Integer.parseInt(
                                    artistaResponse.getProperty(
                                            "idArtista").toString()));
                    artista.setProperty(1,
                            artistaResponse.getProperty(
                                    "nombre").toString());
                    artista.setProperty(2,
                            Integer.parseInt(
                                    artistaResponse.getProperty(
                                            "numeroExitos").toString()));
                    artistas.add(artista);
                }catch (SoapFault sf){
                    Log.e("Soap Fault", sf.toString());
                    result = false;
                }
            }


            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            final String[] datos = new String[artistas.size()];
            if(result){
                for (int i=0; i<artistas.size();i++){
                    datos[i] = artistas.get(i).getProperty(1)
                            +" - "+artistas.get(i).getProperty(2);
                }
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(
                                ListArtistaActivity.this,
                                android.R.layout.simple_list_item_1,
                                datos);
                lsvArtistas.setAdapter(adapter);

            }else{
                Toast.makeText(getApplicationContext(),
                        "No se encontraron datos",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(
             lsvArtistas.getAdapter().getItem(info.position).toString());
        idSeleccionado = (Integer)
                artistas.get(info.position).getProperty(0);
        posicionSeleccionado = info.position;
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_modificar:
                artista = artistas.get(posicionSeleccionado);
                Bundle bundleArtista = new Bundle();
                for (int i = 0; i <artista.getPropertyCount() ; i++) {
                    bundleArtista.putString("valor"+i,
                            artista.getProperty(i).toString());
                }
                bundleArtista.putString("accion", "modificar");
                startActivity(new Intent(ListArtistaActivity.this,
                        FormArtistaActivity.class)
                        .putExtras(bundleArtista));
                finish();
                break;
            case R.id.itm_eliminar:
                TareaWSBorrado borrado = new TareaWSBorrado();
                borrado.execute();
                break;
            default:
                break;
         }
        return true;
    }

    private class TareaWSBorrado
            extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final  String METHOD_NAME = "eliminar";
            final  String SOAP_ACTION = FormArtistaActivity.NAME_SPACE +"/"+METHOD_NAME;
            SoapObject request = new SoapObject(FormArtistaActivity.NAME_SPACE, METHOD_NAME);

            artista = new Artista();
            artista.setProperty(0, idSeleccionado);

            PropertyInfo info = new PropertyInfo();
            info.setName("artista");
            info.setValue(artista);
            info.setType(Artista.class);

            request.addProperty(info);
            FormArtistaActivity.ENVELOPE.setOutputSoapObject(request);
            FormArtistaActivity.ENVELOPE.addMapping(FormArtistaActivity.NAME_SPACE, "Artista", Artista.class);

            HttpTransportSE trasporte = new HttpTransportSE(FormArtistaActivity.URL);
            try{
                trasporte.call(SOAP_ACTION, FormArtistaActivity.ENVELOPE);
                SoapPrimitive response = (SoapPrimitive)FormArtistaActivity.ENVELOPE.getResponse();
                String strResponse = response.toString();

                if(!strResponse.equals("0")){
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
                        "Borrado exitoso",
                        Toast.LENGTH_SHORT).show();
                 TareaWSListado listado = new TareaWSListado();
                listado.execute();
            }else{
                Toast.makeText(getApplicationContext(),
                        "Borrado fallido",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_regresar:
                startActivity(new Intent(ListArtistaActivity.this,
                        FormArtistaActivity.class));
                finish();
                break;
            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
