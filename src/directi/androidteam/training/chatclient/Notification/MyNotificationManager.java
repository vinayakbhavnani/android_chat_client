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
public class MyNotificationManager {

    private static NotificationManager notificationManager ;
    Notification notification ;

    public Context getNotificationContext() {
        return notificationContext;
    }

    Context notificationContext ;
    NotificationCompat.Builder  notificationBuilder ;
    private long [] DEFAULT_VIBRATION_PATTERN  = new long[] { 1000 , 1000 , 1000 , 1000 , 1000 } ;
    public static String NOTIFICATION_ID = "NOTIFICATION_ID" ;
    public static String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";

    public MyNotificationManager(Context currentContext) {
        notificationContext = currentContext;
        synchronized (this) {
            if(notificationManager == null ) {
             notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
            }
        }
    }

    public void setNotification ( int icon , String contentTitle , String contentText , String tickerText, int notificationID , int notificationType) {
        notificationBuilder = new NotificationCompat.Builder(notificationContext);
        notificationBuilder.setSmallIcon(icon);
        notificationBuilder.setTicker(tickerText );
        notificationBuilder.setContentTitle(contentTitle );
        notificationBuilder.setContentText(contentText );
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setAutoCancel(true);

    }

    public void setTask(Intent targetIntent , Class homeActivityClass) {
        notificationBuilder.setContentIntent( TaskStackBuilder.create(notificationContext).addParentStack(homeActivityClass).addNextIntent(new Intent(notificationContext,homeActivityClass)).addNextIntent(targetIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
    }


    public void markOngoing() {
        notificationBuilder.setOngoing(true);
    }

    public void fireNotification(int notificationID ,boolean sound , boolean vibrate , boolean ledFlash , Uri ringURI ,  long[] vibrationPattern , int ledColor) {
        Log.d("fire notification" ,"entered fire notification");
        int notDefaults = 0 ;
        if(sound) {
            notDefaults |= Notification.DEFAULT_SOUND ;
        }
        if(vibrate) {
            notDefaults |= Notification.DEFAULT_VIBRATE ;
        }
        if(ledFlash) {
            notDefaults |= Notification.DEFAULT_LIGHTS ;
        }
        notificationBuilder.setDefaults( notDefaults );
        notification = notificationBuilder.build();
        if(sound) {
            // Uri ringURI =  Uri.parse( "android.resource://com.directi.talkto.api.notification/"+ R.raw.barfi_whistle) ;  ]]
            if(ringURI == null) {
                ringURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
            notification.sound = ringURI ;
        }
        if(vibrate) {

            if(vibrationPattern == null) {
                notification.vibrate = DEFAULT_VIBRATION_PATTERN;
            } else {
                notification.vibrate = vibrationPattern ;
            }
        }
        if(ledFlash) {

            notification.ledARGB = ledColor ;
            notification.ledOffMS = 0 ;
            notification.ledOnMS = 1 ;
            notification.flags = notification.flags | Notification.FLAG_SHOW_LIGHTS ;
        }
        notificationManager.notify("note"+notificationID,notificationID,notification);
        Log.d("fire notification" , "successfully exiting fire notification");
    }
}