package directi.androidteam.training.chatclient.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import directi.androidteam.training.chatclient.Authentication.ConnectGTalk;
import directi.androidteam.training.chatclient.Authentication.LoginActivity;
import directi.androidteam.training.chatclient.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/11/12
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
//        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
//        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_MOBILE );
//        if ( activeNetInfo != null ) {
//            Toast.makeText(context, "Active Network Type : " + activeNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
//        }
//        if( mobNetInfo != null ) {
//            Toast.makeText( context, "Mobile Network Type : " + mobNetInfo.getTypeName(), Toast.LENGTH_SHORT ).show();
//        }
        boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
        boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

        NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
        NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

        if(currentNetworkInfo.isConnected()){
            Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
            if (!(LoginActivity.uname.equals("") || LoginActivity.pwd.equals(""))) {
                (new ConnectGTalk(context)).execute(Constants.username, Constants.password);
            } else {

            }
        }else{
            Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show();
        }
    }
}
