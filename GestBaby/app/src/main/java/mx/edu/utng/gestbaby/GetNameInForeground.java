package mx.edu.utng.gestbaby;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;

import java.io.IOException;

/**
 * Created by qas on 6/03/16.
 */
public class GetNameInForeground extends AbstractGetNameTask {

    public GetNameInForeground(SplashActivity activity, String email, String scope) {
        super(activity, email, scope);
        Log.i("GetNameInForeground", "Entre a Constructor");

    }

    @Override
    protected String fetchToken() throws IOException {
        Log.i("GetNameInForeground", "Entre a fetchToken");
        try {
            Log.e("fetchToken()", GoogleAuthUtil.getToken(activity, email, scope));
            return GoogleAuthUtil.getToken(activity, email, scope);
        }catch (GooglePlayServicesAvailabilityException e){
            e.printStackTrace();
        }catch (UserRecoverableAuthException e){
            activity.startActivityForResult(e.getIntent(), requestCode);
        }catch (GoogleAuthException e){
          onError("Error irrecuperable"+e.getMessage(), e);
        }
        return null;
    }
}
