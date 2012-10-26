package directi.androidteam.training.chatclient.Authentication;

import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/10/12
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GtalkAccount extends Account {
    public GtalkAccount(String username , String passwd , boolean pwdbased){
        this.accountUid =username;
        this.serviceIcon = R.drawable.gtalk;
        this.serverURL = "talk.google.com";
        this.serverPort = 5223;

        this.passwd = passwd;
        loginStatus = LoginStatus.OFFLINE;
        if(pwdbased){
            this.xmppLogin = new GtalkLogin(username,passwd);
            this.service="gtalk";
        }
        else{
            this.xmppLogin = new GtalkOauthLogin(username,passwd);
            this.service="gtalkauth";
        }
        //AccountManager.getInstance().addAccount(this);
        //this.Login();
    }
}
