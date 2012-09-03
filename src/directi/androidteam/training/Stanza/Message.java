package directi.androidteam.training.Stanza;

import directi.androidteam.training.TagStore.MessageTag;
import directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private Tag tag;
    public Message(String to, String body, String from){
        tag = new MessageTag(to,body,from);
    }
    public Message(Tag tag) {
        this.tag = tag;
    }
    public Tag getTag(){
        return tag;
    }

}
