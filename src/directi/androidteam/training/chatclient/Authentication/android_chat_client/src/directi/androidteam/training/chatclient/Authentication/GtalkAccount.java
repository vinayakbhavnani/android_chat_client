package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Authentication;

import android.util.Log;

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

        this.serverURL = "talk.google.com";
        this.serverPort = 5223;
        if(pwdbased)
            this.xmppLogin = new GtalkLogin(username,passwd);
        else
            this.xmppLogin = new GtalkOauthLogin(username,passwd);
        try {
            //setupReaderWriter();
            this.socket = createSocket();
            Log.d("accountinfo","created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountManager.getInstance().addAccount(this);
        //this.Login();
    }
}
