package directi.androidteam.training.chatclient.Chat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.Notification.IncompleteNotificationException;
import directi.androidteam.training.chatclient.Notification.NoNotificationToCancelException;
import directi.androidteam.training.chatclient.Notification.NotificationWrapper;
import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 21/9/12
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatNotifier {
    Context context;

    public ChatNotifier(Context context){
        this.context=context;
    }
    public void notifyChat(MessageStanza stanza){
        Log.d("chat notifier"," reached notify chat , am taking control");
        CharSequence messageSender = stanza.getFrom();
        CharSequence message = stanza.getBody();
        try {
            Log.d("chat notifier","sending message notification");
            NotificationWrapper.sendMessageNotification(context,messageSender.toString(),message.toString());
            Log.d("chat notifier" , "done sending message notification");
        } catch (IncompleteNotificationException ine) {
            Log.d("chat notifier","incomplete notification exception in chat notifier");
            //ine.printStackTrace();
        }
        Log.d("chat notifier" , "i am successfully exiting , not my bug");
    }

    public void cancelAllNotification(){
        Log.d("chat notifier" , "cancel notification entry point reached");
        try {
            NotificationWrapper.cancelAllMessageNotification();
        } catch (NoNotificationToCancelException nntce) {
            //nntce.printStackTrace();
        }
        Log.d("chat notifier" , "cancel notification is successfully exiting");
    }

    public void cancelNotification(int notificationID){
        Log.d("chat notifier" , "cancel notification entry point reached");
        try {
            NotificationWrapper.cancelMessageNotification(notificationID);
        } catch (NoNotificationToCancelException nntce) {
            //nntce.printStackTrace();
        }
        Log.d("chat notifier" , "cancel notification is successfully exiting");
    }

}
