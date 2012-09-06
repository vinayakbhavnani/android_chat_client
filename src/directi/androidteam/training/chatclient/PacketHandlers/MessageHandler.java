package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.ChatBox;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 6/9/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler implements Handler{

    HashMap<String,ChatBox> chatpanes;
    private static final MessageHandler messageHandler = new MessageHandler();
    private MessageHandler(){

    }

    public static MessageHandler getInstance(){
        return messageHandler;
    }

    @Override
    public void processPacket(Tag tag){
        Log.d("newmessage",tag.getChildTags().get(0).getContent());
        MessageStanza ms = new MessageStanza(tag);

    }
}
