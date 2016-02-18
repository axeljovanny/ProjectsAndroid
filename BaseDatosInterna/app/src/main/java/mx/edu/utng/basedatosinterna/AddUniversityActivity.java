package mx.edu.utng.basedatosinterna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by qas on 16/02/16.
 */
public class AddUniversityActivity extends Activity implements View.OnClickListener{

    private EditText edtUniversityName;
    private EditText edtUniversityStudents;
    private Button btnInsertar;
    private SQLController sqlController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_university_layout);
        initComponents();
    }

    private void initComponents(){
        edtUniversityName = (EditText)findViewById(R.id.edt_university_name);
        edtUniversityStudents = (EditText)findViewById(R.id.edt_university_students);
        btnInsertar = (Button)findViewById(R.id.btn_insertar);
        sqlController = new SQLController(this);
        sqlController.openDB();
        btnInsertar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insertar:
                String strName = edtUniversityName.getText().toString();
                int students = Integer.parseInt(
                        edtUniversityStudents.getText().toString());
                 sqlController.insert(strName, students);
                Intent principal = new Intent(AddUniversityActivity.this,
                        UnivesidadesActivity.class).setFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(principal);
            break;
            default:
                break;
        }
    }
}
