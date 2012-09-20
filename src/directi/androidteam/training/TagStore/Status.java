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
public class Status extends Tag{
    public Status() {
        super("status",null,null,null);
    }

    public Status(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("status", attributes, childTags, content);
    }
    public Status(Tag tag){
        super(tag.tagname,tag.attributes,tag.childTags,tag.content);
    }
    public void setStatus(String status){
        content = status;
    }
    public String getstatus(){
        return content;
    }
}