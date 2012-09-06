package directi.androidteam.training.chatclient.Util;

import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 29/8/12
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionHandler {
    public static Socket socket;
    public static BufferedReader reader;
    public static PrintWriter writer;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public Writer getWriter(){
        return writer;
    }
    public static void init(Socket sock,PrintWriter out , BufferedReader in){
        socket = sock;
        reader = in;
        writer = out;

        XMLHelper xml = new XMLHelper();
        out.write(xml.buildPacket(new MessageStanza("vinayak.bhavnani@gmail.com", "newtest").getTag()));
        out.flush();
        new PacketReader(socket);

    }
}
