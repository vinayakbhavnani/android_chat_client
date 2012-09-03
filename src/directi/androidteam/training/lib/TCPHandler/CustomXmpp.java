package directi.androidteam.training.lib.TCPHandler;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 31/8/12
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomXmpp extends XMPPConnection {
    public CustomXmpp(ConnectionConfiguration connectionConfiguration) {
        super(connectionConfiguration);
    }
    public void writexmlMessage(String str){
        try {
            super.writer.write(str);
            super.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
