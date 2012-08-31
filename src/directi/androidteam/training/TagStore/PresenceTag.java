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
public class PresenceTag extends Tag {
    public PresenceTag(String tagname, HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("presence", attributes, childTags, content);
    }

    public PresenceTag() {
    }
}
