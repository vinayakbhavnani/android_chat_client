package directi.androidteam.training.chatclient.Authentication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/13/12
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserListActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        UserDatabaseHandler db = new UserDatabaseHandler(this);
        ArrayList<User> users = db.getAllUsers();
        db.close();
        UserAdapter userAdapter = new UserAdapter(this, R.layout.userlistitem, users);
        setListAdapter(userAdapter);
    }

    @Override
    public void onListItemClick(ListView view, View v, int position, long id) {
        String username = ((TextView)((RelativeLayout)v).getChildAt(0)).getText().toString();
        Log.d("Login Flow (Check Username)", username);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar_users);
        progressBar.setVisibility(View.VISIBLE);
        UserDatabaseHandler db = new UserDatabaseHandler(this);
        String password = db.getPassword(username);
        Log.d("Login Flow (Check Password)", password);
        db.close();
        (new ConnectGTalk(this)).execute(username, password);
    }

    @Override
    public void onPause() {
        super.onPause();
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar_users);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void addUser(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }
}
