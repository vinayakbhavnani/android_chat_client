package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Chat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.gen.directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.StanzaStore.MessageStanza;

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
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);
        int icon = R.drawable.android;
        long when = System.currentTimeMillis();
        CharSequence text = "new message";
        CharSequence contentTitle = stanza.getFrom();  // message title
        CharSequence contentText = stanza.getBody();      // message text

        Intent notificationIntent = new Intent(ChatApplication.getAppContext(), ChatBox.class);
        notificationIntent.putExtra("buddyid",stanza.getFrom());
        notificationIntent.putExtra("notification",true);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification(icon, text, when);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        mNotificationManager.notify(1,notification);
    }

    public void cancelNotification(){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);
        mNotificationManager.cancel(1);
    }

}
