package mx.edu.utng.basedatosinterna;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by qas on 15/02/16.
 */
public class UnivesidadesActivity extends Activity
        implements View.OnClickListener,
        AdapterView.OnItemClickListener {
   private Button btnAgregarUniversidad;
   private ListView lsvUniversidades;
   private SQLController sqlController;
   private TextView txvUnivesityName;
   private TextView txvUniversityId;
   private TextView txvUniversityStudents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universidades_layout);
        initComponents();
    }

    private void initComponents() {
        sqlController = new SQLController(this);
        sqlController.openDB();
        btnAgregarUniversidad = (Button)findViewById(R.id.btn_agregar_universidad);
        lsvUniversidades = (ListView)findViewById(R.id.lsv_univesidades);

        btnAgregarUniversidad.setOnClickListener(this);

        Cursor cursor = sqlController.read();

        String[] from = new String[]{
                DBHelper.UNIVERSITY_ID,
                DBHelper.UNIVERSITY_NAME,
                DBHelper.UNIVERSITY_STUDENTS
        };
        int[] to = new int[]{
                R.id.txv_university_id,
                R.id.txv_university_name,
                R.id.txv_university_students
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                UnivesidadesActivity.this,
                R.layout.item_layout,
                cursor,from,to);

        adapter.notifyDataSetChanged();
        lsvUniversidades.setAdapter(adapter);

        lsvUniversidades.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(UnivesidadesActivity.this,
                        AddUniversityActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        txvUniversityId = (TextView)view.findViewById(R.id.txv_university_id);
        txvUnivesityName = (TextView)view.findViewById(R.id.txv_university_name);
        txvUniversityStudents = (TextView)view.findViewById(R.id.txv_university_students);

        String strId = txvUniversityId.getText().toString();
        String strName = txvUnivesityName.getText().toString();
        String strStudents = txvUniversityStudents.getText().toString();

        Intent intent = new Intent(getApplicationContext(),
                UpdateUniversityActivity.class);
        intent.putExtra("universityId", strId);
        intent.putExtra("universityName", strName);
        intent.putExtra("universityStudents", strStudents);
        startActivity(intent);
    }
}
