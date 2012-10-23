package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Chat.MessageManager;
import directi.androidteam.training.chatclient.Notification.TalkToNotifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class MessageHandler implements Handler{

    private static final String LOGTAG = "MessageHandler";
    public MessageHandler(){
    }

    @Override
    public void processPacket(Tag tag){
        HashMap<String,Vector<MessageStanza>> chatLists = MessageManager.getInstance().getMessageStore();
        if(tag.getTagname().equals("message")) {
            MessageStanza ms = new MessageStanza(tag);
            String from = ms.getTag().getAttribute("from").split("/")[0];
            String chatState = ms.getChatState();
            if(chatLists.containsKey(from) && chatState.equals("composing")) {
                Log.d(LOGTAG,"Compose received from :" + from);
                ChatBox.composeToast(from +" is composing");
                return;
            }
            else if(ms.getBody()!=null) {
                if(ChatBox.getContext()==null){
                        Log.d(LOGTAG , "sending notification");
                    TalkToNotifier ttn = TalkToNotifier.getInstance(ChatApplication.getAppContext());
                    ttn.sendMessageNotification(ms.getFrom(),ms.getBody());
                }
                else {
                    ChatBox.notifyChat(ms,from);
                }

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
                        Log.d(LOGTAG,"feature not included");
                    }
                    else {
                        Tag tag1 = queryChildList.get(0);
                        Log.d(LOGTAG,"var - "+tag1.getAttribute("var"));
                    }
                }
            }
        }
   }
}
