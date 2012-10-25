package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubjectTag extends Tag {
    public SubjectTag(String subject){
        tagname = "subject";
        content = subject;
        attributes=null;
        childTags=null;
    }
}
