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
    private static boolean chatrunning;

    public static boolean isChatrunning() {
        return chatrunning;
    }

    public static void setChatrunning(boolean chatrunning) {
        ChatApplication.chatrunning = chatrunning;
    }

    public void onCreate() {
        super.onCreate();
        ChatApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ChatApplication.context;
    }
}
