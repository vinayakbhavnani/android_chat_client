package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.content.Intent;
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
public class MessageNotificationHandler  {

    static int notificationID = 100000 * MyNotification.TYPE_MESSAGE;

    public MessageNotificationHandler() {
    }

    public MyNotification setNotification(Context context,String messageSender,String message ) {
        MyNotification notification = new MyNotification();
        notification.notificationType = MyNotification.TYPE_MESSAGE;
        notificationID++;
        if(notificationID == 1000000) {
            notificationID =  100000 * MyNotification.TYPE_MESSAGE;
        }
        notification.setNotificationID(notificationID);
        notification.setIcon(R.drawable.ic_launcher);
        notification.setTickerText(messageSender + " : " + message);
        notification.setContentText(message);
        notification.setContentTitle("Talk.to");
        notification.setHomeActivityClass(DisplayRosterActivity.class);
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra("buddyid", messageSender);
        targetIntent.putExtra("notification", true);
        targetIntent.putExtra("notificationID", notification.getNotificationID());
        notification.setTargetIntent(targetIntent);
        return notification;
    }

}
