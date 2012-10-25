package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Inactive extends Tag {
    public Inactive() {
        super("cha:inactive",null,null,null);
        addAttribute("xmlns:cha","http://jabber.org/protocol/chatstates");
    }
}
