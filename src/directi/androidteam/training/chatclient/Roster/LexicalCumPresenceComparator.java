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
    private PresenceComparator presenceComparator;

    public LexicalCumPresenceComparator() {
        this.presenceComparator = new PresenceComparator();
    }

    @Override
    public int compare(RosterItem rosterItem1, RosterItem rosterItem2) {
        int presenceOrder = this.presenceComparator.compare(rosterItem1, rosterItem2);
        if (presenceOrder == 0) {
            return rosterItem1.getBareJID().compareTo(rosterItem2.getBareJID());
        } else {
            return presenceOrder;
        }
    }
}
