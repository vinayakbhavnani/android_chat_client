package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/7/12
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginErrorActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Called when the user clicks the Login button
     */
    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("username", ConnectGTalk.uname);
        startActivity(intent);
        this.finish();
    }
}
