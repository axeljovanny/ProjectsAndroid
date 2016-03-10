package mx.edu.utng.clientewsdb;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;
import java.util.Vector;

/**
 * Created by qas on 8/03/16.
 */
public class ListBooksActivity extends Activity {
    private List<Libro> books;
    private int idSelected;
    private int positionSelected;
    private ListView lsvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_books_layout);
        initComponents();
    }

    private void initComponents() {
        lsvBooks = (ListView) findViewById(R.id.lsv_books);
        TareaWSListado tareaWSListado = new TareaWSListado();
        tareaWSListado.execute();
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
            books = null;
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
                        Libro libro = new Libro();
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
                        books.add(libro);
                    }
                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
                result = false;
            }
            try {

                SoapObject soapObject =
                        (SoapObject) FormBookActivity.envelope.getResponse();
                Libro libro = new Libro();
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
                books.add(libro);
            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
            } catch (Exception e) {
                Log.e("Error", e.toString());
                result = false;
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
}

