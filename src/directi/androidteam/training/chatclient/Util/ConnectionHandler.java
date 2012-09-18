package directi.androidteam.training.chatclient.Util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 29/8/12
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionHandler {
    private static Socket socket;
    private static BufferedReader reader;
    private static PrintWriter writer;

    private void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static Socket getSocket() {
        return socket;
    }

    private void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    private void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public static PrintWriter getWriter(){
        return writer;
    }

    public static void init(Socket s, PrintWriter w, BufferedReader r) {
        socket = s;
        writer = w;
        reader = r;
     /*   XMLHelper xml = new XMLHelper();
    //    w.write(xml.buildPacket(new MessageStanza("vinayak.bhavnani@gmail.com", "newtest").getTag()));
        w.flush();
        new PacketReader(socket);  */
    }
}
