package directi.androidteam.training.chatclient.Chat;

import directi.androidteam.training.StanzaStore.MessageStanza;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/3/12
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FragmentManager {

    public String FragToJid(int i){
        return MessageManager.getInstance().getRequiredJiD(i);
    }

    public int JidToFrag(String from){
        MessageManager.getInstance().insertEntry(from);
        HashMap<String,ArrayList<MessageStanza>> chatLists = MessageManager.getInstance().getMessageStore();

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

}
