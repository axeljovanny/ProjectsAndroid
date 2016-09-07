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
public class UpdateEmployeeActivity  extends Activity implements View.OnClickListener{

    private EditText edtName;
    private EditText edtRfc;
    private EditText edtPhone;
    private Button btnUpdate;
    private Button btnDelete;
    private SQLController sqlController;
    private Employee employee = new Employee();
    private  String name, phone, rfc;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_update_layout);
        edtName = (EditText)findViewById(R.id.edt_name);
        edtRfc = (EditText)findViewById(R.id.edt_rfc);
        edtPhone = (EditText)findViewById(R.id.edt_phone);

        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnUpdate = (Button)findViewById(R.id.btn_update);
        sqlController = new SQLController(this);
        try {
            sqlController.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        id = Integer.parseInt(getIntent().getStringExtra(DBHelper.EMPLOYEE_ID));
        name = getIntent().getStringExtra(DBHelper.EMPLOYEE_NAME);
        phone = getIntent().getStringExtra(DBHelper.EMPLOYEE_PHONE);
        rfc = getIntent().getStringExtra(DBHelper.EMPLOYEE_RFC);

        edtName.setText(name);
        edtRfc.setText(rfc);
        edtPhone.setText(phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                employee.setId(id);
                employee.setName(edtName.getText().toString());
                employee.setRfc(edtRfc.getText().toString());
                employee.setPhone(edtPhone.getText().toString());
                sqlController.update(employee);
                getBack();
              break;
            case R.id.btn_delete:
                employee.setId(id);
                sqlController.delete(employee);
                getBack();
                break;
            default:
                break;
        }
    }

    private void getBack(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        sqlController.close();
    }
}
