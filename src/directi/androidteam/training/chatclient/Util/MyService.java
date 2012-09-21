package directi.androidteam.training.chatclient.Util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;

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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        (new ConnectGTalk()).execute(intent.getStringExtra("username"), intent.getStringExtra("password"));
        return START_STICKY;
    }

}
