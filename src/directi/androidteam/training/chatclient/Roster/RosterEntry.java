package directi.androidteam.training.chatclient.Roster;

import directi.androidteam.training.StanzaStore.JID;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/12/12
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterEntry {
    public JID jid;
    public ArrayList<RosterGroup> rosterGroups;
    public String status;
    public String presence;

    public RosterEntry(JID jid) {
        this.jid = jid;
        rosterGroups = null;
        status="";
        presence="";
        RosterGroupManager rosterGroupManager = RosterGroupManager.getInstance();
        RosterGroup rosterGroup = rosterGroupManager.getRosterGroupByName("default");
        this.addRosterGroup(rosterGroup);
    }

    public RosterEntry(JID jid, ArrayList<RosterGroup> rosterGroups, String status, String presence) {
        this.jid = jid;
        this.rosterGroups = rosterGroups;
        this.status = status;
        this.presence = presence;
    }

    public JID getJid() {
        return jid;
    }

    public ArrayList<RosterGroup> getRosterGroups() {
        return rosterGroups;
    }

    public String getStatus() {
        return status;
    }

    public String getPresence() {
        return presence;
    }

    public void setRosterGroups(ArrayList<RosterGroup> rosterGroups) {
        this.rosterGroups = rosterGroups;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public void addRosterGroup(RosterGroup rosterGroup) {
        if(rosterGroups==null)
            rosterGroups = new ArrayList<RosterGroup>();
        rosterGroups.add(rosterGroup);
    }
}
