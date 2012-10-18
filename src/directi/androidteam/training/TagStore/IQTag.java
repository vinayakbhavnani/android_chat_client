package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

public class IQTag extends Tag {
    public IQTag() {
        super("iq",null,null,null);
    }

    public IQTag(Tag tag) {
        super(tag.tagname, tag.attributes, tag.childTags, tag.content);
        this.setRecipientAccount(tag.getRecipientAccount());
    }

    public IQTag(String id, String type, Tag child) {
        this.tagname = "iq";
        this.addAttribute("id", id);
        this.addAttribute("type", type);
        this.addChildTag(child);
    }

    public IQTag(String id, String to, String type, Tag child) {
        this.tagname = "iq";
        this.addAttribute("type", type);
        this.addAttribute("to", to);
        this.addAttribute("id", id);
        this.addChildTag(child);
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
}
