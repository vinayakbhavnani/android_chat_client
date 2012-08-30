package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageTag extends Tag{
    public MessageTag(String to , String body , String from){
        tagname="message";
        attributes = new HashMap<String, String>();
        attributes.put("from",from);
        attributes.put("to",to);
        childTags = new ArrayList<Tag>();
        childTags.add(new BodyTag(body));
        content=null;

    }
}
