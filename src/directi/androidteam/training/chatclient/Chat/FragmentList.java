package directi.androidteam.training.chatclient.Chat;

import directi.androidteam.training.StanzaStore.MessageStanza;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/10/12
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class FragmentList extends Vector<MessageStanza> {

    ChatFragment listener_frag;

    public FragmentList() {
        super();
    }

    public void registerFragment(ChatFragment frag){
        this.listener_frag = frag;
    }

    public void unregisterFragment(){
        listener_frag = null;
    }

    public boolean add(MessageStanza e,Boolean b){
        boolean ret = super.add(e);
        if(listener_frag!=null){
            listener_frag.addChatItem(e, b);
        }
        return ret;
    }
}