package ch.heigvd.sym.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginSucceededActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_succeeded);

        email = findViewById(R.id.usernameSuccess);
        password = findViewById(R.id.passwordSuccess);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("emailEntered"));
        password.setText(intent.getStringExtra("passwordGiven"));
    }
}
