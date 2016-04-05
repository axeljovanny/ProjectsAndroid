package bareta.sendemailpicture;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                EditText user=(EditText)findViewById(R.id.editUser);
                EditText pass=(EditText)findViewById(R.id.editPass);
                EditText to=(EditText)findViewById(R.id.editTo);
                EditText subject=(EditText)findViewById(R.id.editSubject);
                EditText body=(EditText)findViewById(R.id.editBody);


                EmailSender emailSender=new EmailSender();

                user.setText("crackiman@gmail.com");
                pass.setText("elchino1");
                to.setText("anastaciorodriguez@utng.edu.mx");
                subject.setText("prueba");
                body.setText("Prueba correo");
                Log.i("MainActivity","Enviando correo");
                emailSender.execute(user.getText().toString(),pass.getText().toString(),to.getText().toString(),subject.getText().toString(),
                        body.getText().toString(),"/sdcard/DCIM/Camera/test.jpg");

                //EmailSender emailSender2=new EmailSender();

                //emailSender.execute(emailSenderData.getAddress(), emailSenderData.getPassword(), arrayRecipients, "Novedad - " + format.format(newDate), emailBody, _fullThumbFileName);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
