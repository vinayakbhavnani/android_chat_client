package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/7/12
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterPush extends TagWrapper {

    public RosterPush(Tag tag) {
        this.tag = new IQTag(tag);
        addSender();
    }

    public void  addSender() {
        tag.addAttribute("from", JID.getJid());
    }


    public String getJID(){
        Tag childTag = tag.getChildTags().get(0);
        if(childTag==null){
            return "error";
        }
        Tag itemTeag = childTag.getChildTags().get(0);
        String jid = itemTeag.getAttribute("jid");
        if(jid==null){
            return "error";
        }
        return jid;
    }


}
