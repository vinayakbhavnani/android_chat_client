package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import directi.androidteam.training.chatclient.Authentication.*;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Chat.ChatNotifier;

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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDatabaseHandler db = new UserDatabaseHandler(this);
        ArrayList<User> users = db.getAllUsers();
        db.close();
        Intent chatintent = new Intent(this, ChatBox.class);
        startActivity(chatintent);
//        cn.notifyChat(null);
        if (users.size() == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            if (userLoggedIn(users)) {
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
