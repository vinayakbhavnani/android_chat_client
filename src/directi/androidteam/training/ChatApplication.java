package directi.androidteam.training;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getAppContext() {
        return ChatApplication.context;
    }
}
