package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/7/12
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Show extends Tag{
    public Show(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("show", attributes, childTags, content);
    }

    public Show(String show) {
        this.tagname = "show";
        this.content = show;
    }

    public Show() {
        super("show",null,null,null);
    }
    public Show(Tag tag) {
        super(tag.tagname,tag.attributes,tag.childTags,tag.content);
    }
    public void setShowState(String state) {
        content=state;
    }
    public String getShowState(){
        return content;
    }
}