package directi.androidteam.training.chatclient.Roster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Authentication.Account;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/3/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayRosterActivity extends FragmentActivity {
    private static ArrayList<Account> accounts;

//    public void setCurrentAccount(String JID, String status, String presence, Tag queryTag) {
//        this.currentAccount = new Account(JID, status, presence, queryTag);
//    }
//
    public static void setAccounts(ArrayList<Account> a) {
        accounts = a;
    }
    public static ArrayList<Account> getAccounts() {
        return accounts;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO : change the roster layout to not show your own jid and photo since you may now be logged in from multiple accounts
        setContentView(R.layout.roster);
        ((ListView)findViewById(R.id.roster_list)).setAdapter(new RosterItemAdapter(this, R.layout.rosterlistitem, new ArrayList<RosterItem>()));
        ((ListView)findViewById(R.id.roster_list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                onListItemClick((ListView) adapterView, view, position, id);
            }
        });
        //TODO : by passing activity to it we are also passing the accounts list, now instead of getting accounts details from jid.getJID
        //TODO : get them from accounts in account array .. iterate over this array and ask for roster of each account .. iteration is done
        //TODO : in request roster itself .. not on ui thread
        (new RequestRoster(this)).execute();
    }

    public void onListItemClick(ListView view, View v, int position, long id) {
        RosterItem rosterItem = (RosterItem) view.getItemAtPosition(position);
        Intent intent = new Intent(ChatApplication.getAppContext(), ChatBox.class);
        intent.putExtra("buddyid", rosterItem.getBareJID());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rostermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.status_busy_menu_item:
//                (new SendStatusCumPresence(this)).execute(this.currentAccount.getJID(), this.currentAccount.getStatus(), "dnd");
//                this.currentAccount.setPresence("dnd");
//                displayPresence("dnd");
                return true;
            case R.id.status_available_menu_item:
//                (new SendStatusCumPresence(this)).execute(this.currentAccount.getJID(), this.currentAccount.getStatus(), "default");
//                this.currentAccount.setPresence("default");
//                displayPresence("default");
                return true;
            case R.id.set_status_menu_item:
                (new SetStatusDialog()).show(getSupportFragmentManager(), "add_status_dialog_box_tag");
                return true;
            case R.id.add_contact_menu_item:
                (new AddContactDialog()).show(getSupportFragmentManager(), "add_contact_dialog_box_tag");
                return true;
            case R.id.search_menu_item:
                return true;
            case R.id.logout_menu_item:
//                Presence presence = new Presence();
//                presence.setType("unavailable");
//                PacketWriter.addToWriteQueue(presence.setRecipientAccount(this.currentAccount.getJID().split("/")[0]));
//                UserDatabaseHandler db = new UserDatabaseHandler(this);
//                db.updateState(ConnectGTalk.username, "offline");
//                startActivity(new Intent(this, UserListActivity.class));
//                RosterManager.getInstance().clearRoster();
//                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void updateRosterList(ArrayList<RosterItem> rosterList) {
        RosterItemAdapter rosterItemAdapter = ((RosterItemAdapter)(((ListView)findViewById(R.id.roster_list)).getAdapter()));
        rosterItemAdapter.setRosterItems(rosterList);
        rosterItemAdapter.notifyDataSetChanged();
    }

    public void displayStatus(String status) {
        ((TextView) findViewById(R.id.Roster_mystatus)).setText(status);
    }

    public void displayPresence(String presence) {
        int availabilityImageResource = R.drawable.gray;
        if (presence.equals("dnd")) {
            availabilityImageResource = R.drawable.red;
        } else if (presence.equals("default")) {
            availabilityImageResource = R.drawable.green;
        }
        ((ImageView) findViewById(R.id.availability_image)).setImageResource(availabilityImageResource);
    }

    public void displayJID(String JID) {
        ((TextView) findViewById(R.id.Roster_myjid)).setText(JID);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}


