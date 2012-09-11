package directi.androidteam.training.lib.TCPHandler;

import android.util.Log;
import directi.androidteam.training.StanzaStore.TagWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/31/12
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocketReader {
    private static final SocketReader socketReader = new SocketReader();
    String initiate_conn="<?xml version=\"1.0\"?>\n\r<stream:stream to=\"google.com\"\n\rversion=\"1.0\"\n\rxmlns=\"jabber:client\"\n\rxmlns:stream=\"http://etherx.jabber.org/streams\">\n";
    String start_tls="<starttls xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>";

    private SocketReader() {
    }
    public static SocketReader getInstance(){
        return socketReader;
    }
    public TagWrapper getMessage() {
        Socket socket = ChatSocket.getSocket();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream to_server = socket.getOutputStream();
            to_server.write(initiate_conn.getBytes());
            String line = bufferedReader.readLine();
            Log.d("Server : ", line);
            to_server.write(start_tls.getBytes());
            line = bufferedReader.readLine();
            Log.d("Server : " , line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
