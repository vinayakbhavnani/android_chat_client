package directi.androidteam.training.chatclient.Authentication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 14/10/12
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkManager {
    public static Boolean connected;
    public static void setConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        boolean ret=false;
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    ret = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    ret = true;
        }
        connected = ret;
    }
}
