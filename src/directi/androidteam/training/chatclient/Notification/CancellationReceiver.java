package directi.androidteam.training.chatclient.Notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 9/10/12
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class CancellationReceiver extends BroadcastReceiver {

    public static final String CANCEL = "directi.androidteam.training.chatclient.Notification.CancellationReceiver.CANCEL";

    public void onReceive(Context context , Intent intent ) {
        Bundle extrasBundle = intent.getExtras() ;
        int notificationID = (Integer) extrasBundle.get(TalkToNotificationManager.NOTIFICATION_ID);
        if(notificationID == MessageNotificationHandler.getNotificationID()) {
            TalkToNotifier ttn = TalkToNotifier.getInstance(context);
            ttn.cancelAllMessageNotifications();
        }

    }
}
