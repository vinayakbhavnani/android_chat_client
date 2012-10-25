package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Forbidden extends Tag {
    public Forbidden(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("forbidden", attributes, childTags, content);
    }
}
