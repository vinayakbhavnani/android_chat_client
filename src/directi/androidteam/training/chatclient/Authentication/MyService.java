package directi.androidteam.training.chatclient.Authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
        (new ConnectGTalk()).execute("");
    }

}
