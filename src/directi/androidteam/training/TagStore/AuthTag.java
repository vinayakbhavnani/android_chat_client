package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/4/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthTag extends Tag {
    public AuthTag(String XMLNameSpace, String mechanism, String content) {
        this.tagname = "auth";
        attributes = new HashMap<String, String>();
        attributes.put("xmlns", XMLNameSpace);
        attributes.put("mechanism", mechanism);
        childTags = new ArrayList<Tag>();
        this.content = content;
    }
}
