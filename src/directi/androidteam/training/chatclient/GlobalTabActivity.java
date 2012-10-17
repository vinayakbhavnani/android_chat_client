package directi.androidteam.training.chatclient;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Authentication.DisplayAccounts;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/16/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalTabActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost tabHost = getTabHost();


        TabHost.TabSpec accountSpec = tabHost.newTabSpec("Accounts");
        Intent accountIntent = new Intent(this, DisplayAccounts.class);
        accountSpec.setIndicator("Accounts");
        accountSpec.setContent(accountIntent);
        tabHost.addTab(accountSpec);


        TabHost.TabSpec contactSpec = tabHost.newTabSpec("Contacts");
        Intent contactIntent = new Intent(ChatApplication.getAppContext(), DisplayRosterActivity.class);
        contactSpec.setIndicator("Contacts");
        contactSpec.setContent(contactIntent);
        tabHost.addTab(contactSpec);

/*
        TabHost.TabSpec chatSpec = tabHost.newTabSpec("Chats");
        Intent chatIntent = new Intent(this, DisplayRosterActivity.class);
        chatSpec.setIndicator("Chats");
        chatSpec.setContent(chatIntent);
        tabHost.addTab(chatSpec);
*/

    }
}
