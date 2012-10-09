package directi.androidteam.training.chatclient.Authentication;

import android.util.Log;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.chatclient.Util.ServiceThread;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 2/10/12
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public  abstract class Account {
    public  String service;
    protected Socket socket;
    protected Thread readerThread;
    protected String accountJid ;
    protected boolean loginStatus;
    protected XMPPLogin xmppLogin;
    protected  String serverURL;
    protected  int serverPort;

    public Thread getReaderThread() {
        return readerThread;
    }

    public void setReaderThread(Thread readerThread) {
        this.readerThread = readerThread;
    }

    public String getAccountJid() {
        return accountJid;
    }

    public void setAccountJid(String accountJid) {
        this.accountJid = accountJid;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public XMPPLogin getXmppLogin() {
        return xmppLogin;
    }

    public void setXmppLogin(XMPPLogin xmppLogin) {
        this.xmppLogin = xmppLogin;
    }



    public Account() {

    }

    protected Socket createSocket() throws IOException {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket sock = sslSocketFactory.createSocket(this.serverURL,this.serverPort);
        sock.setSoTimeout(0);
        sock.setKeepAlive(true);
        return sock;
    }

    private Thread launchInNewThread(final ServiceThread serviceThread) {
        Thread t = new Thread() {
            public void run() {
                serviceThread.execute();
            }
        };
        t.start();
        return t;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setupReaderWriter(Thread thread)throws IOException{

        readerThread = thread;

        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        PacketWriter.addStream(writer,accountJid);
        Log.d("readerwriter","setup");
    }

    public void Login(){
        xmppLogin.initiateLogin();
    }






}
