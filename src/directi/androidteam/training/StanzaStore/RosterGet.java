package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.IQTag;

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
        this.tag = new IQTag(from,id,"get",null);
    }
}
