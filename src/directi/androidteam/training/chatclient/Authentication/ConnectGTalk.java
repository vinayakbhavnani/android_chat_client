package directi.androidteam.training.chatclient.Authentication;

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
public class ConnectGTalk {
    public boolean authenticate (String username, String password) {
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket socket = sslsocketfactory.createSocket("talk.google.com", 5223);
            socket.setSoTimeout(0);
            socket.setKeepAlive(true);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String OpenStreamStanza = "<stream:stream" +
                    " to='gmail.com'" +
                    " xmlns='jabber:client'" +
                    " xmlns:stream='http://etherx.jabber.org/streams'" +
                    " version='1.0'>";
            out.print(OpenStreamStanza);
            out.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int c;
            String response = "";
            while (!(response.endsWith("</stream:features>"))) {
                c = reader.read();
                response = response + (char)c;
                System.out.print((char)c);
            }
            System.out.println();

            String auth = '\0' + username + '\0' + password;
            String AuthStanza = "<auth xmlns='urn:ietf:params:xml:ns:xmpp-sasl' mechanism='PLAIN'>" + Base64.encodeBytes(auth.getBytes()) + "</auth>";
            out.print(AuthStanza);
            out.flush();
            response = "";
            while (!(response.endsWith("sasl\"/>"))) {
                c = reader.read();
                response = response + (char)c;
                System.out.print((char)c);
            }
            System.out.println();

            out.print(OpenStreamStanza);
            out.flush();
            response = "";
            while (!(response.contains("</stream:features>"))) {
                c = reader.read();
                response = response + (char)c;
                System.out.print((char)c);
            }
            System.out.println();

            String RequestResourcePartStanza = "<iq id='tn281v37' type='set'>" +
                    " <bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'/>" +
                    " </iq>";
            out.print(RequestResourcePartStanza);
            out.flush();
            response = "";
            while (!(response.contains("</iq>"))) {
                c = reader.read();
                response = response + (char)c;
                System.out.print((char)c);
            }
            System.out.println();

            String StartSessionStanza = "<iq to='talk.google.com'" +
                    " type='set'" +
                    " id='sess_1'>" +
                    " <session xmlns='urn:ietf:params:xml:ns:xmpp-session'/>" +
                    "</iq>";
            out.print(StartSessionStanza);
            out.flush();
            response = "";
            while (!(response.contains("/>"))) {
                c = reader.read();
                response = response + (char)c;
                System.out.print((char)c);
            }
            System.out.println();

            String TestMessageStanza = "<message to='guptarajat1631990@gmail.com'>" +
                    " <body>Art thou not Romeo, and a Montague?</body>" +
                    "</message>";
            out.print(TestMessageStanza);
            out.flush();
            response = "";
            while (!(response.contains("/>"))) {
                c = reader.read();
                response = response + (char)c;
                System.out.print((char)c);
            }
            System.out.println();

            out.println("</stream>");
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
