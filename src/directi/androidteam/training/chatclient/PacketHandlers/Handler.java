package directi.androidteam.training.chatclient.PacketHandlers;

import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 7/9/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Handler {
    public void processPacket(Tag tag);
}
