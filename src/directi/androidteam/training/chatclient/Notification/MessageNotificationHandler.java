package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.content.Intent;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;

public class MessageNotificationHandler  {

    static int notificationID = 1 ;
    Context context;

    public MessageNotificationHandler(Context context) {
        this.context = context;
    }

    public TalkToNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra("buddyid", messageSender);
        TalkToNotification notification = new TalkToNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,messageSender,message,messageSender + " : " + message);
        notificationID++;

        return notification;
    }

}
