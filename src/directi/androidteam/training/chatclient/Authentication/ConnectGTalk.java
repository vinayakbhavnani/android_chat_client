package directi.androidteam.training.chatclient.Authentication;

import android.os.AsyncTask;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.Util.ConnectionHandler;
import directi.androidteam.training.chatclient.Util.PacketReader;
import directi.androidteam.training.lib.xml.XMLHelper;
import org.jivesoftware.smack.packet.Packet;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 8/30/12
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class ConnectGTalk extends AsyncTask<String, Void, String> {
    private String getOpenStreamStanza() {
        return "<stream:stream" +
                " to='gmail.com'" +
                " xmlns='jabber:client'" +
                " xmlns:stream='http://etherx.jabber.org/streams'" +
                " version='1.0'>";
    }

    private String getAuthStanza(String auth) {
        return "<auth xmlns='urn:ietf:params:xml:ns:xmpp-sasl' mechanism='PLAIN'>" + Base64.encodeBytes(auth.getBytes()) + "</auth>";
    }

    private String getResourcePartStanza() {
        return "<iq id='tn281v37' type='set'>" +
                " <bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'/>" +
                " </iq>";
    }

    private String getStartSessionStanza() {
        return "<iq to='talk.google.com'" +
                " type='set'" +
                " id='sess_1'>" +
                " <session xmlns='urn:ietf:params:xml:ns:xmpp-session'/>" +
                "</iq>";
    }

    private void readWhile(String endsWith, BufferedReader reader) throws IOException {
        String response = "";
        int c;
        while (!(response.contains(endsWith))) {
            c = reader.read();
            response = response + (char)c;
            System.out.print((char)c);
        }
        System.out.println();
    }

    @Override
    public String doInBackground (String ...params) {

        try {
            String username = params[0];
            String password = params[1];

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket socket = sslsocketfactory.createSocket("talk.google.com", 5223);
            socket.setSoTimeout(0);
            socket.setKeepAlive(true);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.print(getOpenStreamStanza());
            out.flush();
            readWhile("</stream:features>", reader);

            out.print(getAuthStanza('\0' + username + '\0' + password));
            out.flush();
            readWhile("sasl\"/>", reader);

            out.print(getOpenStreamStanza());
            out.flush();
            readWhile("</stream:features>", reader);

            out.print(getResourcePartStanza());
            out.flush();
            readWhile("</iq>", reader);

            out.print(getStartSessionStanza());
            out.flush();
            readWhile("/>", reader);


            ConnectionHandler.init(socket,out,reader);

            new PacketReader(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
