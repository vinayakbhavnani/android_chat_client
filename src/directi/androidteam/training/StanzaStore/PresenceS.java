package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.ITagWrapper;
import directi.androidteam.training.TagStore.Presence;
import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/7/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class PresenceS  implements ITagWrapper {
    private Presence presence;
    public PresenceS() {
        presence = new Presence();
    }
    public PresenceS(Tag tag){
        presence = new Presence(tag);
    }
    public PresenceS(String sender,String receiver,String id,String type){
        presence = new Presence();
        addSender(sender);
        addReceiver(receiver);
        addID(id);
        addType(type);
    }
    public void addSender(String sender){
        presence.addAttribute("from",sender);
    }
    public void addID(String id){
        presence.addAttribute("id",id);
    }
    public void addReceiver(String receiver){
        presence.addAttribute("to",receiver);
    }
    public void addType(String type){
        presence.addAttribute("type",type);
    }
    public String getType() {
        return presence.getAttribute("type");
    }
}
