package directi.androidteam.training.chatclient.PacketStore;

import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.PacketHandlers.LoginHandler;
import directi.androidteam.training.chatclient.PacketHandlers.MessageHandler;
import directi.androidteam.training.chatclient.PacketHandlers.RosterHandler;

import java.util.ArrayList;
import java.util.Vector;

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
    LoginHandler loginHandler;
    private final Vector<Tag> tagQueue;
    private static final MessageQueue mqueue = new MessageQueue();

    private MessageQueue(){
        tagQueue = new Vector<Tag>();
        mhandler = new MessageHandler();
        rhandler = RosterHandler.getInstance();
        loginHandler = LoginHandler.getInstance();
    }

    public static MessageQueue getInstance(){
            return mqueue;
    }
    public void pushPacket(Tag tag){
        tagQueue.add(tag);
    }

    public void processPacket(){
        while(true){
            if(tagQueue.size()!=0){
                Tag temp = tagQueue.remove(0);
                launchInNewThread(temp);
            }
        }
    }

    private void launchInNewThread(final Tag tag) {
        Thread thread = new Thread(){public void run() {
            mhandler.processPacket(tag);
            loginHandler.processPacket(tag);
            rhandler.processPacket(tag);
        }};
        thread.start();
    }
}
