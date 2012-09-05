package directi.androidteam.training.chatclient.Util;

import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/9/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketWriter {
    private Socket socket;
    private PrintWriter writer;

    public PacketWriter(Socket sock)  {
        this.socket = sock;
        try {
            this.writer =  new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void write(Tag tag){
        XMLHelper helper = new XMLHelper();
        String tagxml = helper.buildPacket(tag);
        writer.write(tagxml);
        writer.flush();
    }

    public void write(String str){
        writer.write(str);
        writer.flush();
    }


}
