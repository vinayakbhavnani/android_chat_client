package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Roster;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/9/12
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class LexicographicComparator implements Comparator<RosterItem> {
    @Override
    public int compare(RosterItem rosterItem1, RosterItem rosterItem2) {
        return rosterItem1.getBareJID().compareTo(rosterItem2.getBareJID());
    }
}
