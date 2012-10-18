package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 12/10/12
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationWrapper {

    private static MessageNotificationHandler messageHandler = null ;

    public static void sendMessageNotification(Context context , String messageSender , String message ) throws IncompleteNotificationException {
        Log.d("message notification","entered");
         if( (context == null) || (messageSender == null ) || (message == null )  ) {
             throw new IncompleteNotificationException("Incomplete specification of notification parameters ");
         }
        if ((messageSender.equalsIgnoreCase("")) || (message.equalsIgnoreCase(""))  ) {
            throw new IncompleteNotificationException("Incomplete specification of notification parameters ");
        }
        synchronized(NotificationWrapper.class) {
            if(messageHandler == null) {
                messageHandler = new MessageNotificationHandler(context);
            }
        }
        messageHandler.setMessageSender(messageSender);
        messageHandler.setMessage(message);
        Log.d("message notification","prepared to send notification ( setting options) ");
        messageHandler.sendNotification();
        Log.d("message notification","prepared to dispatched notification ( applying options");
        messageHandler.dispatchNotification();
        Log.d("message notification" , "successfully exiting dispatch notification");
    }

}
