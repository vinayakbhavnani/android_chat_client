package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.util.Log;

public class TalkToNotifier {

    private  MessageNotificationHandler messageHandler ;
    private TalkToNotificationManager notificationManager;
    private static  TalkToNotifier instance;
    private static final String LOGTAG = "TalkToNotifier";

    private TalkToNotifier(Context context) {
          messageHandler = new MessageNotificationHandler(context);
          notificationManager = new TalkToNotificationManager(context);
    }

    public static synchronized TalkToNotifier getInstance(Context context) {
        if(instance == null) {
            instance = new TalkToNotifier(context);
        }
        return instance;
    }

    public void sendMessageNotification(String messageSender , String message ,String accountUID)  {
        Log.d(LOGTAG , "received request");
        TalkToNotification notification = messageHandler.getNotification(messageSender, message,accountUID);
        notificationManager.notify(notification);
        Log.d(LOGTAG,"finished request");
    }

    public void cancelMessageNotification(String messageSender) {
        if(messageHandler.containsSender(messageSender)) {
            TalkToNotification notification = messageHandler.cancelNotification(messageSender);
            if(notification == null) {
                 notificationManager.cancelNotification(MessageNotificationHandler.getNotificationID());
            } else {
                 notificationManager.notify(notification);
            }
        }
    }

    public void cancelAllMessageNotifications() {
        messageHandler.cancelAllNotifications();
        notificationManager.cancelNotification(MessageNotificationHandler.getNotificationID());
    }

}
