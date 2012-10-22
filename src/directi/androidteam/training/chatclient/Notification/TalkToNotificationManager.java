package directi.androidteam.training.chatclient.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import android.app.NotificationManager;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 26/9/12
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class TalkToNotificationManager {

    private static NotificationManager notificationManager ;

    Context notificationContext ;
    NotificationCompat.Builder  notificationBuilder ;
    private long [] DEFAULT_VIBRATION_PATTERN  = new long[] { 1000 , 1000 , 1000 , 1000 , 1000 } ;
    public static String NOTIFICATION_ID = "NOTIFICATION_ID" ;
    public static String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";

    public TalkToNotificationManager(Context currentContext) {
        notificationContext = currentContext;
        notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void setNotification ( int icon , String contentTitle , String contentText , String tickerText, int notificationID ) {
        notificationBuilder = new NotificationCompat.Builder(notificationContext);
        notificationBuilder.setSmallIcon(icon);
        notificationBuilder.setTicker(tickerText );
        notificationBuilder.setContentTitle(contentTitle );
        notificationBuilder.setContentText(contentText );
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setAutoCancel(true);

    }

    private void setTask(Intent targetIntent , Class homeActivityClass) {
        notificationBuilder.setContentIntent( TaskStackBuilder.create(notificationContext).addParentStack(homeActivityClass).addNextIntent(new Intent(notificationContext,homeActivityClass)).addNextIntent(targetIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    private void fireNotification(int notificationID) {
        Notification notification = notificationBuilder.build();
        notificationManager.notify("note"+notificationID,notificationID,notification);
        Log.d("fire notification" , "successfully exiting fire notification");
    }

    public void notify(MyNotification notification) {
        setNotification(notification.getIcon(), notification.getContentTitle(), notification.getContentText(), notification.getTickerText(), notification.getNotificationID());
        setTask(notification.getTargetIntent(), notification.getHomeActivityClass());
        fireNotification(notification.getNotificationID());
        Log.d("dispatch notification" , " successfully exiting dispatch notification");
    }
}