package mx.edu.utng.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by qas on 1/03/16.
 */
public class SelectorFragment extends Fragment {
    Activity activity;
    GridView gridView;
    SelectorAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_layout, null);

        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
