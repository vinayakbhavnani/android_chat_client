package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.eventHandlers.*;
import directi.androidteam.training.chatclient.Roster.util.ImageResize;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;
import java.util.UUID;

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
    public DisplayRosterActivity(){
        context =this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RosterManager.flush();
        setContentView(R.layout.roster);
        ImageView myImage = (ImageView) findViewById(R.id.Roster_myimage);
        new ImageResize().attachIcon(myImage,context);
        TextView textView = (TextView) findViewById(R.id.Roster_myjid);
        textView.setText(JID.jid);
        TextView textView2 = (TextView) findViewById(R.id.Roster_mystatus);
        textView2.setText(MyProfile.getInstance().getStatus());
        Spinner spinner = (Spinner) findViewById(R.id.roster_availability_spinner);
        spinner.setOnItemSelectedListener(new RosterAvailSpinnerHandler(this));
        Button button = (Button) findViewById(R.id.roster_availability_launch_spinner_button);
        button.setBackgroundColor(Color.GREEN);
        Log.d("XXXX", "oncreate roster : " + MyProfile.getInstance().getStatus());
        ListView rosterList = (ListView) findViewById(R.id.rosterlist);
        rosterList.setOnItemClickListener(new rosterListClickHandler(rosterList,this));
        requestForRosters();
        sendInitialPresence();
        adapter = new RosterItemAdapter(context);
        updateRosterList(new ArrayList<RosterEntry>());
        rosterList.setAdapter(adapter);
        rosterList.setTextFilterEnabled(true);
    }

    private void sendInitialPresence() {
        PresenceS presenceS = new PresenceS();
        PacketWriter.addToWriteQueue(presenceS.getXml());
    }

    private void requestForRosters() {
        Log.d("ROSTER :","entered request for ROSTER_MANAGER");
        RosterGet rosterGet = new RosterGet();
        rosterGet.setSender(JID.jid).setID(UUID.randomUUID().toString()).setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext","2");
        PacketWriter.addToWriteQueue(rosterGet.getXml());
        Log.d("ROSTER :", "done requesting");
    }

    private void requestForPresence(String typeVal) {
        RosterManager rosterManager = RosterManager.getInstance();
        for (RosterEntry rosterEntry : rosterManager.getRosterList()) {
            PresenceS presence = new PresenceS();
            presence.addID(UUID.randomUUID().toString());
            presence.addReceiver(rosterEntry.getJid());
            presence.addType(typeVal);
            PacketWriter.addToWriteQueue(presence.getXml());
            Log.d("ROSTER :", "entered request for presence");
        }
    }

    public static void updateRosterList(final ArrayList<RosterEntry> rosterList) {
        Activity a = (Activity) context;
        Log.d("ssss","updateroster called");
        a.runOnUiThread(new Runnable() {   public void run() {
            adapter.setRosterEntries(rosterList);
            adapter.notifyDataSetChanged();
        }}
            );
   }
    public static void launchNewIntent() {
        Intent intent = new Intent(context,DisplayRosterActivity.class);
        Log.d("XXXX","show AllRosters Called");
        intent.putExtra("display", "all");
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
    private void displayMyCurrentProfile() {
        ImageView myImage = (ImageView) findViewById(R.id.Roster_myimage);
        new ImageResize().attachIcon(myImage,context);
        TextView textView2 = (TextView) findViewById(R.id.Roster_mystatus);
        textView2.setText(MyProfile.getInstance().getStatus());
        Button button = (Button) findViewById(R.id.roster_availability_launch_spinner_button);
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
        displayMyCurrentProfile();
        Log.d("ROSTER INTENT :", "New Intent Started");
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
    public void addRosterEntry(View view){
        showDialog(1);
    }
    public void addStatus(View view) {
        Log.d("ROSTER UI :","add status called");
        showDialog(2);
    }
    public void searchRosterEntry(View view) {
        Log.d("ROSTER UI :","roster search called");
        showDialog(3);
    }
    public void launchSpinner(View view) {
        Log.d("spinner clicked","happening?");
            Spinner spinner = (Spinner) findViewById(R.id.roster_availability_spinner);
              spinner.performClick();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
}


