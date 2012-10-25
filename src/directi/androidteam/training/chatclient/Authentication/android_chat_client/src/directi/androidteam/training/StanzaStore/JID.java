package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.StanzaStore;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/11/12
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class JID {
    private static String jid = "consttructor";
    private JID () {
    }
    public static String getJid() {
        return jid;
    }

    public static String getBareJid() {
        return jid.split("@")[0];
    }

    public static void setJID(String content) {
        Log.d("jid set","jid is set here");
        if(content!=null)
            Log.d("jid set","jid is " + content);
        jid = content;
    }
}
