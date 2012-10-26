package directi.androidteam.training.chatclient.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
