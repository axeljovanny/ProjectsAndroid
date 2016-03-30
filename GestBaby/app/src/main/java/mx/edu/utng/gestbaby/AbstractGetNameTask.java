package mx.edu.utng.gestbaby;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by qas on 5/03/16.
 */
public abstract class AbstractGetNameTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "TokenInfoTask";
    protected SplashActivity activity;
    public static String GOOGLE_USER_DATA="No_data";
    protected String scope;
    protected String email;
    protected int requestCode;

    public AbstractGetNameTask(SplashActivity activity, String email, String scope){
        this.scope = scope;
        this.email = email;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.i("AbstractGetNameTask", "Entre a doInBackground");

        try{
            fetchNameFromProfileServer();
        }catch (IOException e){
            onError("El siguiente error ocurrio, favor de probar nuevamente."+e.getMessage(), e);
        }catch (JSONException e){
            onError("Mala respuesta: "+e.getMessage(), e);
        }
        return null;
    }

    protected void onError(String msg, Exception e){
        if(e!=null){
            Log.e(TAG, "Exception: ", e);
        }
    }
    protected abstract String fetchToken() throws IOException;

    private void fetchNameFromProfileServer() throws  IOException, JSONException{
        Log.i("AbstractGetNameTask", "Entre fetchNameFromProfileServer");
        String token = fetchToken();

        URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+token);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        int sc = connection.getResponseCode();
        if(sc==200){
            InputStream inputStream = connection.getInputStream();
            GOOGLE_USER_DATA = readResponse(inputStream);
            Log.e("GOOGLE_USER_DATA", GOOGLE_USER_DATA);
            inputStream.close();

            Intent intent = new Intent(activity, HomeActivity.class);
            intent.putExtra("email_id",email);
            activity.startActivity(intent);
            activity.finish();
            return;
         }else if(sc==401){
            Log.e("401", GOOGLE_USER_DATA);
            GoogleAuthUtil.invalidateToken(activity, token);
            onError("Error de autenticación de servidor, favor de intentar nuevamente", null);
            return;
        }else{
            Log.e("OTHER CODE", GOOGLE_USER_DATA);
            onError("Servidor retorno el siguiente error de codigo:"+sc, null);
            return;
        }
    }

    private static String readResponse(InputStream inputStream) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        int len = 0;
        while ((len=inputStream.read(data, 0, data.length))>=0){
            bos.write(data, 0, len);
        }
        return  new String(bos.toByteArray(), "UTF-8");
    }
}
