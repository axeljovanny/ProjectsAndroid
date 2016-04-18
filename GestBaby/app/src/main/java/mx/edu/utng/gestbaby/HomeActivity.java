package mx.edu.utng.gestbaby;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by qas on 5/03/16.
 */
public class HomeActivity extends Activity {
    ImageView imvProfile;
    TextView txvName, txvEmail, txvGender, txtLink;
    String strName, strEmail, strGender, strLink, strUserImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        initComponents();
    }

    private void initComponents() {
        Log.i("HomeActivity", "Entre a initComponents");

        imvProfile = (ImageView) findViewById(R.id.imv_logo);
        txvName = (TextView) findViewById(R.id.txv_name_value);
        txvEmail = (TextView) findViewById(R.id.txv_email_value);
        txvGender = (TextView) findViewById(R.id.txv_gender_value);
        txtLink = (TextView) findViewById(R.id.txv_link_value);

        Intent intent = getIntent();
        strEmail = intent.getStringExtra("email_id");
        txvEmail.setText(strEmail);
        try {
            JSONObject profileData = new JSONObject(
                    AbstractGetNameTask.GOOGLE_USER_DATA);
            Log.i("JSON", profileData.toString());
            if (profileData.has("picture")) {
                strUserImageUrl = profileData.getString("picture");
                new GetImageFromUrl().execute(strUserImageUrl);
            }
            if (profileData.has("name")) {
                strName = profileData.getString("name");
                txvName.setText(strName);
            }
            if (profileData.has("gender")) {
                strGender = profileData.getString("gender");
                txvGender.setText(strGender);
            }
            if (profileData.has("link")) {
                strLink = profileData.getString("link");
                txtLink.setText(strLink);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            Log.i("GetImageFromUrl", "Entre a doInBackground");

            for (String url : urls) {
                bitmap = downloadImage(url);
            }
            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imvProfile.setImageBitmap(bitmap);
        }

        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, options);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        private InputStream getHttpConnection(String strUrl) throws IOException {
            InputStream stream = null;
            URL url = new URL(strUrl);
            URLConnection connection = url.openConnection();
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stream;
        }
    }
}
