package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Chat.ChatNotifier;
import directi.androidteam.training.chatclient.Chat.MessageManager;

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

    private MessageHandler(){
    }

     public String FragToJid(int i){
         HashMap<String,ArrayList<MessageStanza>> chatLists = MessageManager.getInstance().getMessageStore();
         if(i<0 || i >= chatLists.keySet().size())
             return null;
         return (String)chatLists.keySet().toArray()[i];
     }

     public int JidToFrag(String from){
         MessageManager.getInstance().insertEntry(from);
         HashMap<String,ArrayList<MessageStanza>> chatLists = MessageManager.getInstance().getMessageStore();
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
         MessageManager.getInstance().insertEntry(from);
         return MessageManager.getInstance().getMsgList(from);
     }

    public static MessageHandler getInstance(){
        return messageHandler;
    }

    public HashMap<String,ArrayList<MessageStanza>> getChatLists() {
        return MessageManager.getInstance().getMessageStore();
    }

    @Override
    public void processPacket(Tag tag){
        HashMap<String,ArrayList<MessageStanza>> chatLists = MessageManager.getInstance().getMessageStore();
        if(tag.getTagname().equals("message")) {
            MessageStanza ms = new MessageStanza(tag);
            String from = ms.getTag().getAttribute("from").split("/")[0];
            String chatState = ms.getChatState();
            if(chatLists.containsKey(from) && chatState.equals("composing")) {
                Log.d("CHAT STATE","Compose received from :" + from);
                ChatBox.composeToast(from +" is composing");
                return;
            }
            else if(ms.getBody()!=null) {
                ms.setCreater(from);
                MessageManager.getInstance().insertMessage(from,ms);
                if(ChatBox.getContext()==null){
                    ChatNotifier cn = new ChatNotifier(ChatApplication.getAppContext());
                    cn.notifyChat(ms);
                }
                else ChatBox.notifyChat(ms);
            }
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
                        Log.d("MessageHandler","feature not included");
                    }
                    else {
                        Tag tag1 = queryChildList.get(0);
                        Log.d("MessageHandler","var - "+tag1.getAttribute("var"));
                    }
                }
            }
        }
   }
}
