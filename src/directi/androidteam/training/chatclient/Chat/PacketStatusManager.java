package directi.androidteam.training.chatclient.Chat;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 26/9/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketStatusManager {
    private static final PacketStatusManager psm = new PacketStatusManager();
    private HashMap<String,ComboMessage> packetqueue;
    private PacketStatusManager(){
        packetqueue = new HashMap<String, ComboMessage>();
    }

    public static PacketStatusManager getInstance(){
        return psm;
    }

    public void pushMsPacket(MessageStanza ms){
        packetqueue.put(ms.getID(),new ComboMessage(ms,null));
        Log.d("psmanager","addms"+ms.getID());
    }
    public void pushCliPacket(ChatListItem cli){
        Log.d("psmanager","addcli"+cli.getId());
        if(packetqueue.containsKey(cli.getId())){
            packetqueue.get(cli.getId()).setCli(cli);
            Log.d("psmanager","addcli"+cli.getId());
        }
    }

    public void setSuccess(String id){
        packetqueue.remove(id);
        Log.d("psmanager","success "+id);
    }

    public void setFailure(String id){
        if(packetqueue==null || !packetqueue.containsKey(id))
            return;
        ComboMessage cm = packetqueue.remove(id);
        cm.setFailure();
        Log.d("psmanager","failure "+id);
    }

}
