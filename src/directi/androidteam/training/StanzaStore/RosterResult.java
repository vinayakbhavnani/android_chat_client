package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.ITagWrapper;
import directi.androidteam.training.TagStore.Tag;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterResult implements ITagWrapper {
    private IQTag tag;
    public RosterResult(Tag tag) {
        this.tag = new IQTag(tag);
    }
    public boolean isError() {
        return tag.isError();
    }
    public ArrayList<String> getListOfRosters(){
        return tag.getListOfRosters();
    }
}
