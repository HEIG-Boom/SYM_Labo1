package ch.heigvd.sym.template;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

        // Return intent for question 4
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "test");
        setResult(Activity.RESULT_OK, returnIntent);
    }
}
