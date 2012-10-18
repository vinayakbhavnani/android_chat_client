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
