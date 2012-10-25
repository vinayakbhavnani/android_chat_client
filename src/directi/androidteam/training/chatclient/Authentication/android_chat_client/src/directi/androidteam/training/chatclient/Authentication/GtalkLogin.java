package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Authentication;

import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore.AuthTag;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Util.Base64;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/10/12
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GtalkLogin extends XMPPLogin {


    public GtalkLogin(String accountjid, String passwd) {
        super(accountjid, passwd);
        this.serviceName = "gmail.com";
    }

    @Override
    void generateAuthString(String username, String passwd) {
        this.authString = '\0'+username+'\0'+passwd;
    }

    @Override
    public void sendAuthPacket() {
        AuthTag tag = new AuthTag("urn:ietf:params:xml:ns:xmpp-sasl", "PLAIN", Base64.encodeBytes(authString.getBytes()));
        tag.setRecipientAccount(accountJID);
        PacketWriter.addToWriteQueue(tag);
    }
}
