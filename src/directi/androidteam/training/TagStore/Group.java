package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Group extends Tag{
    public Group(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("group", attributes, childTags, content);
    }
}
