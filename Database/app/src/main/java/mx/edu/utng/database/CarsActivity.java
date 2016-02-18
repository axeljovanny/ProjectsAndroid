package mx.edu.utng.database;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by qas on 17/02/16.
 */
public class CarsActivity extends Activity
        implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    private SQLController sqlController;
    private Button btnNew;
    private ListView lsvCars;
    private TextView txvMark;
    private TextView txvModel;
    private TextView txvYear;
    private TextView txvCarId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);
        initComponents();
    }

    private void initComponents(){
        sqlController = new SQLController(this);
        sqlController.openDB();
        btnNew = (Button)findViewById(R.id.btn_new);
        lsvCars = (ListView)findViewById(R.id.lsv_cars);
        btnNew.setOnClickListener(this);
        lsvCars.setOnItemClickListener(this);

        Cursor cursor = sqlController.readAll();
        String[] from ={DBHelper.CAR_ID, DBHelper.CAR_MARK, DBHelper.CAR_MODEL,
                DBHelper.CAR_YEAR
        };
        int[] to = {
                R.id.txv_car_id,
                R.id.txv_car_mark,
                R.id.txv_car_model,
                R.id.txv_car_year
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.item_layout,
                cursor,
                from,
                to);

        adapter.notifyDataSetChanged();
        lsvCars.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(CarsActivity.this,
                AddCarsActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        txvMark = (TextView)view.findViewById(R.id.txv_car_mark);
        txvModel = (TextView)view.findViewById(R.id.txv_car_model);
        txvYear = (TextView)view.findViewById(R.id.txv_car_year);
        txvCarId = (TextView)view.findViewById(R.id.txv_car_id);

        Intent intent = new Intent(CarsActivity.this,
                EditCarsActivity.class);

        intent.putExtra("mark",txvMark.getText().toString());
        intent.putExtra("model",txvModel.getText().toString());
        intent.putExtra("year",txvYear.getText().toString());
        intent.putExtra("id", txvCarId.getText().toString());

        startActivity(intent);
    }
}
