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
            NotificationWrapper.sendMessageNotification(context,messageSender.toString(),message.toString());
        } catch (IncompleteNotificationException ine) {
            Log.e("chat notifier","incomplete notification exception in chat notifier",ine);
            //ine.printStackTrace();
        }
        Log.d("chat notifier" , "i am successfully exiting , not my bug");
    }

}
