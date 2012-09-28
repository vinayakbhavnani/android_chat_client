package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            EditText username_edit_text = (EditText) findViewById(R.id.username);
            username_edit_text.setText(username);
        }
        View.OnFocusChangeListener usernamePasswordFocusChangeListener = new EditTextFocusChangeListener();
        ((EditText) findViewById(R.id.username)).setOnFocusChangeListener(usernamePasswordFocusChangeListener);
        ((EditText)findViewById(R.id.password)).setOnFocusChangeListener(usernamePasswordFocusChangeListener);
//        this.registerReceiver(new NetworkConnectionChangeReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.login_via_gtalk:
                loginUser(new View(this));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /**
     * Called when the user clicks the Login button
     */
    public void loginUser(View view) {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        if (username.equals("")) {
            ((EditText) findViewById(R.id.username)).setError("This Field Cannot Be Left Blank");
        } else if (password.equals("")) {
            ((EditText) findViewById(R.id.password)).setError("This Field Cannot Be Left Blank");
        } else {
            ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            (new ConnectGTalk(this)).execute(username, password);
        }
    }
}
