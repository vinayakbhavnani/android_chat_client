package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;

import java.util.HashMap;
import java.util.Map;

public class MessageNotificationHandler {

    private static int notificationID = 1 ;
    private Context context;
    private static final String LOGTAG = "MessageNotificationHandler";
    private HashMap messageMap;

    public MessageNotificationHandler(Context context) {
        this.context = context;
        messageMap = new HashMap();
    }

    public TalkToNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra(ChatBox.BUDDY_ID, messageSender);
        int ID;
        int times;
        if(messageMap.containsKey(messageSender) ) {
            times = (Integer) messageMap.remove(messageSender);
            times++;
            messageMap.put(messageSender,times);

        } else {
            times = 1;
            messageMap.put(messageSender,times);
        }
        times = messageMap.size();
        String contentTitle;
        if(times > 1) {
             contentTitle = "New messages from " + times + " contacts" ;
        }    else {
            contentTitle = messageSender;
            message = "Last message from " + messageSender + " : " + message ;
        }
        TalkToNotification notification = new TalkToNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,contentTitle ,message,messageSender + " : " + message,notificationID,times);
        Log.d(LOGTAG,"notification created successfully");
        return notification;
    }

}