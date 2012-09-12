package directi.androidteam.training.chatclient.Roster;

import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterResult;
import directi.androidteam.training.TagStore.Tag;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/10/12
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Roster {
    public ArrayList<Tag> rosterList  = new ArrayList<Tag>();
    public static final Roster  roster = new Roster();
    private Roster() {
    }
    public static Roster getInstance(){
        return roster;
    }

    public void setRosterList(ArrayList<Tag> list){
        rosterList = list;
        Intent intent = new Intent();
    }
    public ArrayList getRosterList(){
        return rosterList;
    }
    public void displayRoster(RosterResult rosterResult){

        ArrayList<Tag> listOfRosters = rosterResult.getListOfRosters();
        for (Tag listOfRoster : listOfRosters) {
            Log.d("Roster : ", listOfRoster.getAttribute("jid"));
        }
    }
    public void addRosterEntry(Map<String,Object> rosterEntry){
        ;//RosterSet rosterSet = new RosterSet(JID.jid,,);
    }
    public void sendMyPresence(){
        PresenceS presence = new PresenceS();
    }
}
