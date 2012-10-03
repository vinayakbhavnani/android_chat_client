package directi.androidteam.training.chatclient.Authentication;

import directi.androidteam.training.chatclient.Util.ServiceThread;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 2/10/12
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public  class Account {
    public static String service;
    private Socket socket;
    private Thread readerThread;
    private String accountJid;
    private String base64Pass;
    private boolean loginStatus;
    private XMPPLogin xmppLogin;

    public Account(String username, String password){
        this.accountJid = username;

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






}
