package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Authentication;

import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore.AuthTag;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Util.Base64;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 5/10/12
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GtalkOauthLogin extends XMPPLogin {
    public GtalkOauthLogin(String accountjid, String passwd) {
        super(accountjid, passwd);
        this.serviceName = "gmail.com";
    }

    @Override
    void generateAuthString(String username, String passwd) {
       this.authString = '\0'+username+'\0'+passwd;

    }

    @Override
    public void sendAuthPacket() {
        AuthTag tag = new AuthTag("urn:ietf:params:xml:ns:xmpp-sasl","X-GOOGLE-TOKEN", Base64.encodeBytes(authString.getBytes()));
        //tag.addAttribute("auth:service","oauth2");
        //tag.addAttribute("xmlns:auth","http://www.google.com/talk/protocol/auth");
        tag.setRecipientAccount(this.accountJID);
        PacketWriter.addToWriteQueue(tag);
    }
}
