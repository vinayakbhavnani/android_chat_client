package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import directi.androidteam.training.chatclient.R;

public class LoginActivity extends Activity {
    public static final String USERNAME = "directi.androidteam.training.chatclient.Authentication.LoginActivity.USERNAME";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    /**
     * Called when the user clicks the Login button
     */
    public void loginUser(View view) {
        EditText username_edit_text = (EditText) findViewById(R.id.username);
        EditText password_edit_text = (EditText) findViewById(R.id.password);
        String username = username_edit_text.getText().toString();
        String password = password_edit_text.getText().toString();
        (new ConnectGTalk(this)).execute(username, password);
    }
}
