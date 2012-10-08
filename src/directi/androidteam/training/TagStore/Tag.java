package directi.androidteam.training.TagStore;

import android.util.Log;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tag {
    protected String tagname;
    protected HashMap<String,String> attributes;
    protected ArrayList<Tag> childTags;
    protected String content;
    protected String recipientAccount;

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(String recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public String toXml(){
        XMLHelper helper = new XMLHelper();
        return helper.buildPacket(this);
    }

    public Tag(String tagname, HashMap<String, String> attributes, ArrayList<Tag> childTags,String content) {
        this.tagname = tagname;
        this.attributes = attributes;
        this.childTags = childTags;
        this.content=content;
    }

    public Tag(){
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }
    public void addAttributes(HashMap<String,String> attributes){
        if(this.attributes==null)
            this.attributes = new HashMap<String, String>();
        this.attributes.putAll(attributes);
    }

    public void setChildTags(ArrayList<Tag> childTags) {
        this.childTags = childTags;
    }

    public String getTagname() {

        return tagname;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }


    public ArrayList<Tag> getChildTags() {
        return childTags;
    }

    public String getContent() {
        return content;
    }
    public void addAttribute(String attributeName,String attributeVal){
        if(attributes==null)
            attributes = new HashMap<String, String>();
        attributes.put(attributeName,attributeVal);
    }
    public void deleteAttribute(String attributeName) {
        if (attributes==null)
            attributes = new HashMap<String, String>();
        attributes.remove(attributeName);
    }

    public void addChildTag(Tag tag){
        if(childTags==null)
            childTags = new ArrayList<Tag>();
        childTags.add(tag);
    }

    public void setAttribute(String key, String value) {
        if (attributes==null)
            return;
        attributes.put(key, value);
    }

    public String getAttribute(String key){
        if(attributes==null)
            return null;
        return attributes.get(key);
    }
    public Tag getChildTag(String childTagName) {
        for (int i = 0; i < this.getChildTags().size(); i++) {
            if (this.getChildTags().get(i).getTagname().equals(childTagName)) {
                return this.getChildTags().get(i);
            }
        }
        return new Tag();
    }
    public Tag getChildTag(String childTagName, String showValue) {
        Log.d("xxxxxxxxx", showValue);
        for (int i = 0; i < this.getChildTags().size(); i++) {
            if (this.getChildTags().get(i).getTagname().equals(childTagName) && this.getChildTags().get(i).getAttribute("show").equals(showValue)) {
                Log.d("xxxxxxxxxx", this.getChildTags().get(i).getTagname());
                return this.getChildTags().get(i);
            }
        }
        Log.d("xxxxxxxxxx", "error");
        return new Tag();
    }
    public boolean contains(String childTagName) {
        if (this.getChildTags() != null) {
            for (int i = 0; i < this.getChildTags().size(); i++) {
                if (this.getChildTags().get(i).getTagname().equals(childTagName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void setID(String id) {
        addAttribute("id",id);
    }

    public void setFrom(String from) {
        addAttribute("from",from);
    }

    public void setContent(String content) {
        this.content = content;
    }
}