package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/4/12
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionTag extends Tag {
    public SessionTag(String XMLNameSpace) {
        this.tagname = "session";
        this.attributes = new HashMap<String, String>();
        attributes.put("xmlns", XMLNameSpace);
        this.childTags = new ArrayList<Tag>();
        this.content = null;
    }
}
