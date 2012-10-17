package directi.androidteam.training.chatclient.Notification;

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
        int notificationID = (Integer) extrasBundle.get(MyNotificationManager.NOTIFICATION_ID);
        int notificationType = (Integer) extrasBundle.get(MyNotificationManager.NOTIFICATION_TYPE );
        try {
            NotificationHandler.backtrackNotification(notificationID,notificationType);
        } catch ( NoNotificationToCancelException nntce) {
            nntce.printStackTrace() ;
        }
    }
}
