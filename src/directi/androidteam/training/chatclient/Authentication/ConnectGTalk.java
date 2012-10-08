package directi.androidteam.training.chatclient.Authentication;

import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import directi.androidteam.training.TagStore.StreamTag;
import directi.androidteam.training.chatclient.MessageQueueProcessor;
import directi.androidteam.training.chatclient.Util.PacketReader;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.chatclient.Util.ServiceThread;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 8/30/12
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class ConnectGTalk extends AsyncTask<String, Void, Boolean> {
    public static String username = "";
    public static String password = "";
    public static Activity callerActivity = null;

    public ConnectGTalk(Activity parent) {
        callerActivity = parent;
    }

    public ConnectGTalk() {}

    private Socket createSSLSocket(String serverURL, int portNumber) throws IOException {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket socket = sslSocketFactory.createSocket(serverURL, portNumber);
        socket.setSoTimeout(0);
        socket.setKeepAlive(true);
        return socket;
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

    private void launchReaderOnSocket(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        launchInNewThread(new PacketReader(reader,null));
    }

    private void launchWriterOnSocket(Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        //launchInNewThread(new PacketWriter(writer));
    }

    private void sendOpenStreamStanza() {
        /*PacketWriter.addToWriteQueue("<stream:stream" +
                " to='gmail.com'" +
                " xmlns='jabber:client'" +
                " xmlns:stream='http://etherx.jabber.org/streams'" +
                " version='1.0'>");*/
        StreamTag tag = new StreamTag("stream:stream","gmail.com","jabber:client","http://etherx.jabber.org/streams","1.0");
        PacketWriter.addToWriteQueue(tag);
    }

    @Override
    public Boolean doInBackground (String ...params) {
        username = params[0];
        password = params[1];
        boolean account = false;
        boolean tokenbased = false;
        if(tokenbased){
        android.accounts.Account[] accounts = android.accounts.AccountManager.get(callerActivity).getAccountsByType("com.google");
        android.accounts.Account myaccount = accounts[0];

        AccountManagerFuture<Bundle> accFut = android.accounts.AccountManager.get(callerActivity).getAuthToken(myaccount,"mail",null,callerActivity,null,null);
        try{
        Bundle authTokenBundle = accFut.getResult();
        String authToken = authTokenBundle.get(android.accounts.AccountManager.KEY_AUTHTOKEN).toString();
        android.accounts.AccountManager.get(callerActivity).invalidateAuthToken("com.google",authToken);
            accFut = android.accounts.AccountManager.get(callerActivity).getAuthToken(myaccount,"mail",null,callerActivity,null,null);
            authTokenBundle = accFut.getResult();
            authToken = authTokenBundle.get(android.accounts.AccountManager.KEY_AUTHTOKEN).toString();
            username=myaccount.name;
            Log.d("myusername",username);
            password=authToken;
            Log.d("authtoken",password);
        }
        catch (Exception e){}
        }
        /*try {
            Socket socket = createSSLSocket("talk.google.com", 5223);

            launchReaderOnSocket(socket);
            launchWriterOnSocket(socket);


            sendOpenStreamStanza();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Bootup :","Executed all start functions of threads");
        return null;*/
        launchInNewThread(new MessageQueueProcessor());
        launchInNewThread(new PacketWriter());
        Account gtalk = null;
        if(account)
            gtalk = new PingPongAccount(username,password);
        else
            gtalk = new GtalkAccount(username,password,true);
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(gtalk.getSocket().getInputStream()));
            gtalk.setupReaderWriter(launchInNewThread(new PacketReader(reader, username)));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        gtalk.Login();
        return null;
    }
}