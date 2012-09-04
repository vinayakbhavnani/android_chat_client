package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/31/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Presence extends Tag {
    public Presence(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("presence", attributes, childTags, content);
    }
    public Presence() {
        super("presence",null,null,null);
    }
    public Presence(String from, String id, String to, String type) {
        super("presence",null,null,null);
        addAttribute("from",from);
        addAttribute("id",id);
        addAttribute("to",to);
        addAttribute("type",type);
    }
}
