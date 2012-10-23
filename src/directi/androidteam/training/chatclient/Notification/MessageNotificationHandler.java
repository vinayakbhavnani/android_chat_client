package directi.androidteam.training.chatclient.Notification;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;

import java.util.HashMap;
import java.util.Map;

public class MessageNotificationHandler  {

    private static int notificationID = 1 ;
    private Context context;
    private static final String LOGTAG = "MessageNotificationHandler";
    private HashMap idMap ;
    private HashMap messageMap;

    public MessageNotificationHandler(Context context) {
        this.context = context;
        idMap = new HashMap();
        messageMap = new HashMap();
    }

    public TalkToNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra(ChatBox.BUDDY_ID, messageSender);
        TalkToNotification notification;
        if(idMap.isEmpty() || (!idMap.containsKey(messageSender))  ) {
            notification = new TalkToNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,messageSender,message,messageSender + " : " + message,notificationID);
            idMap.put(messageSender, notificationID);
            messageMap.put(messageSender,1);
            notificationID++;
        } else {
            int ID = (Integer) idMap.get(messageSender);
            int times = (Integer) messageMap.remove(messageSender);
            times++;
            messageMap.put(messageSender,times);
            notification = new TalkToNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,messageSender + " (" + times + ")",message,messageSender + " : " + message,ID);
        }
        Log.d(LOGTAG,"notification created successfully");
        return notification;
    }

}
