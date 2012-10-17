package directi.androidteam.training.chatclient.Authentication;

import android.util.Log;
import directi.androidteam.training.TagStore.StreamClose;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Util.PacketReader;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.chatclient.Util.ServiceThread;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    protected String accountUid;
    protected LoginStatus loginStatus;
    public String presence;
    public Tag queryTag;

    public String getPresence() {
        return this.presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public Tag getQueryTag() {
        return this.queryTag;
    }

    public void setQueryTag(Tag queryTag) {
        this.queryTag = queryTag;
    }

    public LoginStatus getPersistedLoginStatus() {
        return persistedLoginStatus;
    }

    public void setPersistedLoginStatus(LoginStatus persistedLoginStatus) {
        this.persistedLoginStatus = persistedLoginStatus;
    }

    protected LoginStatus persistedLoginStatus;

    protected XMPPLogin xmppLogin;
    protected  String serverURL;
    protected  int serverPort;
    protected String passwd;
    protected String bareJID;

    public String getBareJID() {
        return bareJID;
    }

    public void setBareJID(String bareJID) {
        this.bareJID = bareJID;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getServiceIcon() {
        return serviceIcon;
    }

    protected int serviceIcon;

    public Thread getReaderThread() {
        return readerThread;
    }

    public void setReaderThread(Thread readerThread) {
        this.readerThread = readerThread;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public LoginStatus isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
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

    public static Account createAccount(String username, String password , String service , String prevStatus){
        Account ret = null;
        LoginStatus status = null;
        if(prevStatus.equals("ONLINE"))
            status = LoginStatus.ONLINE;
        else
            status = LoginStatus.OFFLINE;
        if(service.equals("gtalk")){
            ret = new GtalkAccount(username,password,true);
            ret.setPersistedLoginStatus(status);
        }
        else if(service.equals("pingpong")){
            ret = new PingPongAccount(username,password);
            ret.setPersistedLoginStatus(status);
        }
        return ret;
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
        PacketWriter.addStream(writer, accountUid);
        Log.d("readerwriter","setup");
    }

    public void Login(){
        try {
            this.socket = createSocket();
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            setupReaderWriter(launchInNewThread(new PacketReader(reader, accountUid)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmppLogin.initiateLogin();
        loginStatus=LoginStatus.CONNECTING;
    }

    public void Logout(){
        StreamClose close = new StreamClose();
        close.setRecipientAccount(accountUid);
        PacketWriter.addToWriteQueue(close);
        loginStatus=LoginStatus.OFFLINE;


    }

    public void freeResources(){
        readerThread.interrupt();
        readerThread=null;
        PacketWriter.removeStream(this.accountUid);
        socket=null;
    }
}


