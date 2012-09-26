package directi.androidteam.training.chatclient.Roster.eventHandlers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;
import directi.androidteam.training.chatclient.Authentication.UserDatabaseHandler;
import directi.androidteam.training.chatclient.Authentication.UserListActivity;
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

    public RosterAvailSpinnerHandler(Activity parent) {
        this.activity = parent;
    }

    public void signOut() {
        UserDatabaseHandler db = new UserDatabaseHandler(this.activity);
        db.updateState(ConnectGTalk.uname, "offline");
        db.close();
        Intent intent = new Intent(this.activity, UserListActivity.class);
        this.activity.startActivity(intent);
        this.activity.finish();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //To change body of implemented methods use File | Settings | File Templates.
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
            string="away";
        myProfile.setAvailability(string);
        myProfile.setStatusAndPresence();
    }
}
