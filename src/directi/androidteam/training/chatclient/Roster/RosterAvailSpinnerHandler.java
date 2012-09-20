package directi.androidteam.training.chatclient.Roster;

import android.view.View;
import android.widget.AdapterView;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/20/12
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterAvailSpinnerHandler implements AdapterView.OnItemSelectedListener {
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
            return;
        }
        myProfile.setAvailability(string);
        myProfile.setStatusAndPresence();
    }
}
