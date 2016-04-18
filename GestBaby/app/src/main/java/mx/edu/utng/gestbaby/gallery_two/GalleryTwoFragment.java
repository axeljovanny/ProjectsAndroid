package mx.edu.utng.gestbaby.gallery_two;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import mx.edu.utng.gestbaby.R;
import mx.edu.utng.gestbaby.dbsemana.DatabaseSemana;
import mx.edu.utng.gestbaby.pack.IOUtils;
import mx.edu.utng.gestbaby.pack.Semana;


public class GalleryTwoFragment extends Fragment {

    private static String DB_NAME;
    private static String DB_NAME_OUT;
    private static String DB_PATH;
    private DatabaseSemana databaseSemana;
    protected InitTaskCopiarBD copiarBasedeDatos;
    protected InitTaskSemanas getSemanas;
    private Context context;

    private int semanaActual;
    private List<Semana> semanas;

    private boolean copiat;

    static {
        DB_PATH = "/data/data/mx.edu.utng.gestbaby/databases/";
        DB_NAME = "semana1.sqlite";
        DB_NAME_OUT = "semana1.db";
    }

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
            R.drawable.embryo_week_39, R.drawable.embryo_week_40, R.drawable.embryo_week_41, R.drawable.embryo_week_41
    };

    ManejadoraGaleria manejadorGaleria;
    ViewPager mViewPager;

    public GalleryTwoFragment() {
        this.semanas = new ArrayList();
        this.semanaActual = 0;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_gallery_two, container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
       // setContentView(R.layout.main_gallery_two);
        context = getActivity();
        FragmentManager fragmentManager = getFragmentManager();
        manejadorGaleria = new ManejadoraGaleria(fragmentManager);
        if (checkDB()) {
            this.databaseSemana = new DatabaseSemana(this.context);
            this.getSemanas = new InitTaskSemanas();
            this.getSemanas.execute(new Context[]{this.context});
           return;
        }
        copyDB();



    }

    private boolean checkDB() {
        return checkDataBase();
    }

    private void copyDB() {
        if (!checkDataBase()) {
            this.copiarBasedeDatos = new InitTaskCopiarBD();
            this.copiarBasedeDatos.execute(new Context[]{this.context});
        }
    }
    private boolean checkDataBase() {
        if (new File(DB_PATH, DB_NAME_OUT).exists()) {
            return true;
        }
        return false;
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
        private static final String ARG_BABY = "bebe";
        private static final String ARG_MOM = "madre";
        private static final String ARG_TIPS = "consejos";
        private static final String ARG_WEEK = "semana";
        private int imagen;
        private int semana;
        private String bebe;
        private String madre;
        private String consejos;


        public static FragmentosImagenes newInstance(int imagen, String bebe, String madre, String consejos, int semana) {
            FragmentosImagenes fragment = new FragmentosImagenes();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE, imagen);
            args.putString(ARG_BABY, bebe);
            args.putString(ARG_MOM, madre);
            args.putString(ARG_TIPS, consejos);
            args.putInt(ARG_WEEK, semana);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments() != null) {
                imagen = getArguments().getInt(ARG_IMAGE);
                bebe = getArguments().getString(ARG_BABY);
                madre = getArguments().getString(ARG_MOM);
                consejos = getArguments().getString(ARG_TIPS);
                semana = getArguments().getInt(ARG_WEEK);
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

            TextView estadoBebeText = (TextView) rootView.findViewById(R.id.estadoBebeText);
            TextView semanaText = (TextView) rootView.findViewById(R.id.semanaText);
            TextView estadoMadreText = (TextView) rootView.findViewById(R.id.estadoMadreText);
            TextView consejosText = (TextView) rootView.findViewById(R.id.consejosText);

            estadoBebeText.setText(bebe);
            estadoMadreText.setText(madre);
            consejosText.setText(consejos);
            semanaText.setText("Semana "+semana);

            return rootView;
        }
    }

    protected class InitTaskCopiarBD extends AsyncTask<Context, String, String> {
        private ProgressDialog ProgresCopy;

        class l implements DialogInterface.OnClickListener {
            l() {
            }

            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                getActivity().onBackPressed();
            }
        }

        protected InitTaskCopiarBD() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.ProgresCopy = ProgressDialog.show(GalleryTwoFragment.this.context, GalleryTwoFragment.this.context.getString(
                    R.string.wait_please), GalleryTwoFragment.this.context.getString(R.string.copiant), true);
        }

        protected String doInBackground(Context... params) {
            GalleryTwoFragment.this.copyDataBase();
            return "completo";
        }

        protected void onPostExecute(String unused) {
            this.ProgresCopy.dismiss();
            if (GalleryTwoFragment.this.copiat) {
                GalleryTwoFragment.this.databaseSemana = new DatabaseSemana(GalleryTwoFragment.this.context);
                GalleryTwoFragment.this.getSemanas = new InitTaskSemanas();
                GalleryTwoFragment.this.getSemanas.execute(new Context[]{GalleryTwoFragment.this.context});
                return;
            }
            AlertDialog.Builder adb = new AlertDialog.Builder(GalleryTwoFragment.this.context);
            adb.setCancelable(false);
            adb.setMessage(GalleryTwoFragment.this.context.getString(R.string.errorcopiantsemana));
            adb.setPositiveButton(GalleryTwoFragment.this.context.getString(R.string.aceptar), new l());
            adb.show();
        }
    }

    protected class InitTaskSemanas extends AsyncTask<Context, String, String> {
        private ProgressDialog ProgresGetSemanas;

        protected InitTaskSemanas() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            try {
                this.ProgresGetSemanas = ProgressDialog.show(GalleryTwoFragment.this.context,
                        GalleryTwoFragment.this.context.getString(R.string.wait_please), GalleryTwoFragment.this.context.getString(
                                R.string.carcontenido), true);
            } catch (Exception e) {
                Log.e("InitTaskSemanas", e.toString());
            }
        }

        protected String doInBackground(Context... params) {
            GalleryTwoFragment.this.semanas = GalleryTwoFragment.this.databaseSemana.getSemanas();
            return "completo";
        }

        protected void onPostExecute(String unused) {
            try {
                this.ProgresGetSemanas.dismiss();
            } catch (Exception e) {
                Log.e("InitTaskSemanas", e.toString());
            }
            if (GalleryTwoFragment.this.semanas == null) {
                GalleryTwoFragment.this.errorBaseDeDatos();
            } else if (GalleryTwoFragment.this.semanas.size() > 1) {
                Log.i("Semanas", ""+semanas.size());
                mViewPager = (ViewPager) getView().findViewById(R.id.pager);

                for (Semana semana : semanas) {
                    manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[semana.getSemana()-1],
                            semana.getEstadoBebe(), semana.getEstadoMadre(), semana.getConsejos(), semana.getSemana()));
                }
                mViewPager.setAdapter(manejadorGaleria);

                getActivity().onWindowFocusChanged(true);
            } else {
                GalleryTwoFragment.this.errorBaseDeDatos();
            }

        }
    }


    private void copyDataBase() {
        File f = new File(DB_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            InputStream myInput = this.context.getAssets().open(DB_NAME);
            OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME_OUT);
            IOUtils.copy(myInput, myOutput);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            this.copiat = false;
        }
    }

    private void errorBaseDeDatos() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this.context);
        adb.setCancelable(false);
        adb.setMessage(this.context.getString(R.string.errorcargardatos));
        adb.setPositiveButton(this.context.getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().onBackPressed();
            }
        });
        adb.show();
    }

}