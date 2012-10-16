package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.os.AsyncTask;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/8/12
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendStatusCumPresence extends AsyncTask<String, Void, Void> {
    public static Activity callerActivity;

    public SendStatusCumPresence(Activity parent) {
        callerActivity = parent;
    }

    @Override
    public Void doInBackground(String ...params) {
        String from = params[0];
        String status = params[1];
        String show = params[2];
        Tag queryTag = ((DisplayRosterActivity)callerActivity).getCurrentAccount().getQueryTag();
        queryTag.setAttribute("type", "set");
        queryTag.deleteAttribute("to");
        queryTag.deleteAttribute("from");
        queryTag.getChildTag("query").getChildTag("status").setContent(status);
        queryTag.getChildTag("query").getChildTag("show").setContent(show);
        queryTag.getChildTag("query").getChildTag("status-list", show).addChildTag(new directi.androidteam.training.TagStore.Status(status));
        PacketWriter.addToWriteQueue(queryTag.setRecipientAccount(from.split("/")[0]));
        return null;
    }
}
