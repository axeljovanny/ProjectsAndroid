package mx.edu.uttt.feed2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Feed2 extends AppCompatActivity {

    private Button btnCargar;
    private TextView txtResultado;

    private List<Noticia> noticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2);

        btnCargar = (Button)findViewById(R.id.btn_cargar);
        txtResultado = (TextView)findViewById(R.id.txv_resultado);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Sin Tarea As�ncrona
                //RssParserSax saxparser =
                //		new RssParserSax("http://212.170.237.10/rss/rss.aspx");

                //noticias = saxparser.parse();

                //Tratamos la lista de noticias
                //Por ejemplo: escribimos los t�tulos en pantalla
                //txtResultado.setText("");
                //for(int i=0; i<noticias.size(); i++)
                //{
                //	txtResultado.setText(
                //		txtResultado.getText().toString() +
                //			System.getProperty("line.separator") +
                //			noticias.get(i).getTitulo());
                //}

                //Con Tarea As�ncrona
                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://feeds.feedburner.com/cnnexpansion/portada?format=xml");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Tarea As�ncrona para cargar un XML en segundo plano
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserSax saxparser =
                    new RssParserSax(params[0]);

            noticias = saxparser.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {

            //Tratamos la lista de noticias
            //Por ejemplo: escribimos los t�tulos en pantalla
            txtResultado.setText("");
            for(int i=0; i<noticias.size(); i++)
            {
                txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator") +
                                noticias.get(i).getTitulo());
            }
        }
    }
}