package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Chat.ChatNotifier;

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
    private HashMap<String,NotifierArrayList> chatLists;

    private MessageHandler(){
        chatLists = new HashMap<String, NotifierArrayList>();
    }

     public String FragToJid(int i){
         if(i<0 || i >= chatLists.keySet().size())
             return null;
         return (String)chatLists.keySet().toArray()[i];
     }

     public int JidToFrag(String from){
         addChatContact(from);
         Log.d("message handler arraysize",new Integer(chatLists.keySet().size()).toString());
         Object[]  set = chatLists.keySet().toArray();
         int i =0;
         for (Object jid : set) {
             if(((String)jid).equals(from))
                 return i;
             i++;
         }
         return -1;
     }

     public  ArrayList<MessageStanza> getFragList(String from){
         addChatContact(from);
         return chatLists.get(from);
     }

    public static MessageHandler getInstance(){
        return messageHandler;
    }

    public HashMap<String,NotifierArrayList> getChatLists() {
        return chatLists;
    }

    private void addChatContact(String from){
        if(!chatLists.containsKey(from)){
            chatLists.put(from, new NotifierArrayList());
            if(ChatBox.getContext()!=null)
            ChatBox.recreateFragments();
        }
    }
    public void addChat(String from , MessageStanza ms){
        addChatContact(from);
        chatLists.get(from).add(ms);
    }

    @Override
    public void processPacket(Tag tag){
        if(tag.getTagname().equals("message")) {
            MessageStanza ms = new MessageStanza(tag);
            String from = ms.getTag().getAttribute("from").split("/")[0];
            if(ms.getBody()==null)
                return;
            addChat(from,ms);

            Log.d("chatsize",new Integer(chatLists.get(from).size()).toString()+from);

            if(ChatBox.getContext()==null){
                ChatNotifier cn = new ChatNotifier(ChatApplication.getAppContext());
                cn.notifyChat(ms);
            }
            else ChatBox.notifyChat(ms);
        }
        else if(tag.getTagname().equals("iq")) {
            ArrayList<Tag> childlist = tag.getChildTags();
            if(childlist==null)
                return;
            if(childlist.get(0).getTagname().equals("query")) {
                Query query = new Query(childlist.get(0));
                String xmlnsQuery = query.getAttribute("xmlns");
                if(xmlnsQuery==null)
                    return;
                if(xmlnsQuery.equals("http://jabber.org/protocol/disc#info"))
                {
                    ArrayList<Tag> queryChildList = query.getChildTags();
                    if(queryChildList==null) {
                        Log.d("MesaageHandler","feature not included");
                    }
                    else {
                        Tag tag1 = queryChildList.get(0);
                        Log.d("MesaageHandler","var - "+tag1.getAttribute("var"));
                    }
                }
            }
        }
   }
}
