package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import directi.androidteam.training.chatclient.GlobalTabActivity;
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
            EditText username_edit_text = (EditText) findViewById(R.id.username_login);
            username_edit_text.setText(username);
        }
        View.OnFocusChangeListener usernamePasswordFocusChangeListener = new EditTextFocusChangeListener();
        ((EditText) findViewById(R.id.username_login)).setOnFocusChangeListener(usernamePasswordFocusChangeListener);
        ((EditText)findViewById(R.id.password_login)).setOnFocusChangeListener(usernamePasswordFocusChangeListener);
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
            case R.id.addaccount_signin:
                addAccount(new View(this));
                return true;
            case R.id.addaccount_import:
                Log.d("entering","optionsselected");
                importAccount(new View(this));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /**
     * Called when the user clicks the Login button
     */
    public void loginUser(View view) {
        String username = ((EditText) findViewById(R.id.username_login)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_login)).getText().toString();
        /*android.accounts.Account[] accounts = android.accounts.AccountManager.get(this).getAccountsByType("com.google");
        android.accounts.Account account = accounts[0];

        AccountManagerFuture<Bundle> accFut = android.accounts.AccountManager.get(this).getAuthToken(account,"talk",null,this,new OnTokenAcquired(this),null);
        *//*Bundle authTokenBundle = null;

        password = authTokenBundle.get(android.accounts.AccountManager.KEY_AUTHTOKEN).toString();
        username = account.name;*//*
        Log.d("username",account.name);*/
        (new ConnectGTalk(this)).execute(username, password);
        if (username.equals("")) {
            ((EditText) findViewById(R.id.username_login)).setError("This Field Cannot Be Left Blank");
        } else if (password.equals("")) {
            ((EditText) findViewById(R.id.password_login)).setError("This Field Cannot Be Left Blank");
        } else {
            /*ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            (new ConnectGTalk(this)).execute(username, password);*/
        }
    }

    public void addAccount(View view){
        String username = ((EditText) findViewById(R.id.username_login)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_login)).getText().toString();
        RadioGroup group = (RadioGroup) findViewById(R.id.addaccount_radio);
        String service =   (String)((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText();
        if (username.equals("")) {
            ((EditText) findViewById(R.id.username_login)).setError("This Field Cannot Be Left Blank");
        } else if (password.equals("")) {
            ((EditText) findViewById(R.id.password_login)).setError("This Field Cannot Be Left Blank");
        } else {
            //(new ConnectGTalk(this)).execute(username, password,service);
            ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            //(new ConnectGTalk(this)).execute(username, password);*/
            Account account = Account.createAccount(username,password,service,LoginStatus.OFFLINE.toString());
            new LoginTask(account).execute();
            AccountManager.getInstance().addAccount(account);
            Intent intent = new Intent(this,GlobalTabActivity.class);
            startActivity(intent);
        }
    }

    public void importAccount(View view){
        ImportGoogleAccount iga = new ImportGoogleAccount(this);
        //iga.execute();
        Log.d("entering","fromlogin");
        DialogBuilder.createImportAccountDialog(this);
    }
}
