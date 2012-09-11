package directi.androidteam.training.chatclient;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import directi.androidteam.training.chatclient.Authentication.ServiceThread;
import directi.androidteam.training.chatclient.PacketStore.MessageQueue;

public class testtask implements ServiceThread {
    @Override
    public void execute() {
        Log.d("Service Thread : ", "I am testtask");
        MessageQueue.getInstance().processPacket();
    }
}