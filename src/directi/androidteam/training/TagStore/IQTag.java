package directi.androidteam.training.TagStore;

import directi.androidteam.training.StanzaStore.JID;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class IQTag extends Tag{
    public IQTag(){
        super("iq",null,null,null);
        setRecipientAccount(JID.getBareJid());
    }
    public IQTag(Tag tag){
        super(tag.tagname, tag.attributes, tag.childTags, tag.content);
    }
    public IQTag(String tagname, HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super(tagname, attributes, childTags, content);
        setRecipientAccount(JID.getBareJid());
    }
    public IQTag(String id, String type, Tag child) {
        this.tagname = "iq";
        this.addAttribute("id", id);
        this.addAttribute("type", type);
        this.addChildTag(child);
        setRecipientAccount(JID.getBareJid());
    }
    public IQTag(String id, String to, String type, Tag child) {
        this.tagname = "iq";
        this.addAttribute("type", type);
        this.addAttribute("to", to);
        this.addAttribute("id", id);
        this.addChildTag(child);
        setRecipientAccount(JID.getBareJid());
    }

    public void addAttribute(String attributeName,String attributeVal){
        if(attributes==null)
            attributes = new HashMap<String, String>();
        attributes.put(attributeName,attributeVal);
    }
    public ArrayList<Tag> getRosterItems() {
        if(childTags==null)
            return null;
        Tag resultTag = childTags.get(0);
        if(resultTag.childTags==null)
            return null;
        ArrayList<Tag> rosterList = new ArrayList<Tag>();

        for (Tag childTag : resultTag.childTags) {
            rosterList.add(childTag);
        }
        return rosterList;
    }
    public String getType(){
        if(attributes==null)
            return null;
        return attributes.get("type");
    }
}
