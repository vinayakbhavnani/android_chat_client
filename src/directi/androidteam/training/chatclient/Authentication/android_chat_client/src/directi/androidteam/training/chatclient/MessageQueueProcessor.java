package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient;

import android.util.Log;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.PacketStore.MessageQueue;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Util.ServiceThread;

public class MessageQueueProcessor implements ServiceThread {
    @Override
    public void execute() {
        Log.d("Service Thread : ", "I am MessageQueueProcessor");
        MessageQueue.getInstance().processPacket();
    }
}