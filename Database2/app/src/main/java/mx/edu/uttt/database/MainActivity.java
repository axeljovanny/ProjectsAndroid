package mx.edu.uttt.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView lsvEmployees;
    private SQLController sqlController;
    private TextView txv_name;
    private TextView txv_id;
    private TextView txv_phone;
    private Employee employee = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlController = new SQLController(this);
        try {
            sqlController.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnAdd = (Button) findViewById(R.id.btn_add);
        lsvEmployees = (ListView)findViewById(R.id.lsv_empleados);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        AddEmployeeActivity.class));

            }
        });

        Cursor cursor = sqlController.select();

        String[] from = new String[]{
                DBHelper.EMPLOYEE_NAME,
                DBHelper.EMPLOYEE_PHONE,
                DBHelper.EMPLOYEE_ID
       };

        int[] to = new int[]{
                R.id.txv_name,
                R.id.txv_phone,
                R.id.txv_id
        };

        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(
                        MainActivity.this,
                        R.layout.employee_item_layout,
                        cursor,
                        from,
                        to);

        adapter.notifyDataSetChanged();
        lsvEmployees.setAdapter(adapter);

        lsvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                txv_name = (TextView) view.findViewById(R.id.txv_name);
                txv_phone = (TextView) view.findViewById(R.id.txv_phone);
                txv_id = (TextView) view.findViewById(R.id.txv_id);

                Intent intent = new Intent(getApplicationContext(),
                       UpdateEmployeeActivity.class );
                employee.setId(Integer.parseInt(txv_id.getText().toString()));
                Cursor cursor1 = sqlController.selectOne(employee);



                intent.putExtra(DBHelper.EMPLOYEE_NAME, cursor1.getString(1));
                intent.putExtra(DBHelper.EMPLOYEE_PHONE, cursor1.getString(2));
                intent.putExtra(DBHelper.EMPLOYEE_ID, cursor1.getString(0));
                intent.putExtra(DBHelper.EMPLOYEE_RFC, cursor1.getString(3));


                startActivity(intent);


            }
        });

    }
}
