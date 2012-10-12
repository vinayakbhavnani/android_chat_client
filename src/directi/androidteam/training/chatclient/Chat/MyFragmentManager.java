package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;

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
    private static MyFragmentManager fragmentManager  = new MyFragmentManager();
    private MyFragmentManager() {
        JIDOrderOfFragments = new Vector<String>();
    }

    public static MyFragmentManager getInstance() {
        return fragmentManager;

    }

    public synchronized void addFragEntry(final String jid) {
        if(jid==null)
            return;
        Log.d("xcxc","addFragEntry : jid : "+jid);
        if(JIDOrderOfFragments.size()>0)
        for (String s : JIDOrderOfFragments) {
            if(s.equals(jid))
                return;
        }
        Log.d("xcxc","addFragEntry : jid : "+jid + "actual insert");
        Context context = ChatBox.getContext();
        if(context!=null) {
            Activity application = (Activity) context;
                application.runOnUiThread(new Runnable() {
                    public void run() {
                        ChatFragment chatFragment = ChatFragment.getInstance(jid);
                        JIDOrderOfFragments.add(jid);
                        ChatBox.notifyFragmentAdaptorInSameThread();
                    }
                });
        }
    }

    public synchronized void removeFragEntry(String jid) {
        if(jid==null)
            return;
        Log.d("xcxc","addFragEntry : jid : "+jid);
        for (String s : JIDOrderOfFragments) {
            if(s.equals(jid)) {
                JIDOrderOfFragments.remove(s);
            }
        }
        if(ChatBox.getContext()!=null)
            ChatBox.notifyFragmentAdaptorInNewUIThread();
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
//        if(ChatBox.getContext()!=null)
            ChatBox.notifyFragmentAdaptorInSameThread();  //changed
    }

    public synchronized void flush() {
        JIDOrderOfFragments = new Vector<String>();
    }

    public ChatFragment getFragByJID(String jid) {
        return ChatFragment.getInstance(jid);
    }

    public String getJidByFragId(int queryJID){
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
        ChatFragment chatFragment = (ChatFragment) FragmentSwipeAdaptor.getFragment(jid);
        if(chatFragment!=null)
            chatFragment.addChatItem(ms,b);
    }
}
