package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterGet extends TagWrapper {

    public RosterGet(String from, String id) {
        tag = new IQTag();
        tag.addAttribute("from",from);
        tag.addAttribute("id",id);
        tag.addAttribute("type","get");
        Query query = new Query();
        tag.addChildTag(query);
        setSender(JID.getJid());
    }
    public RosterGet(){
        tag = new IQTag();
        tag.addAttribute("type","get");
        Query query = new Query();
        tag.addChildTag(query);
        setSender(JID.getJid());
    }
    public RosterGet setSender(String from){
        tag.addAttribute("from",from);
        return this;
    }
    public RosterGet setID(String id){
        tag.addAttribute("id",id);
        return this;
    }
    public RosterGet setReceiver(String to){
        tag.addAttribute("to",to);
        return this;
    }
    public RosterGet setQueryAttribute(String queryAttrName, String queryAttrVal){
        Tag query = tag.getChildTags().get(0);
        query.addAttribute(queryAttrName,queryAttrVal);
        return this;
    }
}
