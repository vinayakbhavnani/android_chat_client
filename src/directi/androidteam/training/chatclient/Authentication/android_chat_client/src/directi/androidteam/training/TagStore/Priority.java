package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/7/12
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Priority extends Tag {
    public Priority(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("priority", attributes, childTags, content);
    }
    public Priority(Tag tag){
        super(tag.tagname,tag.attributes,tag.childTags,tag.content);
    }
    public Priority(int val){
        super("priority",null,null, String.valueOf(val));
    }
}
