package directi.androidteam.training.chatclient.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class TalkToNotificationManager {

    private static NotificationManager notificationManager ;

    private Context notificationContext ;
    private static final String LOGTAG = "TalkToNotificationManager";
    private static final String NOTIFICATION_TAG = "note";

    public TalkToNotificationManager(Context currentContext) {
        notificationContext = currentContext;
        notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void notify(TalkToNotification notification) {
        Notification androidNotification = getNotification(notification.getIcon(), notification.getContentTitle(), notification.getContentText(), notification.getTickerText(), notification.getNotificationID(),notification.getTargetIntent(), notification.getHomeActivityClass());
        notificationManager.notify(NOTIFICATION_TAG+notification.getNotificationID(),notification.getNotificationID(),androidNotification);
        Log.d(LOGTAG , " successfully exiting notify");
    }

    private Notification getNotification ( int icon , String contentTitle , String contentText , String tickerText, int notificationID ,Intent targetIntent , Class homeActivityClass) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(notificationContext);
        notificationBuilder.setSmallIcon(icon);
        notificationBuilder.setTicker(tickerText );
        notificationBuilder.setContentTitle(contentTitle );
        notificationBuilder.setContentText(contentText );
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent( TaskStackBuilder.create(notificationContext).addParentStack(homeActivityClass).addNextIntent(new Intent(notificationContext,homeActivityClass)).addNextIntent(targetIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        Notification notification = notificationBuilder.build();
        Log.d(LOGTAG,"successfully created android notification") ;
        return notification;
    }

}