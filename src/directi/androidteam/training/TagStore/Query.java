package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Query extends Tag{
    public Query(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("query", attributes, childTags, content);
    }
    public Query(){
        super("query",null,null,null);
    }
}
