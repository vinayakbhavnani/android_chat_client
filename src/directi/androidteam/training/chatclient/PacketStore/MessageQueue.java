package directi.androidteam.training.chatclient.PacketStore;

import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.PacketHandlers.Handler;
import directi.androidteam.training.chatclient.PacketHandlers.LoginHandler;
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
    LoginHandler loginHandler;
    private final ArrayList<Tag> tagQueue;
    private static final MessageQueue mqueue = new MessageQueue();

    private MessageQueue(){
        tagQueue = new ArrayList<Tag>();
        mhandler = MessageHandler.getInstance();
        rhandler = RosterHandler.getInstance();
        loginHandler = LoginHandler.getInstance();
    }

    private boolean contains(Tag parent, String childTagName) {
        if (parent.getChildTags() != null) {
            for (int i = 0; i < parent.getChildTags().size(); i++) {
                if (parent.getChildTags().get(i).getTagname().equals(childTagName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
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
                launchInNewThread(mhandler,temp);
                launchInNewThread(loginHandler,temp);
                launchInNewThread(rhandler,temp);
            }
        }
    }

    private void launchInNewThread(final Handler handler, final Tag tag) {
        Thread thread = new Thread(){public void run() {handler.processPacket(tag);}};
        thread.start();
    }
}
