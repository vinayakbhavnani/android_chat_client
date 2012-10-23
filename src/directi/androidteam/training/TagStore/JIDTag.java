package directi.androidteam.training.TagStore;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/11/12
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class JIDTag extends Tag{
    public JIDTag(Tag tag){
        super(tag.tagname,tag.attributes,tag.getChildTags(),tag.content);
        Log.d("JID : ",tag.content);
    }


}
