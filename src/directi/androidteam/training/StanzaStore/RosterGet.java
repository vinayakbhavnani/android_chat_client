package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.Query;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterGet {
    private IQTag tag;

    public RosterGet(String from, String id) {
        tag = new IQTag();
        tag.addAttribute("from",from);
        tag.addAttribute("id",id);
        tag.addAttribute("type","get");
        Query query = new Query();
        query.addAttribute("xmlns","jabber:iq:roster");
        tag.addChildTag(query);
    }
}
