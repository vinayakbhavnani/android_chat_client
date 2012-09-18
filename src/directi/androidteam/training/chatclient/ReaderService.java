package directi.androidteam.training.chatclient;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import directi.androidteam.training.chatclient.Util.PacketReader;

import java.io.BufferedReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 10/9/12
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReaderService extends IntentService {
    private Socket socket;
    private BufferedReader reader;

    public ReaderService(Socket s, BufferedReader r){
        super("readerservice");
        this.socket = s;
        this.reader = r;
    }

    @Override
    public void onCreate() {
        Log.d("executeservicereader", "background");
        //MessageQueue.getInstance().processPacket();
        new PacketReader(socket, reader);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
