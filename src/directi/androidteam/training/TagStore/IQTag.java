package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class IQTag extends Tag{
    public IQTag(){
        super("iq",null,null,null);
    }
    public IQTag(Tag tag){
        super(tag.tagname, tag.attributes, tag.childTags, tag.content);
    }
    public IQTag(String tagname, HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super(tagname, attributes, childTags, content);
    }
    public boolean isError() {
        if(attributes.get("type").equals("error"))
            return true;
        else return false;
    }
    public void addAttribute(String attributeName,String attributeVal){
        attributes.put(attributeName,attributeVal);
    }
    public ArrayList<String> getListOfRosters() {
        ArrayList<String> rosterList = new ArrayList<String>();
        if(!isError())
            return null;
        Tag query = this.childTags.get(0);
        for (Tag childTag : query.childTags) {
            rosterList.add(childTag.attributes.get("jid"));
        }
        return rosterList;
    }
    public String getType(){
        return attributes.get("type");
    }
}
