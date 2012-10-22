package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.util.Log;

public class TalkToNotifier {

    private  MessageNotificationHandler messageHandler ;
    private TalkToNotificationManager notificationManager;
    private static  TalkToNotifier instance;

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

    public void sendMessageNotification(String messageSender , String message )  {
        Log.d("TalkToNotifier" , "received request");
        TalkToNotification notification = messageHandler.getNotification(messageSender, message);
        notificationManager.notify(notification);
        Log.d("TalkToNotifier","finished request");
    }

}
