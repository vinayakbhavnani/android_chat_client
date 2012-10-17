package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.os.AsyncTask;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.Authentication.AccountManager;
import directi.androidteam.training.chatclient.Authentication.LoginStatus;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/2/12
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestRoster extends AsyncTask<Void, Void, Void> {
    public static Activity callerActivity;

    public RequestRoster(Activity parent) {
        callerActivity = parent;
    }

    public void sendFetchRosterRequest(String uid, String jid) {
        RosterGet rosterGet = new RosterGet();
        rosterGet.setSender(jid).setID(UUID.randomUUID().toString()).setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext", "2");
        PacketWriter.addToWriteQueue(rosterGet.getTag().setRecipientAccount(uid));
    }
    @Override
    public Void doInBackground(Void ...voids) {
        DisplayRosterActivity.setAccounts(AccountManager.getInstance().getAccountList());
        for (int i = 0; i < DisplayRosterActivity.getAccounts().size(); i++) {
            if (DisplayRosterActivity.getAccounts().get(i).isLoginStatus().equals(LoginStatus.ONLINE)) {
                sendFetchRosterRequest(DisplayRosterActivity.getAccounts().get(i).getAccountUid(), DisplayRosterActivity.getAccounts().get(i).getBareJID());
            }
        }
        return null;
    }
}
