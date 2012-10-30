package directi.androidteam.training.chatclient.Roster;

import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.Authentication.*;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/2/12
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestRoster implements Subscriber {
    private void requestRosterForAccount(Account account) {
        if (account.isLoginStatus().equals(LoginStatus.ONLINE)) {
            RosterGet rosterGet = new RosterGet();
            rosterGet.setSender(account.getFullJID()).setID(UUID.randomUUID().toString()).setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext", "2");
            PacketWriter.addToWriteQueue(rosterGet.getTag().setRecipientAccount(account.getAccountUid()));
        }
    }

    @Override
    public void receivedNotification(PublicationType publicationType, String message) {
        if (publicationType.equals(PublicationType.ACCOUNT_STATE_CHANGED)) {
            Account account = AccountManager.getInstance().getAccount(message);
            requestRosterForAccount(account);
        }
    }
}
