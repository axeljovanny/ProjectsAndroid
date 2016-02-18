package mx.edu.utng.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by qas on 17/02/16.
 */
public class AddCarsActivity extends Activity implements View.OnClickListener{
    private SQLController sqlController;
    private EditText edtMark;
    private EditText edtModel;
    private EditText edtYear;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);
        initComponents();
    }
    private void initComponents(){
        edtMark = (EditText)findViewById(R.id.edt_mark);
        edtModel = (EditText)findViewById(R.id.edt_model);
        edtYear = (EditText)findViewById(R.id.edt_year);
        btnAdd = (Button)findViewById(R.id.btn_add);

        sqlController = new SQLController(this);
        sqlController.openDB();
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                String mark = edtMark.getText().toString();
                String model = edtModel.getText().toString();
                int year = Integer.parseInt(
                        edtYear.getText().toString());
                sqlController.add(mark, model, year);
                startActivity(new Intent(AddCarsActivity.this,
                        CarsActivity.class).setFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            default:
                break;
        }
    }
}
