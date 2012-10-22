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
public class TalkToNotifier {

    private  MessageNotificationHandler messageHandler ;
    private TalkToNotificationManager notificationManager;
    private static  TalkToNotifier instance;

    public TalkToNotifier(Context context) {
        synchronized(TalkToNotifier.class) {
            if(messageHandler == null) {
                messageHandler = new MessageNotificationHandler(context);
            }
        }
        synchronized(TalkToNotifier.class) {
            if(notificationManager == null) {
                notificationManager = new TalkToNotificationManager(context);
            }
        }
    }

    public static TalkToNotifier getInstance(Context context) {
        synchronized(TalkToNotifier.class ) {
            if(instance == null) {
                 instance = new TalkToNotifier(context);
            }
        };
            return instance;
    }

    public void sendMessageNotification(String messageSender , String message )  {
        Log.d("TalkToNotifier" , "received request");
        MyNotification notification = messageHandler.getNotification(messageSender, message);
        dispatchNotification(notification);
        Log.d("TalkToNotifier","finished request");
    }

    private void dispatchNotification(MyNotification notification) {
        notificationManager.setNotification(notification.getIcon(), notification.getContentTitle(), notification.getContentText(), notification.getTickerText(), notification.getNotificationID(), notification.getNotificationType());
        notificationManager.setTask(notification.getTargetIntent(), notification.getHomeActivityClass());
        notificationManager.fireNotification(notification.getNotificationID());
        Log.d("dispatch notification" , " successfully exiting dispatch notification");
    }
}
