package directi.androidteam.training.chatclient;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
        //Spinner spinner = (Spinner)findViewById(R.id.accountScreen_spinner);
        Account account = AccountManager.getInstance().getAccount((String)view.getTag());
        /*ArrayAdapter<String> adapter = null;
        if(account.isLoginStatus()){
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,logoutList);
        }
        else
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,loginList);*/
        /*//spinner.setAdapter(adapter);
        //spinner.setVisibility(0);
        //spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view.setVisibility(2);
                Log.d("spinner",adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
*/
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
            }
        }) ;
        builder.setNegativeButton("Busy",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("presence","busy");
            }
        });

        builder.setItems(temp.toArray(new CharSequence[temp.size()]),new DialogListener(temp,account,AccountsFragment.getAdaptor()));

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void updateRosterList(ArrayList<RosterItem> rosterList) {
        RosterItemAdapter rosterItemAdapter = ((RosterItemAdapter)(((ListView)findViewById(R.id.roster_list)).getAdapter()));
        rosterItemAdapter.setRosterItems(new ArrayList<RosterItem>(rosterList));
        rosterItemAdapter.notifyDataSetChanged();
    }


}
