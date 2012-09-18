package directi.androidteam.training.chatclient.PacketHandlers;

import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/18/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginHandler implements Handler {
    private static final LoginHandler loginHandler = new LoginHandler();

    public void processPacket(Tag tag) {

    }

    public static LoginHandler getInstance() {
        return loginHandler;
    }
}
