package directi.androidteam.training.chatclient.Authentication;

import android.util.Log;
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
    protected String authString;
    protected String serverURL;
    protected String serviceName;
    protected String accountJID;
    protected Socket socket;
    protected int port;
    public XMPPLogin(String accountjid,String passwd){

        this.accountJID=accountjid;
        generateAuthString(accountjid,passwd);
        //initiateLogin();

    }
    abstract void generateAuthString(String username, String passwd);
    public void initiateLogin(){
        Log.d("xmpplogin","initiate");
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


}
