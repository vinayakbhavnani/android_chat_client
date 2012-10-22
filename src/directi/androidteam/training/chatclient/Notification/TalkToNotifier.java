package directi.androidteam.training.chatclient.Notification;

import android.app.Notification;
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

    private  MessageNotificationHandler messageHandler ;
    MyNotificationManager notificationManager;

    public TalkToNotifier(Context context) {
        synchronized(TalkToNotifier.class) {
            if(messageHandler == null) {
                messageHandler = new MessageNotificationHandler();
            }
        }
        synchronized(TalkToNotifier.class) {
            if(notificationManager == null) {
                notificationManager = new MyNotificationManager(context);
            }
        }
    }

    public void sendMessageNotification(String messageSender , String message )  {
        Log.d("TalkToNotifier" , "received request");
        MyNotification notification = messageHandler.setNotification(notificationManager.getNotificationContext(),messageSender, message);
        dispatchNotification(notification);
        Log.d("TalkToNotifier","finished request");
    }

    private void dispatchNotification(MyNotification notification) {
        notificationManager.setNotification(notification.getIcon(), notification.getContentTitle(), notification.getContentText(), notification.getTickerText(), notification.getNotificationID(), notification.getNotificationType());
        notificationManager.setTask(notification.getTargetIntent() , notification.getHomeActivityClass() );
        notificationManager.fireNotification(notification.getNotificationID() , notification.isSound() , notification.isVibrate() , notification.isLedFlash() , notification.getRingURI() , notification.getVibrationPattern() , notification.getLedColor() );
        Log.d("dispatch notification" , " successfully exiting dispatch notification");
    }
}
