package directi.androidteam.training.StanzaStore;

import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.lib.xml.XMLHelper;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TagWrapper {
    protected Tag tag;

    public Tag getTag() {
        return tag;
    }

    public String getXml(){
        XMLHelper helper = new XMLHelper();
        return helper.buildPacket(tag);
    }

}
