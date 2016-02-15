package mx.edu.utng.segundoejercicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by qas on 13/02/16.
 */
public class ActividadPrincipal extends Activity implements View.OnClickListener{
    private Button btnEnviar;
    private EditText edtAsunto;
    private EditText edtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        btnEnviar = (Button)findViewById(R.id.btn_enviar);
        edtAsunto = (EditText)findViewById(R.id.edt_asunto);
        edtMensaje = (EditText)findViewById(R.id.edt_mensaje);
        btnEnviar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        String subject = edtAsunto.getText().toString();
        String message = edtMensaje.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL  , new String[]{"crackiman@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        email.setType("message/rfc822");
/**/        startActivity(Intent.createChooser(email, "Seleciona un cliente de correo"));
    }
}
