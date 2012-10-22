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

    private TalkToNotifier(Context context) {
          messageHandler = new MessageNotificationHandler(context);
          notificationManager = new TalkToNotificationManager(context);
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
        notificationManager.notify(notification);
        Log.d("TalkToNotifier","finished request");
    }

}
