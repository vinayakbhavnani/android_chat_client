package directi.androidteam.training.TagStore;

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
}
