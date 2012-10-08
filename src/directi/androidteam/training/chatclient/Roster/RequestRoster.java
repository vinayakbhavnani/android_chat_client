package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.os.AsyncTask;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/2/12
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestRoster extends AsyncTask<Void, Void, Void> {
    public static Activity callerActivity;

    public RequestRoster(Activity parent) {
        callerActivity = parent;
    }

    @Override
    public Void doInBackground(Void ...voids) {
        RosterGet rosterGet = new RosterGet();
        rosterGet.setSender(JID.getJid()).setID(UUID.randomUUID().toString()).setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext", "2");
        PacketWriter.addToWriteQueue(rosterGet.getTag());
        return null;
    }
}
