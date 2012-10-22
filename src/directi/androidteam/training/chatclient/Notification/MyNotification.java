package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 9/10/12
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyNotification {

    /*
    Maintain notification ids .
     */

    private static int numberOfNotifications ;
    private static Context notificationContext ;

    public MyNotificationManager notificationManager ;
    public static int notificationType ;

    public static final int TYPE_MESSAGE = 0 ;

    private Intent targetIntent ;
    private Class homeActivityClass ;
    private int icon ;
    private String contentTitle ;
    private String contentText ;
    private String tickerText ;
    private boolean sound ;
    private Uri ringURI ;

    private boolean vibrate ;
    private long[] vibrationPattern ;
    private boolean ledFlash ;
    private int ledColor;

    private int notificationID;

    public MyNotification(Context context) {
         notificationContext = context ;
        synchronized(this) {
            if(notificationManager == null ) {
                notificationManager = new MyNotificationManager(context);
            }
        } ;
    }

    public void dispatchNotification() {
        notificationManager.setNotification(icon , contentTitle , contentText , tickerText ,notificationID, notificationType);
        notificationManager.setTask(targetIntent , homeActivityClass );
        notificationManager.fireNotification(notificationID , sound , vibrate , ledFlash , ringURI , vibrationPattern , ledColor );
        Log.d("dispatch notification" , " successfully exiting dispatch notification");
    }

    public static void setNumberOfNotifications(int numberOfNotifications) {
        MyNotification.numberOfNotifications = numberOfNotifications;
    }

    public void setTargetIntent(Intent targetIntent) {
        this.targetIntent = targetIntent;
    }

    public void setHomeActivityClass(Class homeActivityClass) {
        this.homeActivityClass = homeActivityClass;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setTickerText(String tickerText) {
        this.tickerText = tickerText;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public void setRingURI(Uri ringURI) {
        this.ringURI = ringURI;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public void setVibrationPattern(long[] vibrationPattern) {
        this.vibrationPattern = vibrationPattern;
    }

    public void setLedFlash(boolean ledFlash) {
        this.ledFlash = ledFlash;
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public static int getNumberOfNotifications() {

        return numberOfNotifications;
    }

    public static Context getNotificationContext() {
        return notificationContext;
    }

    public static void setNotificationContext(Context notificationContext) {
        MyNotification.notificationContext = notificationContext;
    }

    public Intent getTargetActivityClass() {
        return targetIntent;
    }

    public Class getHomeActivityClass() {
        return homeActivityClass;
    }

    public int getIcon() {
        return icon;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public String getTickerText() {
        return tickerText;
    }

    public boolean isSound() {
        return sound;
    }

    public Uri getRingURI() {
        return ringURI;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public long[] getVibrationPattern() {
        return vibrationPattern;
    }

    public boolean isLedFlash() {
        return ledFlash;
    }

    public int getLedColor() {
        return ledColor;
    }

    public int getNotificationID() {
        return notificationID;
    }

}
