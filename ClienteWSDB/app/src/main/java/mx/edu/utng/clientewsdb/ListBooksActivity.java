package mx.edu.utng.clientewsdb;

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
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by qas on 8/03/16.
 */
public class ListBooksActivity extends Activity {
    private List<Libro> books = new ArrayList<Libro>();
    private int idSelected;
    private int positionSelected;
    private ListView lsvBooks;
    private Libro libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_books_layout);
        initComponents();
    }

    private void initComponents() {
        lsvBooks = (ListView) findViewById(R.id.lsv_books);
        registerForContextMenu(lsvBooks);

        TareaWSListado tareaWSListado = new TareaWSListado();
        tareaWSListado.execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(lsvBooks
                .getAdapter()
                .getItem(info.position).toString());
        idSelected = (Integer)books
                .get(info.position)
                .getProperty(0);
        positionSelected = info.position;

        inflater.inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_modificar:
                libro = books.get(positionSelected);
                Bundle bundleLibro = new Bundle();
                for(int i=0; i<libro.getPropertyCount();i++){
                    bundleLibro.putString("valor"+i,
                            libro.getProperty(i).toString());
                }
               bundleLibro.putString("accion", "modificar");
                startActivity(new Intent(
                        ListBooksActivity.this,
                        FormBookActivity.class)
                        .putExtras(bundleLibro));
                finish();
                break;
            case R.id.itm_eliminar:
                    TareaWSEliminar eliminar =
                            new TareaWSEliminar();
                    eliminar.execute();
                    TareaWSListado listado =
                            new TareaWSListado();
                    listado.execute();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =
                new MenuInflater(this);
        inflater.inflate(R.menu.main_menu,
                menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId()==R.id.itm_back){
            startActivity(new Intent(this,
                    FormBookActivity.class));
            finish();
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private class TareaWSListado extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "listar";
            final String SOAP_ACTION =
                    FormBookActivity.NAME_SPACE + "/" + METHOD_NAME;
            SoapObject request = new SoapObject(
                    FormBookActivity.NAME_SPACE, METHOD_NAME);
            books.clear();
            FormBookActivity.envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(
                    FormBookActivity.URL);
            try {
                transporte.call(SOAP_ACTION,
                        FormBookActivity.envelope);
                Vector<SoapObject> vectorResponse =
                        (Vector<SoapObject>)
                                FormBookActivity.envelope.getResponse();

                if (vectorResponse != null) {
                    for (SoapObject soapObject : vectorResponse) {
                        setDataBean(soapObject);
                    }
                }

            } catch (XmlPullParserException e) {
                Log.e("Error XMLPullParser", e.toString());
                result = false;
            } catch (HttpResponseException e) {
                Log.e("Error HTTP", e.toString());
                result = false;
            } catch (IOException e) {
                Log.e("Error IO", e.toString());
                result = false;
            } catch (ClassCastException cce){
                try {

                    SoapObject soapObject =
                            (SoapObject) FormBookActivity.envelope.getResponse();
                    setDataBean(soapObject);

                } catch (SoapFault soapFault) {
                    soapFault.printStackTrace();
                }
            }


            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                final String[] datos = new String[books.size()];
                for (int i = 0; i < books.size(); i++) {
                    datos[i] = books.get(i).getProperty(0) + " - "
                            + books.get(i).getProperty(1);
                }

                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                        ListBooksActivity.this,
                        android.R.layout.simple_list_item_1, datos);
                lsvBooks.setAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "No se encontraron datos.",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class TareaWSEliminar extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "eliminar";
            final String SOAP_ACTION =
                    FormBookActivity.NAME_SPACE + "/" + METHOD_NAME;
            SoapObject request = new SoapObject(
                    FormBookActivity.NAME_SPACE, METHOD_NAME);

            libro = books.get(positionSelected);
            PropertyInfo info = new PropertyInfo();
            info.setName("libro");
            info.setValue(libro);
            info.setType(Libro.class);

            request.addProperty(info);



            FormBookActivity.envelope
                    .setOutputSoapObject(request);

            FormBookActivity.envelope.addMapping(
                    FormBookActivity.NAME_SPACE,
                    "Libro", Libro.class);

            MarshalFloat marshalFloat = new MarshalFloat();
            marshalFloat.register(FormBookActivity.envelope);

            HttpTransportSE transporte = new HttpTransportSE(
                    FormBookActivity.URL);
            try {
                transporte.call(SOAP_ACTION,
                        FormBookActivity.envelope);
               SoapPrimitive response =(SoapPrimitive)
                       FormBookActivity
                               .envelope.getResponse();
                String strResponse = response.toString();
                if (strResponse.equals("0")) {
                    result = false;
                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(
                        getApplicationContext(), "Eliminado exitoso",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No se elimino el registro",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setDataBean(SoapObject soapObject){
        libro = new Libro();
        libro.setProperty(0, Integer.parseInt(
                soapObject.getProperty("idLibro").toString()));
        libro.setProperty(1,
                soapObject.getProperty("titulo").toString());
        libro.setProperty(2,
                soapObject.getProperty("autor").toString());
        libro.setProperty(3,
                soapObject.getProperty("editorial").toString());
        libro.setProperty(4,
                Float.parseFloat(
                        soapObject.getProperty("precio").toString()));
        libro.setProperty(5, Integer.parseInt(
                soapObject.getProperty("categoria").toString()));
        System.out.println(libro.toString());
        books.add(libro);
    }
}

