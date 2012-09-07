package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 6/9/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler implements Handler{
    private static final MessageHandler messageHandler = new MessageHandler();

    private MessageHandler(){

    }

    public static MessageHandler getInstance(){
        return messageHandler;
    }
    public void processPacket(Tag tag){
        Log.d("newmessage",tag.getChildTags().get(0).getContent());
        MessageStanza ms = new MessageStanza(tag);

    }
}
