package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Error extends Tag {
    public Error() {
    }
    public Error(HashMap<String, String> attributes, ArrayList<Tag> childTags, String content) {
        super("error", attributes, childTags, content);
    }
    public Error(String type){
        super("error",null,null,null);
        addAttribute("type",type);
    }
}
