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

    MyNotification notification ;

    public MessageNotificationHandler(Context context) {
        MyNotification notification = new MyNotification(context);
        notification.notificationType = MyNotification.TYPE_MESSAGE;
        notification.setNotificationID((100000 * MyNotification.TYPE_MESSAGE));
    }

    public void sendNotification(String messageSender,String message ) {
        notification.setIcon(R.drawable.ic_launcher);
        notification.setTickerText(messageSender + " : " + message);
        notification.setContentText(message);
        notification.setContentTitle("Talk.to");
        notification.setHomeActivityClass(DisplayRosterActivity.class);
        notification.setNotificationID(notification.getNotificationID() + 1);
        Intent targetIntent = new Intent(notification.getNotificationContext(),ChatBox.class);
        targetIntent.putExtra("buddyid", messageSender);
        targetIntent.putExtra("notification", true);
        targetIntent.putExtra("notificationID", notification.getNotificationID());
        notification.setTargetIntent(targetIntent);
        notification.dispatchNotification();
    }

}
