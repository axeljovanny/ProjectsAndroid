package mx.edu.utng.gestbaby;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.auth.GoogleAuthUtil;

/**
 * Created by qas on 5/03/16.
 */
public class SplashActivity extends Activity {
    Context context = SplashActivity.this;
    AccountManager accountManager;
    String token;
    int serverCode;
    private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        syncGoogleAccount();

    }

    private String[] getAccountNames(){
        accountManager = AccountManager.get(this);
        Account[] accounts = accountManager
                .getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names = new String[accounts.length];
        for (int i= 0; i<names.length; i++){
            names[i] = accounts[i].name;
            Toast.makeText(this, names[i], Toast.LENGTH_SHORT).show();
        }
        return names;
    }
    private AbstractGetNameTask getTask(SplashActivity activity, String email, String scope){
        return new GetNameInForeground(activity, email, scope);
    }

    public void syncGoogleAccount(){
        if(isNetworkAvailable()==true){
            String[] accountarrs = getAccountNames();
            if(accountarrs.length>0){
                getTask(SplashActivity.this, accountarrs[0], SCOPE).execute();
            }else{
                Toast.makeText(SplashActivity.this,
                        "No hay una cuenta de google sincronizada",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            Log.e("Probando Network", "Disponible");
            return  true;
        }
        Log.e("Probando Network", "No disponible");
        return  false;

    }


}