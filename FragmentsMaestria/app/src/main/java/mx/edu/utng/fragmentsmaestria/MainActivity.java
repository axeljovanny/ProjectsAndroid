package mx.edu.utng.fragmentsmaestria;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;



public class MainActivity extends ActionBarActivity {

    String[] ropa = {
            "Telas",
            "Moda",
            "Pantalones",
            "Camisas"
    };

    int[] imagenes = {
            R.drawable.imagen1,
            R.drawable.imagen2,
            R.drawable.imagen3,
            R.drawable.imagen4,
            R.drawable.imagen5,
            R.drawable.imagen6,
            R.drawable.imagen7,
            R.drawable.imagen8,
            R.drawable.imagen9,
            R.drawable.imagen10
    };

    ManejadoraGaleria manejadorGaleria;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lista
        ListView lista = (ListView) findViewById(R.id.listView1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ropa );
        lista.setAdapter(adapter);

        //galeria de imagenes

        manejadorGaleria = new ManejadoraGaleria(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[0]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[1]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[2]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[3]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[4]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[5]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[6]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[7]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[8]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[9]));
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ImageView imagenView = (ImageView) rootView.findViewById(R.id.imageView1);
            imagenView.setImageResource(imagen);
            return rootView;
        }
    }

}