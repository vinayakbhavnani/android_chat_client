package directi.androidteam.training.TagStore;

import directi.androidteam.training.StanzaStore.JID;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/31/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Presence extends Tag {
    public Presence(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("presence", attributes, childTags, content);
        setRecipientAccount(JID.getJid());
    }
    public Presence() {
        super("presence", null,null,null);
        setRecipientAccount(JID.getJid());
    }
    public Presence(String from, String id, String to, String type) {
        super("presence",null,null,null);
        addAttribute("from", from);
        addAttribute("id",id);
        this.addAttribute("to", to);
        addAttribute("type",type);
        setRecipientAccount(JID.getJid());
    }

    public Presence(Tag tag) {
        super("presence",tag.attributes,tag.childTags,tag.content);
    }
    public void addShow(String showState){
        Show show = new Show(showState);
        addChildTag(show);
    }
    public void addStatus(String status){
        Status status1 = new Status();
        status1.setStatus(status);
        addChildTag(status1);
    }
    public void addPriority(int val){
        Priority priority = new Priority(val);
        addChildTag(priority);
    }
}

