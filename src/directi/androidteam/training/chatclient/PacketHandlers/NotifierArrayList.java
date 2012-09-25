package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.Chat.ChatFragment;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 19/9/12
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotifierArrayList extends ArrayList<MessageStanza>{
    ChatFragment listener_frag;
    public void registerFragment(ChatFragment frag){
        this.listener_frag = frag;
    }
    public void unregisterFragment(){
        listener_frag = null;
    }

    @Override
    public boolean add(MessageStanza e){
        boolean ret =  super.add(e);
        if(listener_frag!=null){
            listener_frag.addChatItem(e);
            Log.d("chatlistsize",e.getBody());
        }
        return ret;
    }
}
