package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import directi.androidteam.training.chatclient.Authentication.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/19/12
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class InitialActivity extends Activity {
    private boolean userLoggedIn(ArrayList<User> users) {
        for (User user : users) {
            if (user.getState().equals("online")) {
                return true;
            }
        }
        return false;
    }

    private User getLoggedInUser(ArrayList<User> users) {
        for (User user : users) {
            if (user.getState().equals("online")) {
                return user;
            }
        }
        return new User();
    }

    @Override
    public void onPause() {
        super.onPause();
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar_loading);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showLoading() {
        this.setContentView(R.layout.loading);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar_loading);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDatabaseHandler db = new UserDatabaseHandler(this);
        ArrayList<User> users = db.getAllUsers();
        db.close();
        if (users.size() == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            if (userLoggedIn(users)) {
                showLoading();
                User loggedInUser = getLoggedInUser(users);
                (new ConnectGTalk(this)).execute(loggedInUser.getUsername(), loggedInUser.getPassword());
            } else {
                Intent intent = new Intent(this, UserListActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
    }
}
