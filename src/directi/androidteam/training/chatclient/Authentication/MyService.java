package directi.androidteam.training.chatclient.Authentication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Util.PacketReader;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.chatclient.testtask;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/10/12
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public  void onCreate(){
        launchInNewThread(new PacketWriter());
        launchInNewThread(new PacketReader());
        launchInNewThread(new testtask());
        Log.d("Bootup :","Executed all start functions of threads");

    }
    private void launchInNewThread(final ServiceThread serviceThread){
        Thread t = new Thread(){public void run(){serviceThread.execute();}};
        t.start();
    }



}
