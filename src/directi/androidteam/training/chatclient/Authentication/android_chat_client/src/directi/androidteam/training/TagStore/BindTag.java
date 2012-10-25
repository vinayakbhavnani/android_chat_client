package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/4/12
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class BindTag extends Tag {
    public BindTag(String XMLNameSpace) {
        this.tagname = "bind";
        this.attributes = new HashMap<String, String>();
        attributes.put("xmlns", XMLNameSpace);
        this.childTags = new ArrayList<Tag>();
        this.content = null;
    }
}
