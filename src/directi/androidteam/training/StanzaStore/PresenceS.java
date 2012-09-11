package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.Presence;
import directi.androidteam.training.TagStore.Status;
import directi.androidteam.training.TagStore.Tag;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/7/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class PresenceS  extends TagWrapper {
    public PresenceS() {
        tag = new Presence();
    }
    public PresenceS(Tag tag){
        this.tag = new Presence(tag);
    }
    public PresenceS(String sender,String receiver,String id,String type){
        tag = new Presence();
        addSender(sender);
        addReceiver(receiver);
        addID(id);
        addType(type);
    }
    public void addSender(String sender){
        tag.addAttribute("from", sender);
    }
    public void addID(String id){
        tag.addAttribute("id", id);
    }
    public void addReceiver(String receiver){
        tag.addAttribute("to", receiver);
    }
    public void addType(String type){
        tag.addAttribute("type", type);
    }
    public String getType() {
        return tag.getAttribute("type");
    }
    public String getStatus(){
        ArrayList<Tag> childlist = tag.getChildTags();
        for (Tag tag : childlist) {
            if(tag.getTagname().equals("status")){
                return new Status(tag).getstatus();
            }
        }
        return null;
    }
}
