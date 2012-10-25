package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

import android.util.Log;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.StanzaStore.JID;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/11/12
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class JIDTag extends Tag {
    public JIDTag(Tag tag){
        super(tag.tagname,tag.attributes,tag.getChildTags(),tag.content);
        JID.setJID(tag.content);
        Log.d("JID : ",tag.content);
    }


}
