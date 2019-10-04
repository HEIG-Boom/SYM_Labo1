/*
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * 			  Fabien Dutoit 23 juillet 2019
 *            IICT / HEIG-VD
 *
 * mailto:fabien.dutoit@heig-vd.ch
 *
 * This piece of code reads a [email_account / password ] combination.
 * It is used as a template project for the SYM module element given at HEIG-VD
 * Target audience : students IL, TS, IE [generally semester 1, third bachelor year]
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package ch.heigvd.sym.template;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // For logging purposes
    private static final String TAG = MainActivity.class.getSimpleName();

    // Hard coded users
    private Map<String, Pair<String, String>> users = new HashMap<>();

    // GUI elements
    private EditText email = null;
    private EditText password = null;
    private Button signIn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the welcome screen / login authentication dialog
        setContentView(R.layout.authent_rel);

        // Add users
        users.put("toto@tutu.com", new Pair<>("tata", "toto.jpg"));
        users.put("abc@def.com", new Pair<>("ghi", "abc.jpg"));
        users.put("tztz@tutu.com", new Pair<>("tmtm", "tztz.jpg"));

        // Link to GUI elements
        this.email = findViewById(R.id.email);
        this.password = findViewById(R.id.password);
        this.signIn = findViewById(R.id.buttOk);

        // Then program action associated to "Ok" button
        signIn.setOnClickListener((v) -> {

            String mail = email.getText().toString();
            String psw = password.getText().toString();

            if (!mail.contains("@")) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.wrongmail), Toast.LENGTH_LONG).show();
            } else if (isValid(mail, psw)) {
                Intent intent = new Intent(this, ch.heigvd.sym.template.LoginSucceededActivity.class);
                intent.putExtra("emailEntered", mail);
                intent.putExtra("passwordGiven", psw);
                startActivity(intent);
                Toast.makeText(MainActivity.this, getResources().getString(R.string.good), Toast.LENGTH_LONG).show();
                finish();
            } else {
                // Wrong combination, display pop-up dialog and stay on login screen
                showErrorDialog();
                email.getText().clear();
                password.getText().clear();
            }
        });
    }

    private boolean isValid(String mail, String password) {
        if (mail == null || password == null) {
            Log.w(TAG, "isValid(mail, password) - mail and password cannot be null !");
            return false;
        }
        // Check if the user exists
        return (users.containsKey(mail)) && password.equals(users.get(mail).first);
    }

    private void showErrorDialog() {
        /*
         * Pop-up dialog to show error
         */
        AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(android.R.drawable.ic_dialog_alert);
        alertbd.setTitle(R.string.wronglogin);
        alertbd.setMessage(R.string.wrong);
        alertbd.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            // we do nothing...
            // dialog close automatically
        });
        alertbd.create().show();
    }

}