package directi.androidteam.training.chatclient.Chat;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/4/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MsgGroupFormating {
    private MessageStanza last;
    private MessageStanza present;
    public MsgGroupFormating(MessageStanza lastMessageStanza, MessageStanza ms) {
        this.last = lastMessageStanza;
        this.present = ms;
    }
    public Boolean formatMsg() {
        Log.d("GGGG","format");
        if(last.getMsgMergedCount()>2) {
            Log.d("GGGGG","count more than 2");
            return false;
        }
        if(present.getTime() - last.getTime() < 10000)
            return true;
        else return false;
    }
}
