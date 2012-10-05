package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemTag extends Tag {
    public ItemTag(){
        super("item", null, null, null);
    }
    public ItemTag(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("item", attributes, childTags, content);
    }
}
