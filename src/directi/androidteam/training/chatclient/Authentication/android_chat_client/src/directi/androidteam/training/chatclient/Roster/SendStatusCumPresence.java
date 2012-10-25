package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.os.AsyncTask;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Util.PacketWriter;

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
//        Log.d("gggggggg", (new XMLHelper()).buildPacket(queryTag.getChildTag("query").getChildTag("status-list", show)));
        queryTag.getChildTag("query").getChildTag("status-list", show).addChildTag(new directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore.Status(status));
//        Log.d("gggggggg", (new XMLHelper()).buildPacket(queryTag.getChildTag("query").getChildTag("status-list", show)));
//        Log.d("qqqqqqqqout", (new XMLHelper()).buildPacket(queryTag));
        PacketWriter.addToWriteQueue(queryTag.setRecipientAccount(from.split("/")[0]));
//        PacketWriter.addToWriteQueue("<iq id='psgfggmq9pmpnochq4h7i336vk' type='set'><query version='2' xmlns='google:shared-status'><show>default</show><status>Loving Talk.to for Android</status><invisible value='false'></invisible><status-list show='default'><status>Loving Talk.to for Android</status></status-list></query></iq>");
        return null;
    }
}
