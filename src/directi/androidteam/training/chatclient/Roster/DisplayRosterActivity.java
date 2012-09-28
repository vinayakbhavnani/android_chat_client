package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.eventHandlers.*;
import directi.androidteam.training.chatclient.Roster.util.ImageResize;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;
import java.util.UUID;

//import com.bugsense.trace.BugSenseHandler;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/3/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayRosterActivity extends Activity {
    private static RosterItemAdapter adapter;
    private static Context context;
    private static ArrayList<RosterEntry> rosterEntries = new ArrayList<RosterEntry>();
    public DisplayRosterActivity(){
        context =this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BugSenseHandler.initAndStartSession(this, Constants.BUGSENSE_API_KEY);
        Log.d("XXXX", "oncreate roster : ");
        setContentView(R.layout.roster);
        ImageView myImage = (ImageView) findViewById(R.id.Roster_myimage);
        new ImageResize().attachIcon(myImage,context);
        TextView textView = (TextView) findViewById(R.id.Roster_myjid);
        textView.setText(MyProfile.getInstance().getBareJID());
        TextView textView2 = (TextView) findViewById(R.id.Roster_mystatus);
        textView2.setText(MyProfile.getInstance().getStatus());
        Spinner spinner = (Spinner) findViewById(R.id.roster_availability_spinner);
        spinner.setOnItemSelectedListener(new RosterAvailSpinnerHandler(this));
        Button button = (Button) findViewById(R.id.roster_availability_launch_spinner_button);
        button.setBackgroundColor(Color.GREEN);
        ListView rosterList = (ListView) findViewById(R.id.rosterlist);
        rosterList.setOnItemClickListener(new rosterListClickHandler(rosterList,this));
        requestForRosters();
        sendInitialPresence();
        adapter = new RosterItemAdapter(context);
        updateRosterList(new ArrayList<RosterEntry>());
        rosterList.setAdapter(adapter);
    }

    private void sendInitialPresence() {
        PresenceS presenceS = new PresenceS();
        PacketWriter.addToWriteQueue(presenceS.getXml());
    }

    private void requestForRosters() {
        Log.d("ROSTER :","entered request for ROSTER_MANAGER");
        RosterGet rosterGet = new RosterGet();
        rosterGet.setSender(JID.getJid()).setID(UUID.randomUUID().toString()).setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext","2");
        PacketWriter.addToWriteQueue(rosterGet.getXml());
        Log.d("ROSTER :", "done requesting");
    }

    public static void updateRosterList(final ArrayList<RosterEntry> rosterList) {
        Activity a = (Activity) context;
        Log.d("ssss","updateroster called");
        a.runOnUiThread(new Runnable() {
            public void run() {
                adapter.setRosterEntries(rosterList);
                adapter.notifyDataSetChanged();
            }
        }
        );
        rosterEntries = rosterList;
   }
    public static void displayMyCurrentProfile(Activity c) {
        ImageView myImage = (ImageView) c.findViewById(R.id.Roster_myimage);
        new ImageResize().attachIcon(myImage,context);
        TextView textView2 = (TextView) c.findViewById(R.id.Roster_mystatus);
        textView2.setText(MyProfile.getInstance().getStatus());
        Button button = (Button) c.findViewById(R.id.roster_availability_launch_spinner_button);
        String avail = MyProfile.getInstance().getAvailability();
        if(avail.equals("Available") || avail.equals("chat"))
            button.setBackgroundColor(Color.GREEN);
        else if(avail.equals("away"))
            button.setBackgroundColor(Color.YELLOW);
        else if(avail.equals("dnd") || avail.equals("Busy"))
            button.setBackgroundColor(Color.RED);
        else
            button.setBackgroundColor(Color.GRAY);
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
            AddRosterDialog dialog = new AddRosterDialog(context);
            dialog.setContentView(R.layout.roster_add_dialog);
            dialog.setTitle("Add Your Friend");
            return dialog;
        }
        else if(id==2) {
            AddStatusDialog dialog = new AddStatusDialog(context);
            dialog.setContentView(R.layout.rostet_add_status);
            dialog.setTitle("Set Status");
            return dialog;
        }
        else if(id==3) {
            SearchRosterEntryDialog dialog = new SearchRosterEntryDialog(context);
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
            context.startActivity(intent);
        }
        else if(menuItem.getItemId() == R.id.menu_add_contact) {
            showDialog(1);
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
    public void OnClick(View view) {
        int id = view.getId();
        if( id == R.id.roster_availability_launch_spinner_button) {
            Log.d("spinner clicked","happening?");
            Spinner spinner = (Spinner) findViewById(R.id.roster_availability_spinner);
            spinner.performClick();
        }
        else if(id == R.id.Roster_search) {
            Intent intent = new Intent(ChatApplication.getAppContext(),SearchRosterEntry.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if(id == R.id.Roster_add) {
            showDialog(1);
        }
        else if(id == R.id.Roster_mystatus) {
            Log.d("ROSTER UI :","add status called");
            showDialog(2);
        }
    }
}


