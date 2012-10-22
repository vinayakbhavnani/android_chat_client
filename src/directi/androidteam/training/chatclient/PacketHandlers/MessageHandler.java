package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Chat.MessageManager;
import directi.androidteam.training.chatclient.Notification.IncompleteNotificationException;
import directi.androidteam.training.chatclient.Notification.TalkToNotifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 6/9/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler implements Handler{

    public MessageHandler(){
    }

    @Override
    public void processPacket(Tag tag){
        Log.d("processPacket","entered process packet");
        HashMap<String,Vector<MessageStanza>> chatLists = MessageManager.getInstance().getMessageStore();
        Log.d("processPacket","got chatLists hashmap");
        if(tag.getTagname().equals("message")) {
            Log.d("processPacket","entered if block");
            MessageStanza ms = new MessageStanza(tag);
            Log.d("process packet","extracting from");
            String from = ms.getTag().getAttribute("from").split("/")[0];
            Log.d("process packet","finished extracting from ");
            String chatState = ms.getChatState();
            if(chatLists.containsKey(from) && chatState.equals("composing")) {
                Log.d("CHAT STATE","Compose received from :" + from);
                ChatBox.composeToast(from +" is composing");
                return;
            }
            else if(ms.getBody()!=null) {
                if(ChatBox.getContext()==null){
                    try {
                        Log.d("process packet" , "sending notification");
                    TalkToNotifier.sendMessageNotification(ChatApplication.getAppContext(),ms);
                    } catch ( IncompleteNotificationException ine ) {
                        Log.d("process packet" , " incomplete notification");
                    }
                }
                else {
                    ChatBox.notifyChat(ms,from);
                }

            }
            Log.d("processPacket" , "finished");
        }
        else if(tag.getTagname().equals("iq")) {
            Log.d("process packet","entered else if");
            ArrayList<Tag> childlist = tag.getChildTags();
            Log.d("process packet","got childlist");
            if(childlist==null)
                return;
            if(childlist.get(0).getTagname().equals("query")) {
                Log.d("process packet","entered query if");
                Query query = new Query(childlist.get(0));
                Log.d("process packet","got query");
                String xmlnsQuery = query.getAttribute("xmlns");
                Log.d("process packet", " got xmlns");
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
        Log.d("process packet" , "leaving successfully");
   }
}
