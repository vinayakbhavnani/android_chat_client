package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotAcceptable extends Tag{
    public NotAcceptable(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("not-acceptable", attributes, childTags, content);
    }
    public NotAcceptable(){
        super("non-acceptable",null,null,null);
    }
}
