package directi.androidteam.training.chatclient;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import directi.androidteam.training.chatclient.Authentication.MyTabListener;
import directi.androidteam.training.chatclient.Authentication.accountsFragment;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/16/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalTabActivity extends Activity {
    public static final int FRAGMENT_ACCOUNTS = 1;
    public static final int FRAGMENT_ROSTER = 2;
    private Vector<ActionBar.Tab> tabs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tabs = new Vector<ActionBar.Tab>();
        setContentView(R.layout.main);
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab accountsTab = actionbar.newTab().setText("Accounts");
        Fragment accountsFragment = new accountsFragment();
        accountsTab.setTabListener(new MyTabListener(accountsFragment));
        actionbar.addTab(accountsTab);
        tabs.add(accountsTab);
        actionbar.selectTab(accountsTab);
    }

    public void switchFragment(int fragmentNumber) {
        ActionBar actionbar = getActionBar();
        if(fragmentNumber==FRAGMENT_ACCOUNTS) {
            actionbar.selectTab(tabs.get(0));
        }
        else
            actionbar.selectTab(tabs.get(1));
    }
}
