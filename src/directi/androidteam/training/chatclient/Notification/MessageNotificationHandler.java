package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageNotificationHandler {

    private static int notificationID = 1 ;
    private Context context;
    private static final String LOGTAG = "MessageNotificationHandler";
    private ArrayList senderList;

    public MessageNotificationHandler(Context context) {
        this.context = context;
        senderList = new ArrayList();
    }

    public TalkToNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra(ChatBox.BUDDY_ID, messageSender);
        if(!senderList.contains(messageSender) ) {
            senderList.add(messageSender);
        }
        int times = senderList.size();
        String contentTitle;
        if(times > 1) {
             contentTitle = "New messages from " + times + " contacts" ;
            message = "Last message from " + messageSender + " : " + message ;
        }    else {
            contentTitle = messageSender;
        }
        TalkToNotification notification = new TalkToNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,contentTitle ,message,messageSender + " : " + message,notificationID,times);
        Log.d(LOGTAG,"notification created successfully");
        return notification;
    }

}