package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
    public static final String USERNAME = "com.example.LoginActivity.USERNAME";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Called when the user clicks the Login button
     */
    public void loginUser(View view) {
        Intent intent = new Intent(this, DisplayRosterActivity.class);
        EditText username_edit_text = (EditText) findViewById(R.id.username);
        EditText password_edit_text = (EditText) findViewById(R.id.password);
        String username = username_edit_text.getText().toString();
        String password = password_edit_text.getText().toString();
        intent.putExtra(USERNAME, username);
        ConnectGTalk connectGTalk = new ConnectGTalk();
        (new Test()).execute(username, password);
        startActivity(intent);
    }
}
