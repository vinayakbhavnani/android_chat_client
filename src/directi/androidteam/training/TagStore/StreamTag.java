package directi.androidteam.training.TagStore;

import android.util.Log;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/4/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class StreamTag extends Tag {
    public StreamTag(String tagname, String to, String XMLNameSpace, String XMLNameSpaceStream, String version) {
        this.tagname=tagname;
        attributes = new HashMap<String, String>();
        attributes.put("to", to);
        attributes.put("xmlns",XMLNameSpace);
        attributes.put("xmlns:stream", XMLNameSpaceStream);
        attributes.put("version", version);
        childTags = new ArrayList<Tag>();
        content=null;
    }
    @Override
    public String toXml(){
        XMLHelper helper = new XMLHelper();
        String str = helper.buildPacket(this);
        String open = str.substring(0,str.length()-2)+">";
        Log.d("streamxml",open);
        return str;
    }
}
