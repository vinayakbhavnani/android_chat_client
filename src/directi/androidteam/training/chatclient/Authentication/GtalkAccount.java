package directi.androidteam.training.chatclient.Authentication;

import android.util.Log;
import directi.androidteam.training.chatclient.R;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/10/12
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GtalkAccount extends Account {
    public GtalkAccount(String username , String passwd , boolean pwdbased){
        this.accountJid=username;
        this.serviceIcon = R.drawable.gtalk;
        this.serverURL = "talk.google.com";
        this.serverPort = 5223;
        this.service="gtalk";
        loginStatus = LoginStatus.OFFLINE;
        if(pwdbased)
            this.xmppLogin = new GtalkLogin(username,passwd);
        else
            this.xmppLogin = new GtalkOauthLogin(username,passwd);

        //AccountManager.getInstance().addAccount(this);
        //this.Login();
    }
}
