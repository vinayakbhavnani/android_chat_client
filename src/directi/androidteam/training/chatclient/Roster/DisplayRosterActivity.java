package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;
import directi.androidteam.training.chatclient.Authentication.UserDatabaseHandler;
import directi.androidteam.training.chatclient.Authentication.UserListActivity;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.eventHandlers.AddRosterDialog;
import directi.androidteam.training.chatclient.Roster.eventHandlers.AddStatusDialog;
import directi.androidteam.training.chatclient.Roster.eventHandlers.SearchRosterEntryDialog;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/3/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayRosterActivity extends ListActivity {
    private Account currentAccount;

    public Account getCurrentAccount() {
        return this.currentAccount;
    }

    public void setCurrentAccount(String JID, String status, String presence, Tag queryTag) {
        this.currentAccount = new Account(JID, status, presence, queryTag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        (new RequestRoster(this)).execute();
        setListAdapter(new RosterItemAdapter(this));
    }

    @Override
    public void onListItemClick(ListView view, View v, int position, long id) {
        RosterEntry rosterEntry = (RosterEntry) view.getItemAtPosition(position);
        Intent intent = new Intent(this, ChatBox.class);
        intent.putExtra("buddyid", rosterEntry.getJid());
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
            case R.id.menu_search:
                Intent intent = new Intent(this, SearchRosterEntry.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            case R.id.menu_add_contact:
                showDialog(1);
                return true;
            case R.id.logout:
                PresenceS presenceS = new PresenceS();
                presenceS.addType("unavailable");
                PacketWriter.addToWriteQueue(presenceS.getXml());
                UserDatabaseHandler db = new UserDatabaseHandler(this);
                db.updateState(ConnectGTalk.username, "offline");
                startActivity(new Intent(this, UserListActivity.class));
                this.finish();
                return true;
            case R.id.set_status:
                showDialog(2);
                return true;
            case R.id.status_busy:
                (new SendStatusCumPresence(this)).execute(this.currentAccount.getJID(), this.currentAccount.getStatus(), "dnd");
                this.currentAccount.setPresence("dnd");
                setPresence("dnd");
                return true;
            case R.id.status_available:
                (new SendStatusCumPresence(this)).execute(this.currentAccount.getJID(), this.currentAccount.getStatus(), "default");
                this.currentAccount.setPresence("default");
                setPresence("default");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void updateRosterList(ArrayList<RosterEntry> rosterList) {
        ((RosterItemAdapter)getListAdapter()).setRosterEntries(rosterList);
        ((RosterItemAdapter)getListAdapter()).notifyDataSetChanged();
    }

    public void setStatus(String status) {
        ((TextView) findViewById(R.id.Roster_mystatus)).setText(status);
    }

    public void setPresence(String presence) {
        int availabilityImageResource = R.drawable.gray;
        if (presence.equals("dnd")) {
            availabilityImageResource = R.drawable.red;
        } else if (presence.equals("default")) {
            availabilityImageResource = R.drawable.green;
        }
        ((ImageView) findViewById(R.id.availability_image)).setImageResource(availabilityImageResource);
    }

    public void setJID(String JID) {
        ((TextView) findViewById(R.id.Roster_myjid)).setText(JID);
    }

    public void displayMyCurrentProfile(Activity c) {
        ImageView myImage = (ImageView) c.findViewById(R.id.Roster_myimage);
     //   new ImageResize().attachIcon(myImage,this);
        TextView textView2 = (TextView) c.findViewById(R.id.Roster_mystatus);
        textView2.setText(MyProfile.getInstance().getStatus());
        ImageView imageView = (ImageView) c.findViewById(R.id.availability_image);
        String avail = MyProfile.getInstance().getAvailability();
        if(avail.equals("Available") || avail.equals("chat"))
            imageView.setImageResource(R.drawable.green);
        else if(avail.equals("away"))
            imageView.setImageResource(R.drawable.yellow);
        else if(avail.equals("dnd") || avail.equals("Busy"))
            imageView.setImageResource(R.drawable.red);
        else
            imageView.setImageResource(R.drawable.gray);
    }
        protected Dialog onCreateDialog(int id) {
        if(id==1){
            AddRosterDialog dialog = new AddRosterDialog(this);
            dialog.setContentView(R.layout.roster_add_dialog);
            dialog.setTitle("Add Your Friend");
            return dialog;
        }
        else if(id==2) {
            AddStatusDialog dialog = new AddStatusDialog(this);
            dialog.setContentView(R.layout.rostet_add_status);
            dialog.setTitle("Set Status");
            return dialog;
        }
        else if(id==3) {
            SearchRosterEntryDialog dialog = new SearchRosterEntryDialog(this);
            dialog.setContentView(R.layout.roster_search_entry);
            dialog.setTitle("Find Ur Friend");
            return dialog;
        }
        Log.d("ROSTER : ","invalid request for dialog");
        return null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.runFinalizersOnExit(true);
        RosterManager.flush();
    }
    public void displayVCard(VCard vCard) {
        RosterManager rosterManager = RosterManager.getInstance();
        rosterManager.updatePhoto(vCard);
    }
}


