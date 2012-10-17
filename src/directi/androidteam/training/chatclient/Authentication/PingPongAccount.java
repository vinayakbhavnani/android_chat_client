package directi.androidteam.training.chatclient.Authentication;

import directi.androidteam.training.chatclient.R;

import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 5/10/12
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class PingPongAccount extends Account {
    public PingPongAccount(String username , String passwd){
        this.accountUid =username;
        this.serviceIcon = R.drawable.pingpong_icon;
        this.serverURL = "10.10.100.163";
        this.serverPort = 5222;
        this.service="pingpong";
        this.passwd = passwd;
        this.xmppLogin = new PingPongLogin(username,passwd);
        loginStatus = LoginStatus.OFFLINE;
    }

    @Override
    public Socket createSocket() throws IOException{
        Socket sock = new Socket(this.serverURL,this.serverPort);
        sock.setSoTimeout(0);
        sock.setKeepAlive(true);
        return sock;
    }
}
