package directi.androidteam.training.chatclient.Authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 16/10/12
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackgroundService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int temp){
        return START_STICKY;
    }

    @Override
    public void onCreate(){
        Log.d("BackgroundService","Service");
    }
}
