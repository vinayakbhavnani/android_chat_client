package directi.androidteam.training.TagStore;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemTag extends Tag {
    public ItemTag(String query) {
        super("item", null, null, null);
        HashMap<String,String> temp = new HashMap<String, String>();
        temp.put("jid", query);
        setAttributes(temp);
    }
}
