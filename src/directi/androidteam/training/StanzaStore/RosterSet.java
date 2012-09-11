package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.*;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterSet extends TagWrapper {

    public RosterSet(String from, String id, String jid) {
        tag = new IQTag();
        tag.addAttribute("from",from);
        tag.addAttribute("id",id);
        tag.addAttribute("type","set");
        Query query1 = new Query();
        query1.addAttribute("xmlns","jabber:iq:roster");
        ItemTag itemTag = new ItemTag();
        itemTag.addAttribute("jid",jid);
        query1.addChildTag(itemTag);
        tag.addChildTag(query1);
    }
    public void addAttr(String attrName,String attrVal){
        ItemTag itemTag = (ItemTag) tag.getChildTags().get(0).getChildTags().get(0);
        itemTag.addAttribute(attrName,attrVal);
    }
    public void addGroup(String groupName){
        ItemTag itemTag = (ItemTag) tag.getChildTags().get(0).getChildTags().get(0);
        Group group = new Group(groupName);
        itemTag.addChildTag(group);
    }
}
