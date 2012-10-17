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
import directi.androidteam.training.chatclient.Notification.CancellationReceiver;

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
        Intent deleteIntent = new Intent(CancellationReceiver.CANCEL);
        deleteIntent.putExtra(NOTIFICATION_ID,notificationID);
        deleteIntent.putExtra(NOTIFICATION_TYPE,notificationType);
        notificationBuilder.setDeleteIntent(TaskStackBuilder.create(notificationContext).addNextIntent(deleteIntent).getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT));
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setAutoCancel(true);

    }

    public void setTask(Class targetActivityClass , Class homeActivityClass ,Bundle targetBundle) {
        Log.d("setTask","inside");
        Intent targetIntent = new Intent(notificationContext,targetActivityClass);
        Log.d("setTask","target intent created");
        if(targetBundle == null) {
            Log.d("setTask" , "target bundle is null");
        } else {
        Set<String> keySet = targetBundle.keySet();
        Log.d("setTask","keyset obtained");
        if(keySet == null) {
            Log.d("setTask","keyset is null");
        } else {
        Iterator iter = keySet.iterator();
        Log.d("setTask","iterator obtained");
        while(iter.hasNext()) {
            Log.d("setTask","inside loop");
            String key =  (String) iter.next();
            Log.d("setTask" , "key obtained");
            Object obj =  targetBundle.get(key);
            Log.d("setTask","object obtained");
            if( obj.getClass().equals(ArrayList.class) ) {
                targetIntent.putCharSequenceArrayListExtra(key, (ArrayList<CharSequence>) obj);
            } else if ( obj.getClass().equals(double.class))  {
                targetIntent.putExtra(key,(Double) obj);
            }  else if ( obj.getClass().equals(int.class)) {
                targetIntent.putExtra(key, (Integer) obj) ;
                Log.d("setTask","integer object put in target intent" + ((Integer) obj));
            }   else if ( obj.getClass().equals(CharSequence.class)) {
                targetIntent.putExtra(key,(CharSequence) obj);
            }   else if ( obj.getClass().equals(char.class)) {
                targetIntent.putExtra(key,(Character) obj);
            }   else if ( obj.getClass().equals(Bundle.class)) {
                targetIntent.putExtra(key,(Bundle) obj) ;
            }   else if ( obj.getClass().equals(Parcelable[].class)) {
                targetIntent.putExtra(key,(Parcelable[]) obj);
            }   else if ( obj.getClass().equals(Serializable.class)) {
                targetIntent.putExtra(key,(Serializable) obj);
            }   else if ( obj.getClass().equals(int[].class)) {
                targetIntent.putExtra(key,(int []) obj);
            }   else if ( obj.getClass().equals(float.class)) {
                targetIntent.putExtra(key,(Float) obj);
            }   else if ( obj.getClass().equals(byte[].class)) {
                targetIntent.putExtra(key,(byte[]) obj);
            }   else if ( obj.getClass().equals(long[].class)) {
                targetIntent.putExtra(key,(long[]) obj);
            }   else if ( obj.getClass().equals(Parcelable.class)) {
                targetIntent.putExtra(key,(Parcelable) obj);
            }   else if ( obj.getClass().equals(float[].class)) {
                targetIntent.putExtra(key,(float[]) obj);
            }   else if ( obj.getClass().equals(long.class)) {
                targetIntent.putExtra(key,(Long) obj);
            }   else if ( obj.getClass().equals(String[].class)) {
                targetIntent.putExtra(key,(String[]) obj) ;
            }   else if ( obj.getClass().equals(boolean.class)) {
                targetIntent.putExtra(key,(Boolean) obj);
                Log.d("setTask","boolean object put in target intent");
            }   else if ( obj.getClass().equals(boolean[].class)) {
                targetIntent.putExtra(key,(boolean[]) obj );
            }   else if ( obj.getClass().equals(short.class)) {
                targetIntent.putExtra(key,(Short) obj) ;
            }   else if ( obj.getClass().equals(double.class)) {
                targetIntent.putExtra(key,(Double) obj);
            }   else if ( obj.getClass().equals(short[].class)) {
                targetIntent.putExtra(key,(short[]) obj);
            }   else if ( obj.getClass().equals(String.class)) {
                targetIntent.putExtra(key,(String) obj) ;
                Log.d("setTask" ,"string object put in target intent");
            }   else if ( obj.getClass().equals(byte.class)) {
                targetIntent.putExtra(key,(Byte) obj);
            }   else if ( obj.getClass().equals(char[].class)) {
                targetIntent.putExtra(key,(char[]) obj);
            }   else if ( obj.getClass().equals(CharSequence[].class)) {
                targetIntent.putExtra(key,(CharSequence[]) obj);
            }
            Log.d("setTask","finishing loop");
        }

        }
        }
        Log.d("setTask","entering last line");
        notificationBuilder.setContentIntent( TaskStackBuilder.create(notificationContext).addParentStack(homeActivityClass).addNextIntent(new Intent(notificationContext,homeActivityClass)).addNextIntent(targetIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        Log.d("setTask","should successfully exit");
    }


    public void markOngoing() {
        notificationBuilder.setOngoing(true);
    }

    public void removeNotification(int notificationID) {
        notificationManager.cancel("note"+notificationID,notificationID);
        Log.d("removeNotification" ,"notification cancelled");
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

        Log.d("fire notification","prepared to call fire notification");
        notificationManager.notify("note"+notificationID,notificationID,notification);
        Log.d("fire notification" , "successfully exiting fire notification");
    }
}