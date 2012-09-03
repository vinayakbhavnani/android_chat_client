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
public class Message {
    private Tag tag;
    public Message(String to, String body){
        tag = new MessageTag(to,body,null);
    }
    public Message(String to, String body, String subject){
        tag = new MessageTag(to,body,subject);
    }
    public Message(Tag tag) {
        this.tag = tag;
    }
    public Tag getTag(){
        return tag;
    }

}
