package mx.edu.itses.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private EditText edtNumber1;
    private EditText edtNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edtNumber1 = (EditText) findViewById(
                R.id.edt_number_1);
        edtNumber2 = (EditText) findViewById(
                R.id.edt_number_2);
    }

    public void sumar(View v){
        int number1, number2;
        try {
            number1 = Integer.parseInt(
                    edtNumber1.getText().toString());
            number2 = Integer.parseInt(
                    edtNumber2.getText().toString());
        }catch (Exception e){
            number1 = 0;
            number2 = 0;
        }

        int result = number1 + number2;

        Toast.makeText(this, "Suma: "
                +result,Toast.LENGTH_SHORT).show();

        Intent i = new Intent(HomeActivity.this,
                SecondActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("result", result);

        i.putExtras(bundle);
        startActivity(i);


    }
}
