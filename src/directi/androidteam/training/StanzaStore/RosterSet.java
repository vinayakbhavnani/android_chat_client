package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.ITagWrapper;
import directi.androidteam.training.TagStore.ItemTag;
import directi.androidteam.training.TagStore.Query;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterSet implements ITagWrapper {
    private IQTag tag;

    public RosterSet(String from, String id, String query) {
        tag = new IQTag();
        tag.addAttribute("from",from);
        tag.addAttribute("id",id);
        tag.addAttribute("type","set");
        Query query1 = new Query();
        query1.addAttribute("xmlns","jabber:iq:roster");
        ItemTag itemTag = new ItemTag();
        itemTag.addAttribute("jid",query);
        query1.addChildTag(itemTag);
        tag.addChildTag(query1);
    }
}
