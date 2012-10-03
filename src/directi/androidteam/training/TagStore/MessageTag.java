package directi.androidteam.training.TagStore;

import directi.androidteam.training.StanzaStore.JID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageTag extends Tag{
    public MessageTag(String to , String body ,String subject){
        tagname="message";
        attributes = new HashMap<String, String>();
        attributes.put("type","chat");
        attributes.put("to",to);
        attributes.put("from", JID.getJid());
        attributes.put("id", UUID.randomUUID().toString());
        if(subject!=null)
            attributes.put("subject",subject);
        childTags = new ArrayList<Tag>();
        childTags.add(new BodyTag(body));
        content=null;
    }
    public MessageTag(String to) {
        super("message",null,null,null);
        addAttribute("from", JID.getJid());
        addAttribute("to",to);
        childTags = new ArrayList<Tag>();
    }

    public MessageTag(Tag tag) {
        super(tag.getTagname(),tag.getAttributes(),tag.getChildTags(),tag.getContent());
    }

    public String getBody() {
        ArrayList<Tag> childList = getChildTags();
        if(childList==null)
            return null;
        else {
            Tag bTag = childList.get(0);
            BodyTag bodyTag = new BodyTag(bTag);
            return bodyTag.getContent();
        }
    }
    public void setBody(String body) {
        ArrayList<Tag> childList = getChildTags();
        if(childList==null) {
            childList = new ArrayList<Tag>();
            childList.add(new BodyTag(body));
            return;
        }
        else {
            Tag bTag = childList.get(0);
            BodyTag bodyTag = new BodyTag(bTag);
            bodyTag.setBody(body);
            childList.set(0,bodyTag);
        }
    }

    public void addActiveTag() {
        childTags.add(new Active());
    }
    public void addComposingTag() {
        childTags.add(new Composing());
    }
    public void addPaused() {
        childTags.add(new Paused());
    }
    public void addInactive() {
        childTags.add(new Inactive());
    }
    public void addGoneTag() {
        childTags.add(new Gone());
    }
}
