package mx.edu.utng.swipeactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qas on 20/02/16.
 */
public class ClientesFragment extends Fragment{
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frm_productos, container, false);
        return rootView;
    }
}
