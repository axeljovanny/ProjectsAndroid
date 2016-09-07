package mx.edu.itses.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txvResult = (TextView)
                findViewById(R.id.txv_result);
        Bundle bundle = getIntent().getExtras();
        txvResult.setText(""
                +bundle.getInt("result"));
    }
}
