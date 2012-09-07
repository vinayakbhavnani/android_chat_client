package directi.androidteam.training.chatclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import directi.androidteam.training.chatclient.PacketStore.MessageQueue;

public class testtask extends Service {
    public testtask() {
    }

    protected Object doInBackground(Object... objects) {
        Log.d("execute", "background");
        //new customConnection();
        // new smackLogin().execute();
        //ConnectGTalk conn  = new ConnectGTalk();
        //Socket sock = conn.authenticate("brian.gingers", "androidchat");
        //PacketReader pr = new PacketReader(sock);
        //XMLHelper xml = new XMLHelper();
        //ConnectionHandler.writer.write(xml.buildPacket(new MessageStanza("vinayak.bhavnani@gmail.com","newtest").getTag()));
        //new PacketReader(ConnectionHandler.socket);
        MessageQueue.getInstance().processPacket();
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("execute123", "background");
        MessageQueue.getInstance().processPacket();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}