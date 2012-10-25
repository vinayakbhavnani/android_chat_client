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
    private ArrayList<Message> messageList;

    public MessageNotificationHandler(Context context) {
        this.context = context;
        messageList = new ArrayList<Message>();
    }

    public static int getNotificationID() {
        return notificationID;
    }

    public TalkToNotification getNotification(String messageSender,String message ) {
        Intent targetIntent = new Intent(context,ChatBox.class);
        targetIntent.putExtra(ChatBox.BUDDY_ID, messageSender);
        int index = getIndex(messageSender);
        Message m = new Message(messageSender,message);
        if( index == -1) {
            messageList.add(0,m);
        } else {
            messageList.remove(index);
            messageList.add(0,m);
        }
        int numberOfContacts = messageList.size();
        String contentTitle;
        if(numberOfContacts > 1) {
             contentTitle = "New messages from " + numberOfContacts + " contacts" ;
             int others = numberOfContacts - 1;
             if(others == 1) {
                 message = messageSender + " and " + messageList.get(1).messageSender ;
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

    public TalkToNotification cancelNotification(String messageSender) {
        int index = getIndex(messageSender);
         messageList.remove(index);
         if(messageList.size() == 0) {
             return null;
         } else {
             return getNotification(messageList.get(0).messageSender , messageList.get(0).message);
         }
    }

    public boolean containsSender(String messageSender) {
        int index = getIndex(messageSender);
        if(index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public class Message {
        public String messageSender;
        public String message;
        public Message(String messageSender,String message) {
            this.messageSender = messageSender ;
            this.message = message;
        }
    }

    private int getIndex(String messageSender) {
        int index = -1;
        for(int i = 0 ; i < messageList.size() ;i++) {
            if(messageList.get(i).messageSender.equalsIgnoreCase(messageSender) ) {
                index = i;
            }
        }
        return index;
    }

}