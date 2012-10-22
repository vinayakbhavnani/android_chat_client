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
    public static String NOTIFICATION_ID = "NOTIFICATION_ID" ;
    public static String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";

    public TalkToNotificationManager(Context currentContext) {
        notificationContext = currentContext;
        notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void notify(TalkToNotification notification) {
        NotificationCompat.Builder notificationBuilder = setNotification(notification.getIcon(), notification.getContentTitle(), notification.getContentText(), notification.getTickerText(), notification.getNotificationID(),notification.getTargetIntent(), notification.getHomeActivityClass());
        fireNotification(notification.getNotificationID(),notificationBuilder);
        Log.d("dispatch notification" , " successfully exiting dispatch notification");
    }

    private NotificationCompat.Builder setNotification ( int icon , String contentTitle , String contentText , String tickerText, int notificationID ,Intent targetIntent , Class homeActivityClass) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(notificationContext);
        notificationBuilder.setSmallIcon(icon);
        notificationBuilder.setTicker(tickerText );
        notificationBuilder.setContentTitle(contentTitle );
        notificationBuilder.setContentText(contentText );
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent( TaskStackBuilder.create(notificationContext).addParentStack(homeActivityClass).addNextIntent(new Intent(notificationContext,homeActivityClass)).addNextIntent(targetIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        return notificationBuilder;
    }

    private void fireNotification(int notificationID,NotificationCompat.Builder notificationBuilder) {
        Notification notification = notificationBuilder.build();
        notificationManager.notify("note"+notificationID,notificationID,notification);
        Log.d("fire notification" , "successfully exiting fire notification");
    }

}