package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Authentication;

import android.util.Log;

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
        this.accountJid=username;

        this.serverURL = "10.10.100.162";
        this.serverPort = 5222;
        this.xmppLogin = new PingPongLogin(username,passwd);
        try {
            //setupReaderWriter();
            this.socket = createSocket();
            Log.d("accountinfo", "created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountManager.getInstance().addAccount(this);
        //this.Login();
    }

    @Override
    public Socket createSocket() throws IOException{
        Socket sock = new Socket(this.serverURL,this.serverPort);
        sock.setSoTimeout(0);
        sock.setKeepAlive(true);
        return sock;
    }
}
