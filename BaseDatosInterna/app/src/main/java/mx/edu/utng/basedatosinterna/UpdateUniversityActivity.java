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

public class UpdateUniversityActivity extends Activity implements View.OnClickListener{

    private EditText edtUniversityName;
    private EditText edtUniversityStudents;
    private Button btnDelete;
    private Button btnUpdate;
    private SQLController sqlController;
    private long universityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        initComponents();
    }

    public void initComponents(){
        edtUniversityName = (EditText)findViewById(R.id.edt_university_name);
        edtUniversityStudents = (EditText)findViewById(R.id.edt_university_students);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnUpdate = (Button)findViewById(R.id.btn_modificar);
        sqlController = new SQLController(this);
        sqlController.openDB();
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        String strId = getIntent().getStringExtra("universityId");
        String strName = getIntent().getStringExtra("universityName");
        String strStudents = getIntent().getStringExtra("universityStudents");

        universityId = Long.parseLong(strId);

        edtUniversityName.setText(strName);
        edtUniversityStudents.setText(strStudents);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_modificar:
              sqlController.update(universityId,
                      edtUniversityName.getText().toString(),
                      Integer.parseInt(
                              edtUniversityStudents.getText()
                                      .toString()));
                returnHome();
             break;
            case  R.id.btn_delete:
                sqlController.delete(universityId);
                returnHome();
                break;
            default:
            break;
        }
    }

    public void returnHome(){
        Intent principal = new Intent(UpdateUniversityActivity.this,
                UnivesidadesActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(principal);
    }
}
