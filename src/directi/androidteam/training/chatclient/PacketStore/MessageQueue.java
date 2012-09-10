package directi.androidteam.training.chatclient.PacketStore;

import android.util.Log;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.PacketHandlers.MessageHandler;
import directi.androidteam.training.chatclient.PacketHandlers.RosterHandler;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 6/9/12
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageQueue {
    MessageHandler mhandler;
    RosterHandler rhandler;
    private final ArrayList<Tag> tagQueue;
    private static final MessageQueue mqueue = new MessageQueue();

    private MessageQueue(){
        tagQueue = new ArrayList<Tag>();
        mhandler = MessageHandler.getInstance();
        rhandler = RosterHandler.getInstance();
    }

    public static MessageQueue getInstance(){
            return mqueue;
    }
    public void pushPacket(Tag tag){
        tagQueue.add(tag);
    }

    public void processPacket(){
        while(true){
           // Log.d("tester","tester");
            if(tagQueue.size()!=0){
                Tag temp = tagQueue.remove(0);
                Log.d("packet","packetprocessed");
                if(temp.getTagname().equals("message")){
                      mhandler.processPacket(temp);
                }
                else rhandler.processPacket(temp);
            }
        }
    }
}
