package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.os.AsyncTask;
import directi.androidteam.training.TagStore.Presence;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/2/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendPresence extends AsyncTask<String, Void, Void> {
    public static Activity callerActivity;

    public SendPresence(Activity parent) {
        callerActivity = parent;
    }

    @Override
    public Void doInBackground(String ...params) {
        String uid = params[0];
        String from = params[1];
        String status = params[2];
        String show = params[3];
        Presence presence = new Presence(UUID.randomUUID().toString(), from, status, show);
        PacketWriter.addToWriteQueue(presence.setRecipientAccount(uid));
        return null;
    }
}
