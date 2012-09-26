package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
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
    public static String uname = "";
    public static String pwd = "";
    public static Activity callerActivity = null;

    public ConnectGTalk(Activity parent) {
        callerActivity = parent;
    }

    public ConnectGTalk() {}

    @Override
    public Boolean doInBackground (String ...params) {
        uname = params[0];
        pwd = params[1];
        SSLSocketFactory sslSocketFactory;
        Socket socket;
        PrintWriter out;
        BufferedReader reader;
        try {
            sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = sslSocketFactory.createSocket("talk.google.com", 5223);
            socket.setSoTimeout(0);
            socket.setKeepAlive(true);

            out = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            launchInNewThread(new PacketReader(socket, reader));
            launchInNewThread(new PacketWriter(out));
            launchInNewThread(new MessageQueueProcessor());
            PacketWriter.addToWriteQueue("<stream:stream" +
                    " to='gmail.com'" +
                    " xmlns='jabber:client'" +
                    " xmlns:stream='http://etherx.jabber.org/streams'" +
                    " version='1.0'>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Bootup :","Executed all start functions of threads");
        return null;
    }

    private  void launchInNewThread(final ServiceThread serviceThread){

        Thread t = new Thread(){public void run(){serviceThread.execute();}};
        t.start();

    }
}