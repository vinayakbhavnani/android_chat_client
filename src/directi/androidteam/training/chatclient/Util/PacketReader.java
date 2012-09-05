package directi.androidteam.training.chatclient.Util;

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
public class PacketReader {
    private Socket socket;
    private BufferedReader reader;
    public PacketReader(Socket sock){
        this.socket=sock;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            XMLHelper helper = new XMLHelper();
            helper.tearxmlPacket(reader);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String blockRead(){
        return null;
    }
}
