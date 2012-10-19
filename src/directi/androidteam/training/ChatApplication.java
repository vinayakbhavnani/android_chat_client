package directi.androidteam.training;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import directi.androidteam.training.chatclient.Authentication.BackgroundService;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;
import directi.androidteam.training.chatclient.Authentication.NetworkManager;
import directi.androidteam.training.chatclient.MessageQueueProcessor;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/18/12
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        ChatApplication.context = getApplicationContext();
        ConnectGTalk.launchInNewThread(new MessageQueueProcessor());
        ConnectGTalk.launchInNewThread(new PacketWriter());
        Intent intent = new Intent(context, BackgroundService.class);
        startService(intent);
        NetworkManager.setConnected(context);
    }

    public static Context getAppContext() {
        return ChatApplication.context;
    }


}
