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

    public PacketReader(){

        this.socket=ConnectionHandler.getSocket();
    }

    @Override
    public void execute() {
        Log.d("Service Thread","I am Packet Reader");
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            XMLHelper helper = new XMLHelper();
            helper.tearxmlPacket(reader);
            //read();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void read(){
        while(true)
            try {
                String response = "";
                int c;
                while (!response.contains(">")) {
                    c = reader.read();
                    response = response + (char)c;

            }
                Log.d("packetinxml", response);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }
}
