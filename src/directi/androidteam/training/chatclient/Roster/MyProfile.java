package directi.androidteam.training.chatclient.Roster;

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
    private MyProfile() {
        this.rosterEntry = new RosterEntry(JID.jid);
        this.rosterEntry.setPresence("chat");
        this.rosterEntry.setStatus("Set Status");
    }
    public void setStatusAndPresence(){
        PresenceS presence = new PresenceS();
        presence.addID(UUID.randomUUID().toString());
        String avail = rosterEntry.getPresence();
        presence.addAvailability(avail);
        presence.addID(UUID.randomUUID().toString());
        presence.addStatus(rosterEntry.getStatus());
        PacketWriter.addToWriteQueue(presence.getXml());
        DisplayRosterActivity.launchNewIntent();
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
        String m =  rosterEntry.getStatus();
        if(m==null || m.equals("")) {
            return "Set status";
        }
        else return m;
    }
    public String getAvailability() {
        return rosterEntry.getPresence();
    }
}
