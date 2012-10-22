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

    public static int notificationType ;

    private Intent targetIntent ;
    private Class homeActivityClass ;
    private int icon ;
    private String contentTitle ;
    private String contentText ;
    private String tickerText ;

    private int notificationID;

    public MyNotification() {

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

    public static int getNotificationType() {
        return notificationType;
    }

    public static void setNotificationType(int notificationType) {
        MyNotification.notificationType = notificationType;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
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

    public Intent getTargetIntent() {
        return targetIntent;
    }

    public String getTickerText() {
        return tickerText;

    }

    public int getNotificationID() {
        return notificationID;
    }

}
