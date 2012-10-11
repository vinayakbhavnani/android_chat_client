package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.content.Context;
import directi.androidteam.training.StanzaStore.MessageStanza;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/3/12
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyFragmentManager {

    private Vector<String> JIDOrderOfFragments;
    private HashMap<String,ChatFragment> JIDToFrag;
    private static MyFragmentManager fragmentManager  = new MyFragmentManager();
    private MyFragmentManager() {
        JIDOrderOfFragments = new Vector<String>();
        JIDToFrag = new HashMap<String, ChatFragment>();
    }

    public static MyFragmentManager getInstance() {
        return fragmentManager;

    }

    public synchronized void addFragEntry(final String jid) {
        for (String s : JIDOrderOfFragments) {
            if(s.equals(jid))
                return;
        }
        Context context = ChatBox.getContext();
        if(context!=null) {
            Activity application = (Activity) context;
                application.runOnUiThread(new Runnable() {
                    public void run() {
                        ChatFragment chatFragment = ChatFragment.getInstance(jid);
                        JIDOrderOfFragments.add(jid);
                        JIDToFrag.put(jid,chatFragment);
                        ChatBox.recreateFragments();
                    }
                });
        }
    }

    public synchronized void removeFragEntry(String jid) {
        for (String s : JIDOrderOfFragments) {
            if(s.equals(jid)) {
                JIDOrderOfFragments.remove(s);
            }
        }
        JIDToFrag.remove(jid);
        if(ChatBox.getContext()!=null)
            ChatBox.recreateFragments();
    }

    public synchronized void removeFragEntry(int position) {
        if(position>=getSizeofActiveChats())
            return;
        int i=0;
        String jid = "";
        for (String s : JIDOrderOfFragments) {
            if(i==position) {
                jid = JIDOrderOfFragments.get(i);
                JIDOrderOfFragments.remove(s);
                break;
            }
            i++;
        }
        JIDToFrag.remove(jid);
        if(ChatBox.getContext()!=null)
            ChatBox.recreateFragments();
    }

    public synchronized void flush() {
        JIDToFrag = new HashMap<String, ChatFragment>();
        JIDOrderOfFragments = new Vector<String>();
    }

    public ChatFragment getFragByJID(String jid) {
        return JIDToFrag.get(jid);
    }

    public String FragIdToJid(int queryJID){
        if(queryJID<0 || queryJID>= getSizeofActiveChats())
            return null;
        return JIDOrderOfFragments.get(queryJID);
    }

    public int JidToFragId(String from){
        if(from==null)
            return -1;
        int i =0;
        for (String s : JIDOrderOfFragments) {
            if(s.equals(from))
                return i;
            i++;
        }
        return -1;
    }

    public  Vector<MessageStanza> getFragList(String from){
        addFragEntry(from);
        MessageManager.getInstance().insertEntry(from);
        return MessageManager.getInstance().getMsgList(from);
    }

    public int getSizeofActiveChats() {
        return JIDOrderOfFragments.size();
    }

    public void updateFragment(String jid, MessageStanza ms, boolean b) {
        if(jid==null)
            return;
        ChatFragment chatFragment = JIDToFrag.get(jid);
        if(chatFragment!=null)
            chatFragment.addChatItem(ms,b);
    }
}
