package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.MessageTag;
import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageStanza extends TagWrapper{
    public MessageStanza(String to, String body){
        tag = new MessageTag(to,body,null);
    }
    public MessageStanza(String to, String body, String subject){
        tag = new MessageTag(to,body,subject);
    }
    public MessageStanza(Tag tag) {
        this.tag = tag;
    }
    public String getBody(){
        return tag.getChildTags().get(0).getContent();
    }
    public Tag getTag(){
        return tag;
    }
    public String getFrom(){
        return tag.getAttribute("from").split("/")[0];
    }

    public String getTo(){
        return tag.getAttribute("to").split("/")[0];
    }

}
