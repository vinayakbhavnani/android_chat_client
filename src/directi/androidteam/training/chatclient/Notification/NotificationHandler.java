package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 9/10/12
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class NotificationHandler {

    /*
    Maintain notification ids .
     */

    private static int numberOfNotifications ;
    private static Context notificationContext ;

    protected MyNotificationManager notificationManager ;
    protected static int notificationType ;

    protected static final int TYPE_MESSAGE = 0 ;
    protected static final int TYPE_ERROR = 1;
    protected static final  int TYPE_ROSTER_STATUS = 2;
    protected static final int TYPE_ROSTER_REQUEST = 3;
    protected static final int TYPE_ONGOING = 4;

    private Class targetActivityClass ;
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
    private Bundle targetBundle;

    private int notificationID;

    public NotificationHandler(Context context) {
         notificationContext = context.getApplicationContext() ;
    }

    public abstract void sendNotification() ;

    public abstract void cancelNotification(int notificationID) throws NoNotificationToCancelException ;

    public void dispatchNotification() {
        notificationManager.setNotification(icon , contentTitle , contentText , tickerText ,notificationID, notificationType);
        if(targetBundle == null) {
            Log.d("dispatchNotification","target bundle is null" );
        } else {
            Log.d("dispatchNotification","target bundle is not null");
        }
        notificationManager.setTask(targetActivityClass , homeActivityClass , targetBundle );
        Log.d("dispatch notification" , " finished setting task");
        notificationManager.fireNotification(notificationID , sound , vibrate , ledFlash , ringURI , vibrationPattern , ledColor );
        Log.d("dispatch notification" , " finished firing notification");
        if(notificationType == TYPE_ONGOING ) {
            notificationManager.markOngoing();
        }
        Log.d("dispatch notification" , " successfully exiting dispatch notification");
    }

    public static void backtrackNotification(int notificationID,int notificationType) throws NoNotificationToCancelException {
        if(notificationType == TYPE_MESSAGE) {
            if(notificationContext != null) {
              Log.d("backtrackNotification","notificationContext is not null");
              new MyNotificationManager(notificationContext).removeNotification(notificationID);
            } else {
                throw new NoNotificationToCancelException("No pending notifications present");
            }
        }
    }

    public static void setNumberOfNotifications(int numberOfNotifications) {
        NotificationHandler.numberOfNotifications = numberOfNotifications;
    }

    public void setTargetActivityClass(Class targetActivityClass) {
        this.targetActivityClass = targetActivityClass;
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

    public Class getTargetActivityClass() {
        return targetActivityClass;
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

    public Bundle getTargetBundle() {
        return targetBundle;
    }

    public void setTargetBundle(Bundle targetBundle) {
        this.targetBundle = targetBundle;
    }

}
