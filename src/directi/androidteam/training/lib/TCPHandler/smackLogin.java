package directi.androidteam.training.lib.TCPHandler;

import android.util.Log;
import directi.androidteam.training.TagStore.MessageStanza;
import directi.androidteam.training.lib.xml.XMLHelper;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 31/8/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class smackLogin {
    String pass = "fuckedup";
    public void execute(){
        ConnectionConfiguration config = new ConnectionConfiguration("talk.google.com", 5222,"gmail.com");
        //config.setCompressionEnabled(true);

        //config.setSASLAuthenticationEnabled(true);

        Connection connection = new CustomXmpp(config);
// Connect to the server
        try {
            connection.connect();
            connection.login("vinayak.bhavnani@gmail.com",pass);

        } catch (XMPPException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Message message = new Message("vinayakhappening@gmail.com",Message.Type.chat);
        message.setBody("newtest");
        //connection.sendPacket(message);
        Log.d("packetxml",message.toXML());
        CustomXmpp xmpp = (CustomXmpp)connection;
        String str = "<message\n" +
                "    from='vinayak.bhavnani@gmail.com'\n" +
                "    id="+xmpp.getConnectionID()+"\n" +
                "    to='vinayakhappening@gmail.com'\n" +
                "    type='chat'\n" +
                "    xml:lang='en'>\n" +
                "  <body>Wherefore art thou, Romeo?</body>\n" +
                "</message>";
        String str1 = "<message to=\"vinayakhappening@gmail.com\" type=\"chat\"><body>testhi</body></message>";

        XMLHelper xml = new XMLHelper();
        String xmlstring = xml.buildPacket(new MessageStanza("vinayakhappening@gmail.com","heyteghghgh","vinayak.bhavnani@gmail.com").getTag());
        xmpp.writexmlMessage(xmlstring);
        Log.d("packetxml",xmlstring);

// Log into the server

    }
}
