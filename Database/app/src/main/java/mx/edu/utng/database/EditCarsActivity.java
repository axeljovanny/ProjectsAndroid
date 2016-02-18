package mx.edu.utng.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by qas on 17/02/16.
 */
public class EditCarsActivity extends Activity
        implements View.OnClickListener{
   private EditText edtMark;
    private EditText edtModel;
    private EditText edtYear;
    private Button btnUpdate;
    private Button btnDelete;
    private long carId;
    private SQLController sqlController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        initComponents();
    }
    private void initComponents(){
        sqlController = new SQLController(this);
        sqlController.openDB();

        edtMark = (EditText)findViewById(R.id.edt_mark);
        edtModel = (EditText)findViewById(R.id.edt_model);
        edtYear = (EditText)findViewById(R.id.edt_year);

        carId = Long.parseLong(getIntent().getStringExtra("id"));

        edtMark.setText(getIntent().getStringExtra("mark"));
        edtModel.setText(getIntent().getStringExtra("model"));
        edtYear.setText(getIntent().getStringExtra("year"));

        btnUpdate = (Button)findViewById(R.id.btn_update);
        btnDelete = (Button)findViewById(R.id.btn_delete);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_update:
               Log.i("ID CARRO",""+carId+" "+
                       edtMark.getText().toString()+" "+
                       edtModel.getText().toString()+"  "+
                       edtYear.getText().toString());

               sqlController.edit(carId,
                       edtMark.getText().toString(),
                       edtModel.getText().toString(),
                       Integer.parseInt(edtYear.getText().toString()));
               returnHome();
               break;
           case R.id.btn_delete:
               sqlController.delete(carId);
              returnHome();
               break;

           default:
               break;
       }

    }
    private void returnHome(){
        startActivity(new Intent(EditCarsActivity.this,
                CarsActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
