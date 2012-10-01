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

    public MessageStanza(String to, String body){
        tag = new MessageTag(to,body,null);
        setCurrentTime();
    }

    public MessageStanza(String to, String body, String subject){
        tag = new MessageTag(to,body,subject);
        setCurrentTime();
    }

    public MessageStanza(Tag tag) {
        this.tag = tag;
        setCurrentTime();
    }

    public MessageStanza(String to) {
        tag = new MessageTag(to);
    }

    public void formActiveMsg() {
        ((MessageTag)tag).addActiveTag();
    }

    public void formInActiveMsg() {
        ((MessageTag)tag).addInactive();
    }
    public void formComposingMsg() {
        ((MessageTag)tag).addComposingTag();
    }
    public void formGoneMsg() {
        ((MessageTag)tag).addGoneTag();
    }
    public void formPausedMsg() {
        ((MessageTag)tag).addPaused();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status = true;

    private void setCurrentTime(){
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
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
    public String getID(){
        return tag.getAttribute("id");
    }

    public String getChatState() {
        ArrayList<Tag> childList = tag.getChildTags();
        for (Tag tag1 : childList) {
            if(tag1.getTagname().equals("cha:active"))
                return "active";
            else if(tag1.getTagname().equals("cha:composing"))
                return "composing";
            else if(tag1.getTagname().equals("cha:gone"))
                return "gone";
            else if(tag1.getTagname().equals("cha:inactive"))
                return "inactive";
        }

        return "no chatstate";
    }
}
