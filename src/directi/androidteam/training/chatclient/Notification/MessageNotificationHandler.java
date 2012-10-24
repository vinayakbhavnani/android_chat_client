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
    private ArrayList<String> senderList;

    public MessageNotificationHandler(Context context) {
        this.context = context;
        senderList = new ArrayList<String>();
    }

    public TalkToNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra(ChatBox.BUDDY_ID, messageSender);
        int index =  senderList.indexOf(messageSender);
        if( index == -1) {
            senderList.add(0,messageSender);
        }
        int numberOfContacts = senderList.size();
        String contentTitle;
        if(numberOfContacts > 1) {
             contentTitle = "New messages from " + numberOfContacts + " contacts" ;
             int others = numberOfContacts - 1;
             if(others == 1) {
                 message = messageSender + " and " + senderList.get(1) ;
             } else {
                 message = messageSender + " and " + others + " other contacts " ;
             }
        }   else {
             contentTitle = messageSender;
        }
        TalkToNotification notification = new TalkToNotification(targetIntent,DisplayRosterActivity.class,R.drawable.ic_launcher,contentTitle ,message,messageSender + " : " + message,notificationID,numberOfContacts);
        Log.d(LOGTAG,"notification created successfully");
        return notification;
    }

}