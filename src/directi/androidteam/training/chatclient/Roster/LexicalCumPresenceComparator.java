package directi.androidteam.training.chatclient.Roster;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/9/12
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class LexicalCumPresenceComparator implements Comparator<RosterItem> {
    @Override
    public int compare(RosterItem rosterItem1, RosterItem rosterItem2) {
        int presenceOrder = rosterItem1.getPresence().compareTo(rosterItem2.getPresence());
        if (presenceOrder == 0) {
            return rosterItem1.getBareJID().compareTo(rosterItem2.getBareJID());
        } else {
            return presenceOrder;
        }
    }
}
