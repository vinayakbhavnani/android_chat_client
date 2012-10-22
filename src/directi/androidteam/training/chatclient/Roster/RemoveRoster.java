package directi.androidteam.training.chatclient.Roster;

import directi.androidteam.training.chatclient.Authentication.*;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/22/12
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveRoster implements Subscriber {
    private void removeRosterForAccount(Account account) {
        if (account.isLoginStatus().equals(LoginStatus.OFFLINE)) {
            RosterManager.getInstance().removeAccount(account.getAccountUid());
        }
    }

    @Override
    public void receivedNotification(PublicationType publicationType, String message) {
        if (publicationType.equals(PublicationType.ACCOUNT_STATE_CHANGED)) {
            Account account = AccountManager.getInstance().getAccount(message);
            removeRosterForAccount(account);
        }
    }
}
