/*
 * File     : LoginSucceededActivity.java
 * Project  : TemplateActivity
 * Authors  : Jael Dubey, Loris Gilliand, Mateo Tutic, Luc Wachter
 */
package ch.heigvd.sym.template;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class LoginSucceededActivity extends AppCompatActivity {
    public static final int READ_EXTERNAL_STORAGE = 112;

    private static final String TAG = LoginSucceededActivity.class.getSimpleName();

    private TextView email;
    private TextView password;
    private TextView imeiField;
    private ImageView image;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_succeeded);

        Log.d(LoginSucceededActivity.TAG, "onCreate");

        image = findViewById(R.id.userImage);
        email = findViewById(R.id.usernameSuccess);
        password = findViewById(R.id.passwordSuccess);
        imeiField = findViewById(R.id.IMEI);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("emailEntered"));
        password.setText(intent.getStringExtra("passwordGiven"));

        // Set user image from downloaded files
        readSDcardDownloadedFiles();

        // Question 5
        String imei = getIMEI();
        if (imei == null) {
            imei = "null";
        }
        imeiField.setText(imei);

        // Return intent (with IMEI) for question 4
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", imei);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    // Source : https://stackoverflow.com/a/52841438
    protected void readSDcardDownloadedFiles() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
        } else {
            // Permission is granted
            setImageProfile();
        }
    }

    // Source : https://stackoverflow.com/a/52841438
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Read the files
            setImageProfile();
        }
    }

    private void setImageProfile() {
        // Get username
        int index = getIntent().getStringExtra("emailEntered").indexOf('@');
        String username = getIntent().getStringExtra("emailEntered").substring(0, index);

        // Set image profile
        File imageFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/" + username + ".jpeg");
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        image.setImageBitmap(bitmap);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LoginSucceededActivity.TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LoginSucceededActivity.TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LoginSucceededActivity.TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LoginSucceededActivity.TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(LoginSucceededActivity.TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LoginSucceededActivity.TAG, "onDestroy");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String getIMEI() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        assert tm != null;

        String imei = null;

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imei = tm.getImei();
            } else {
                imei = tm.getDeviceId();
            }
        }

        return imei;
    }
}
