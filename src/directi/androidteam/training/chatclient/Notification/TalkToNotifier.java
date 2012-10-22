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

    private static MessageNotificationHandler messageHandler = null ;

    public static void sendMessageNotification(Context context ,MessageStanza stanza ) throws IncompleteNotificationException {
        Log.d("TalkToNotifier" , "received request");
        String messageSender = stanza.getFrom();
        String message = stanza.getBody();
         if( (context == null) || (messageSender == null ) || (message == null )  ) {
             throw new IncompleteNotificationException("Incomplete specification of notification parameters ");
         }
        if ((messageSender.equalsIgnoreCase("")) || (message.equalsIgnoreCase(""))  ) {
            throw new IncompleteNotificationException("Incomplete specification of notification parameters ");
        }
        synchronized(TalkToNotifier.class) {
            if(messageHandler == null) {
                messageHandler = new MessageNotificationHandler(context,messageSender,message);
            }
        }
        messageHandler.sendNotification();
        Log.d("TalkToNotifier","finished request");
    }

}
