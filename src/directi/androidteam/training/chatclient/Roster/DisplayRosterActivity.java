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
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;
import directi.androidteam.training.chatclient.Authentication.UserDatabaseHandler;
import directi.androidteam.training.chatclient.Authentication.UserListActivity;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.eventHandlers.AddRosterDialog;
import directi.androidteam.training.chatclient.Roster.eventHandlers.AddStatusDialog;
import directi.androidteam.training.chatclient.Roster.eventHandlers.SearchRosterEntryDialog;
import directi.androidteam.training.chatclient.Roster.util.ImageResize;
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
    private RosterItemAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        /*TODO Replace this with fetching your own vCard from persistent storage and displaying accordingly*/
        TextView textView = (TextView) findViewById(R.id.Roster_myjid);
        textView.setText(MyProfile.getInstance().getBareJID());
        TextView textView2 = (TextView) findViewById(R.id.Roster_mystatus);
        textView2.setText(MyProfile.getInstance().getStatus());
        (new RequestRoster(this)).execute();
        (new SendPresence(this)).execute();
        this.adapter = new RosterItemAdapter(this);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView view, View v, int position, long id) {
        RosterEntry rosterEntry = (RosterEntry) view.getItemAtPosition(position);
        Intent intent = new Intent(ChatApplication.getAppContext(), ChatBox.class);
        intent.putExtra("buddyid", rosterEntry.getJid());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void updateRosterList(final ArrayList<RosterEntry> rosterList) {
        this.adapter.setRosterEntries(rosterList);
        this.adapter.notifyDataSetChanged();
   }

    public void displayMyCurrentProfile(Activity c) {
        ImageView myImage = (ImageView) c.findViewById(R.id.Roster_myimage);
        new ImageResize().attachIcon(myImage,this);
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
    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.d("ROSTER INTENT :", "New Intent Started");
    }
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
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
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.menu_search) {
            Intent intent = new Intent(ChatApplication.getAppContext(),SearchRosterEntry.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(intent);
        }
        else if(menuItem.getItemId() == R.id.menu_add_contact) {
            showDialog(1);
        } else if (menuItem.getItemId() == R.id.logout) {
            PresenceS presenceS = new PresenceS();
            presenceS.addType("unavailable");
            PacketWriter.addToWriteQueue(presenceS.getXml());
            UserDatabaseHandler db = new UserDatabaseHandler(this);
            db.updateState(ConnectGTalk.username, "offline");
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);
            this.finish();
        }
        else return super.onOptionsItemSelected(menuItem);
        return true;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("oncreate", "rosterdisplay destroyed");
        System.runFinalizersOnExit(true);
        RosterManager.flush();
        System.exit(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("oncreate","menu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rostermenu, menu);
        return true;
    }
//    public void OnClick(View view) {
//        int id = view.getId();
//        if( id == R.id.roster_availability_launch_spinner_button) {
//            Log.d("spinner clicked","happening?");
//            Spinner spinner = (Spinner) findViewById(R.id.roster_availability_spinner);
//            spinner.performClick();
//        }
//        else if(id == R.id.Roster_search) {
//            Intent intent = new Intent(ChatApplication.getAppContext(),SearchRosterEntry.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            context.startActivity(intent);
//        }
//        else if(id == R.id.Roster_add) {
//            showDialog(1);
//        }
//        else if(id == R.id.Roster_mystatus) {
//            Log.d("ROSTER UI :","add status called");
//            showDialog(2);
//        }
//    }
    public void displayVCard(VCard vCard) {
        RosterManager rosterManager = RosterManager.getInstance();
        rosterManager.updatePhoto(vCard);
//        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.Roster_myimage);
//        imageView.setImageBitmap(vCard.getAvatar());
    }
}


