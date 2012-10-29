package directi.androidteam.training.StanzaStore;

import android.util.Log;
import directi.androidteam.training.TagStore.*;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterSet extends TagWrapper {

    public RosterSet() {
        tag = new IQTag();
        tag.addAttribute("type","set");
        tag.addAttribute("id", UUID.randomUUID().toString());
    }


    public String getID(){
        return tag.getAttribute("id");
    }
    public void addQuery(String jid){
        Query query1 = new Query();
        query1.addAttribute("xmlns","jabber:iq:roster");
        tag.addChildTag(query1);
        ItemTag itemTag = new ItemTag();
        itemTag.addAttribute("jid",jid);
        query1.addChildTag(itemTag);
        Log.d("roster set","addquery called");
    }
    public void addSubscription(String subscriptionType) {
        Query query = (Query) tag.getChildTags().remove(0);
        ItemTag itemTag = (ItemTag) query.getChildTags().remove(0);
        itemTag.addAttribute("subscription",subscriptionType);
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
