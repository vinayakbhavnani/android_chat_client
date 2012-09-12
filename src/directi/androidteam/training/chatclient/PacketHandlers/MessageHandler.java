package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.ChatBox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 6/9/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler implements Handler{

    private static final MessageHandler messageHandler = new MessageHandler();



    private HashMap<String,ArrayList<String>> chatLists;

    private MessageHandler(){
        chatLists = new HashMap<String, ArrayList<String>>();

        //chatpanes.put("vinayak.bhavnani",new ChatBox());
    }

     public  ArrayList<String> getFragList(String from){
         if(!chatLists.containsKey(from))
             chatLists.put(from,new ArrayList<String>());
         return chatLists.get(from);
     }

    public static MessageHandler getInstance(){
        return messageHandler;
    }


    @Override
    public void processPacket(Tag tag){
        Log.d("newmessage",tag.getChildTags().get(0).getContent());
        MessageStanza ms = new MessageStanza(tag);
        String message = ms.getBody();
        String from = ms.getTag().getAttribute("from").split("/")[0];
        if(!chatLists.containsKey(from))
            chatLists.put(from, new ArrayList<String>());
        chatLists.get(from).add(message);
        Log.d("chatsize",new Integer(chatLists.get(from).size()).toString()+from);
        ChatBox.openChat(from);
   }
}
