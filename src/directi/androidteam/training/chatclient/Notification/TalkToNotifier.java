package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 12/10/12
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TalkToNotifier {

    private  MessageNotificationHandler messageHandler = null ;

    public TalkToNotifier(Context context) {
        synchronized(TalkToNotifier.class) {
            if(messageHandler == null) {
                messageHandler = new MessageNotificationHandler(context);
            }
        }
    }

    public void sendMessageNotification(String messageSender , String message )  {
        Log.d("TalkToNotifier" , "received request");
        messageHandler.sendNotification(messageSender,message);
        Log.d("TalkToNotifier","finished request");
    }

}
