package directi.androidteam.training.chatclient;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import directi.androidteam.training.chatclient.Authentication.AccountsFragment;
import directi.androidteam.training.chatclient.Authentication.MyTabListener;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/16/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalTabActivity extends FragmentActivity {
    public static final int FRAGMENT_ACCOUNTS = 1;
    public static final int FRAGMENT_ROSTER = 2;

    private ViewPager viewPager;
    private Vector<ActionBar.Tab> tabs;
    public static final int NUM_TABS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tabs = new Vector<ActionBar.Tab>();
        /*GlobalTabAdaptor tabAdaptor = new GlobalTabAdaptor(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.tab_pager);
        viewPager.setAdapter(tabAdaptor);
*/
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab accountsTab = actionbar.newTab().setText("Accounts");
        Fragment accountsFragment = new AccountsFragment();
        accountsTab.setTabListener(new MyTabListener(accountsFragment,this,AccountsFragment.class.getName()));
        actionbar.addTab(accountsTab);

        ActionBar.Tab contactsTab = actionbar.newTab().setText("Contacts");
        Fragment contactsFragment = new AccountsFragment();
        contactsTab.setTabListener(new MyTabListener(contactsFragment,this,AccountsFragment.class.getName()));
        actionbar.addTab(contactsTab);
        tabs.add(accountsTab);
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
