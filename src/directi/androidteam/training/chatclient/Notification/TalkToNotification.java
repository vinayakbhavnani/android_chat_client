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
public class TalkToNotification {

    private Intent targetIntent ;
    private Class homeActivityClass ;
    private int icon ;
    private String contentTitle ;
    private String contentText ;
    private String tickerText ;

    private int notificationID;

    public TalkToNotification(Intent targetIntent, Class homeActivityClass, int icon, String contentTitle, String contentText, String tickerText) {
        this.targetIntent = targetIntent;
        this.homeActivityClass = homeActivityClass;
        this.icon = icon;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.tickerText = tickerText;
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
