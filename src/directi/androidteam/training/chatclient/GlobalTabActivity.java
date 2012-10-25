package directi.androidteam.training.chatclient;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import directi.androidteam.training.chatclient.Authentication.*;
import directi.androidteam.training.chatclient.Roster.RosterFragment;
import directi.androidteam.training.chatclient.Roster.RosterItem;
import directi.androidteam.training.chatclient.Roster.RosterItemAdapter;

import java.util.ArrayList;
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
        Log.d("ioio", "before acctfrag()");
        Fragment accountsFragment = new AccountsFragment();
        Log.d("ioio", "after acctfrag()");
        accountsTab.setTabListener(new MyTabListener(accountsFragment,this,AccountsFragment.class.getName()));
        actionbar.addTab(accountsTab);
        tabs.add(accountsTab);

        ActionBar.Tab contactsTab = actionbar.newTab().setText("Contacts");
        Fragment contactsFragment = new RosterFragment();
        contactsTab.setTabListener(new MyTabListener(contactsFragment, this, RosterFragment.class.getName()));
        actionbar.addTab(contactsTab);
        tabs.add(contactsTab);
    }


    public void switchFragment(int fragmentNumber) {
        ActionBar actionbar = getActionBar();

        if(fragmentNumber==FRAGMENT_ACCOUNTS) {
            actionbar.selectTab(tabs.get(0));
        }
        else
            actionbar.selectTab(tabs.get(1));
    }


    public void  accountSettings(View view){
        final Account account = AccountManager.getInstance().getAccount((String)view.getTag());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Settings");
        final ArrayList<String> temp;
        if(account.isLoginStatus().equals(LoginStatus.ONLINE)){
            temp = AccountsFragment.getLogoutList();
        }
        else
            temp = AccountsFragment.getLoginList();
        builder.setPositiveButton("Available",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("presence","available");
                account.sendAvail("available");
            }
        }) ;
        builder.setNegativeButton("Busy",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("presence","busy");
                account.sendAvail("dnd");
            }
        });

        builder.setItems(temp.toArray(new CharSequence[temp.size()]),new DialogListener(temp,account,AccountsFragment.getAdaptor(),this));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void updateRosterList(ArrayList<RosterItem> rosterList) {
        ListView lv = (((ListView)findViewById(R.id.roster_list)));
        if(lv == null) {return;}
        RosterItemAdapter rosterItemAdapter = (RosterItemAdapter)lv.getAdapter();
        rosterItemAdapter.setRosterItems(new ArrayList<RosterItem>(rosterList));
        rosterItemAdapter.notifyDataSetChanged();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.rostermenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.add_contact:
//                //TODO : add feature to ask for which account to add the contact to
//                // (new AddContactDialog()).show(getSupportFragmentManager(), "add_contact_dialog_box_tag");
//                return true;
//            default:
//                return super.onOptionsItemSelected(menuItem);
//        }
//    }
}
