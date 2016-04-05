package mx.edu.utng.gestbaby.gallery_two;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import mx.edu.utng.gestbaby.R;


public class MainActivity extends FragmentActivity {

    int[] imagenes = {
            R.drawable.embryo_week_3, R.drawable.embryo_week_4, R.drawable.embryo_week_5, R.drawable.embryo_week_6,
            R.drawable.embryo_week_7, R.drawable.embryo_week_8, R.drawable.embryo_week_9, R.drawable.embryo_week_10,
            R.drawable.embryo_week_11, R.drawable.embryo_week_12, R.drawable.embryo_week_13, R.drawable.embryo_week_14,
            R.drawable.embryo_week_15, R.drawable.embryo_week_16, R.drawable.embryo_week_17, R.drawable.embryo_week_18,
            R.drawable.embryo_week_19, R.drawable.embryo_week_20, R.drawable.embryo_week_21, R.drawable.embryo_week_22,
            R.drawable.embryo_week_23, R.drawable.embryo_week_24, R.drawable.embryo_week_25, R.drawable.embryo_week_26,
            R.drawable.embryo_week_27, R.drawable.embryo_week_28, R.drawable.embryo_week_29, R.drawable.embryo_week_30,
            R.drawable.embryo_week_31, R.drawable.embryo_week_32, R.drawable.embryo_week_33, R.drawable.embryo_week_34,
            R.drawable.embryo_week_35, R.drawable.embryo_week_36, R.drawable.embryo_week_37, R.drawable.embryo_week_38,
            R.drawable.embryo_week_39, R.drawable.embryo_week_40, R.drawable.embryo_week_41
    };

    ManejadoraGaleria manejadorGaleria;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gallery_two);

        manejadorGaleria = new ManejadoraGaleria(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        for (int i = 0; i <39 ; i++) {
            manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[i]));
        }
        mViewPager.setAdapter(manejadorGaleria);

    }



    public class ManejadoraGaleria extends FragmentPagerAdapter {

        List<Fragment> fragmentos;
        public ManejadoraGaleria(FragmentManager fm) {
            super(fm);
            fragmentos = new ArrayList();
        }

        public void agregarFragmentos(Fragment xfragmento){
            fragmentos.add(xfragmento);
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }
    }


    public static class FragmentosImagenes extends Fragment {

        private static final String ARG_IMAGE = "imagen";
        private int imagen;

        public static FragmentosImagenes newInstance(int imagen) {
            FragmentosImagenes fragment = new FragmentosImagenes();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE, imagen);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments() != null) {
                imagen = getArguments().getInt(ARG_IMAGE);
            }
        }

        public FragmentosImagenes() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_gallery_two, container, false);

            ImageView imagenView = (ImageView) rootView.findViewById(R.id.imageView1);
            imagenView.setImageResource(imagen);
            return rootView;
        }
    }

}