package directi.androidteam.training.chatclient.Roster;

import android.util.Log;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/19/12
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyProfile {
    private RosterEntry rosterEntry;
    private static MyProfile myProfile = new MyProfile();
    private String bareJID;
    private MyProfile() {
        String jid  = JID.getJid();
        rosterEntry = new RosterEntry(jid);
        rosterEntry.setPresence("chat");
        rosterEntry.setStatus("Set Status");
        if(jid!=null)
        bareJID = jid.split("/")[0];
        else
            bareJID = "me";
    }
    public void setStatusAndPresence(){
        PresenceS presence = new PresenceS();
        presence.addID(UUID.randomUUID().toString());
        String avail = getAvailability();
        presence.addAvailability(avail);
        presence.addID(UUID.randomUUID().toString());
        presence.addStatus(getStatus());
        Log.d("asdfasdf", presence.getXml());
        PacketWriter.addToWriteQueue(presence.getXml());
    }
    public String getBareJID() {
        return bareJID;
    }
    public void setStatus(String status) {
        rosterEntry.setStatus(status);
    }
    public void setAvailability(String avail){
        rosterEntry.setPresence(avail);
    }
    public static MyProfile getInstance() {
        return myProfile;
    }
    public String getStatus() {
        return  rosterEntry.getStatus();
    }
    public String getAvailability() {
        return rosterEntry.getPresence();
    }
}
