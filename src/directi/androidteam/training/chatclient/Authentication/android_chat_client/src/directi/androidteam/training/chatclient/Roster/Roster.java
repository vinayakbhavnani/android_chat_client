package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Roster;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/9/12
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Roster {
    private ArrayList<RosterItem> roster;
    private Comparator comparator;

    public Roster(Comparator comparator) {
        this.comparator = comparator;
        this.roster = new ArrayList<RosterItem>();
    }

    public RosterItem searchRosterItem(String bareJID) {
        for (RosterItem rosterItem : this.roster) {
            if (rosterItem.getBareJID().equals(bareJID)) {
                return rosterItem;
            }
        }
        return null;
    }

    private void deleteRosterItem(String bareJID) {
        int i = 0;
        for (i = 0; i < this.roster.size(); i++) {
            if (this.roster.get(i).getBareJID().equals(bareJID)) {
                break;
            }
        }
        this.roster.remove(i);
    }

    public void insertRosterItem(RosterItem rosterItem) {
        if (this.searchRosterItem(rosterItem.getBareJID()) != null) {
            this.deleteRosterItem(rosterItem.getBareJID());
        }
        int i = 0;
        while (i < this.roster.size() && this.comparator.compare(this.roster.get(i), rosterItem) < 0) {
            i++;
        }
        while (i < this.roster.size()) {
            rosterItem = this.roster.set(i, rosterItem);
            i++;
        }
        this.roster.add(rosterItem);
    }

    public ArrayList<RosterItem> getRoster() {
        return this.roster;
    }

    public ArrayList<RosterItem> searchRosterItems(String searchString) {
        ArrayList<RosterItem> rosterItemsResult = new ArrayList<RosterItem>();
        for (RosterItem rosterItem : this.roster) {
            if (rosterItem.getBareJID().contains(searchString))
                rosterItemsResult.add(rosterItem);
        }
        return rosterItemsResult;
    }
}
