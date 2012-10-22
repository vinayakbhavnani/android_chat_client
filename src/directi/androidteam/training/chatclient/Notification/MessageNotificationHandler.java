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

    static int notificationID = 100000 ;
    Context context;

    public MessageNotificationHandler(Context context) {
        this.context = context;
    }

    public MyNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra("buddyid", messageSender);
        MyNotification notification = new MyNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,messageSender,message,messageSender + " : " + message);
        notificationID++;
        if(notificationID == 1000000) {
            notificationID =  100000 ;
        }

        return notification;
    }

}
