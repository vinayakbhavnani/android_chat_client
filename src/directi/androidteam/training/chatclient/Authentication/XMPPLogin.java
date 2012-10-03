package directi.androidteam.training.chatclient.Authentication;

import directi.androidteam.training.TagStore.*;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.lib.xml.XMLHelper;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 2/10/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class XMPPLogin {
    private String authString;
    private String serverURL;
    private String serviceName;
    private String accountJID;
    private Socket socket;
    private int port;
    public XMPPLogin(String serverURL , int dstport , String service , String accountjid){
        this.serverURL = serverURL;
        this.serviceName=service;
        this.accountJID=accountjid;
        this.port = dstport;

    }
    abstract void generateAuthString(String username, String passwd);
    public void initiateLogin(){
        sendInitStream();
    }
    private void sendInitStream(){
        StreamTag streamTag = new StreamTag("stream:stream",serviceName,"jabber:client","http://etherx.jabber.org/streams","1.0");
        streamTag.setRecipientAccount(accountJID);
        PacketWriter.addToWriteQueue(streamTag);
    }
    public abstract void sendAuthPacket();
    public void restartStream(){
        sendInitStream();
    }
    public void sendIQwithBind(){
       IQTag iqTag =  new IQTag("tn281v37", "set", new BindTag("urn:ietf:params:xml:ns:xmpp-bind"));
       iqTag.setRecipientAccount(accountJID);
       PacketWriter.addToWriteQueue(iqTag);
    }

    public void sendStartSession(){
        IQTag iqTag = new IQTag("sess_1", "talk.google.com", "set", new SessionTag("urn:ietf:params:xml:ns:xmpp-session"));
        iqTag.setRecipientAccount(accountJID);
        PacketWriter.addToWriteQueue(iqTag);
    }

    private Socket createSocket() throws IOException {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket sock = sslSocketFactory.createSocket(this.serverURL,this.port);
        sock.setSoTimeout(0);
        sock.setKeepAlive(true);
        return sock;
    }
}
