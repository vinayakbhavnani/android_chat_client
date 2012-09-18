package directi.androidteam.training.chatclient.Util;

import android.util.Log;
import directi.androidteam.training.chatclient.Authentication.ServiceThread;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/9/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketReader implements ServiceThread{
    private Socket socket;
    private BufferedReader reader;

    public PacketReader(Socket s, BufferedReader r) {
        this.socket = s;
        this.reader = r;
    }

    @Override
    public void execute() {
        Log.d("Service Thread","I am Packet Reader");
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            XMLHelper helper = new XMLHelper();
            helper.tearxmlPacket(reader);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
