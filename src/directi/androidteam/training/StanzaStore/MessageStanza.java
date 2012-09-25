package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.MessageTag;
import directi.androidteam.training.TagStore.Tag;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageStanza extends TagWrapper{
    private long time;

    private void setCurrentTime(){
        time = System.currentTimeMillis();
    }
    public MessageStanza(String to, String body){
        tag = new MessageTag(to,body,null);
        setCurrentTime();
    }

    public long getTime() {
        return time;
    }

    public MessageStanza(String to, String body, String subject){
        tag = new MessageTag(to,body,subject);
        setCurrentTime();
    }
    public MessageStanza(Tag tag) {
        this.tag = tag;
        setCurrentTime();
    }
    public String getBody(){
        ArrayList<Tag> children = tag.getChildTags();
        for (Tag child : children) {
            if(child.getTagname().equals("body"))
                return child.getContent();
        }

        return null;
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
