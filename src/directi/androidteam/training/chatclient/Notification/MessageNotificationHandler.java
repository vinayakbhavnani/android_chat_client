package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;

/**
 * Created with IntelliJ IDEA.
 * User: vineet
 * Date: 11/10/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageNotificationHandler extends NotificationHandler {

    private static boolean messageFlag = false;
    private String messageSender ;
    private String message ;

    public MessageNotificationHandler(Context context) {
        super(context);
        synchronized(this) {
            if(notificationManager == null ) {
                notificationManager = new MyNotificationManager(context);
            }
        } ;
        notificationType = NotificationHandler.TYPE_MESSAGE;
        setNotificationID( ( 100000 * NotificationHandler.TYPE_MESSAGE)  );
    }

    public void sendNotification( ) {
        setIcon(R.drawable.ic_launcher);
        setTickerText(messageSender + " : " + message);
        setContentText(message);
        setContentTitle("Talk.to");
        setTargetActivityClass(ChatBox.class);
        setHomeActivityClass(DisplayRosterActivity.class);
        setSound(true);
        setVibrate(false );
        setLedFlash(false);
        setNotificationID(getNotificationID()+1);
        Bundle bundle = new Bundle(3);
        bundle.putString("buddyid",messageSender);
        bundle.putBoolean("notification",true);
        bundle.putInt("notificationID",getNotificationID());
        setTargetBundle(bundle);
        if(getTargetBundle() == null) {
            Log.d("send notification","target bundle is null");
        } else {
            Log.d("send notification" , "target bundle is not null");
        }
    }

    public void cancelNotification(int notificationID) throws NoNotificationToCancelException {
         backtrackNotification(notificationID,NotificationHandler.TYPE_MESSAGE);
    }

    public void cancelAllNotification() {
        Log.d("cancelAllNotification","entered");
        int notificationID = getNotificationID();
        while( notificationID > 100000) {
            try {
                cancelNotification(notificationID );
                setNotificationID(getNotificationID()-1);
            } catch ( NoNotificationToCancelException nntce){
                Log.d("cancel all notification " ,"this notification was already cancelled " + notificationID);
            }
            notificationID = getNotificationID();
        }
        Log.d("cancelAllNotification","exiting");
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
