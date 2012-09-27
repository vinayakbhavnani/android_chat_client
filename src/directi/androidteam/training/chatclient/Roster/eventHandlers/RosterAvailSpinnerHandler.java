package directi.androidteam.training.chatclient.Roster.eventHandlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;
import directi.androidteam.training.chatclient.Authentication.UserDatabaseHandler;
import directi.androidteam.training.chatclient.Authentication.UserListActivity;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;
import directi.androidteam.training.chatclient.Roster.MyProfile;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/20/12
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterAvailSpinnerHandler implements AdapterView.OnItemSelectedListener {
    private Activity activity;

    public RosterAvailSpinnerHandler(Context context) {
        this.activity = (Activity) context;
    }

    private void signOut() {
        UserDatabaseHandler db = new UserDatabaseHandler(this.activity);
        db.updateState(ConnectGTalk.username, "offline");
        db.close();
        Intent intent = new Intent(this.activity, UserListActivity.class);
        this.activity.startActivity(intent);
        this.activity.finish();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        String string = adapterView.getItemAtPosition(pos).toString();
        MyProfile myProfile = MyProfile.getInstance();
        if(string.equals("LogOut")){
            PresenceS presenceS = new PresenceS();
            presenceS.addType("unavailable");
            PacketWriter.addToWriteQueue(presenceS.getXml());
            signOut();
            return;
        }
        if(string.equals("Available"))
            string="chat";
        else if(string.equals("Busy"))
            string="dnd";
        myProfile.setAvailability(string);
        myProfile.setStatusAndPresence();
        DisplayRosterActivity.displayMyCurrentProfile(activity);
    }
}