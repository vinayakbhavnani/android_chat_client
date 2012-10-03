package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.os.AsyncTask;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/2/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendPresence extends AsyncTask<Void, Void, Void> {
    public static Activity callerActivity;

    public SendPresence(Activity parent) {
        callerActivity = parent;
    }

    @Override
    public Void doInBackground(Void ...voids) {
        PresenceS presenceS = new PresenceS();
        PacketWriter.addToWriteQueue(presenceS.getTag());
        return null;
    }
}
