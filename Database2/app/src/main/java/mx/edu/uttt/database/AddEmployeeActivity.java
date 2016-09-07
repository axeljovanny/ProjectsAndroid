package mx.edu.uttt.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

/**
 * Created by qas on 23/08/16.
 */
public class AddEmployeeActivity extends Activity implements View.OnClickListener{

    private Button btnSave;
    private EditText edtName;
    private EditText edtRfc;
    private EditText edtPhone;
    private SQLController sqlController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_add_layout);
        edtName = (EditText)findViewById(R.id.edt_name);
        edtRfc = (EditText)findViewById(R.id.edt_rfc);
        edtPhone = (EditText)findViewById(R.id.edt_phone);
        btnSave = (Button)findViewById(R.id.btn_save);
        sqlController = new SQLController(this);
        try {
            sqlController.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                Employee employee = new Employee(
                        edtName.getText().toString(),
                        edtRfc.getText().toString(),
                        edtPhone.getText().toString()
                );
                sqlController.insert(employee);
                Intent intent = new Intent(AddEmployeeActivity.this
                , MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
