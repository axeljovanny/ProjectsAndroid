package mx.edu.utng.xmlreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import mx.edu.utng.xmlreader.parser.DOMParser;
import mx.edu.utng.xmlreader.parser.RSSFeed;

/**
 * Created by qas on 15/03/16.
 */
public class SplashActivity extends Activity {
    private final String RSSFEEDURL =
       "http://archivo.eluniversal.com.mx/rss/universalmxm.xml";
    RSSFeed feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(
                        Context.CONNECTIVITY_SERVICE
                );

        if(connectivityManager.getActiveNetworkInfo()==null
                && !connectivityManager.getActiveNetworkInfo()
                .isAvailable()
                && !connectivityManager.getActiveNetworkInfo()
                .isConnected()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    "No hay servidor activo, \nFavor de checar la conectividad.")
            .setTitle("Lector de Feed")
            .setCancelable(false)
                    .setPositiveButton("Salir",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else{
            new AsyncLoadXMLFeed().execute();
        }
    }

    private class AsyncLoadXMLFeed
            extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            //Obtenemos el feed
            DOMParser parser = new DOMParser();
            feed = parser.parseXml(RSSFEEDURL);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Bundle bundle = new Bundle();
            bundle.putSerializable("feed", feed);

            startActivity(new Intent(SplashActivity.this,
                    ListActivity.class).putExtras(bundle));
            finish();

        }
    }
}
