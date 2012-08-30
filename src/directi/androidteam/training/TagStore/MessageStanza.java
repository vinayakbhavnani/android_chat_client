package directi.androidteam.training.TagStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageStanza {
    private  Tag tag;
    public MessageStanza(String to , String body , String from){
        tag = new MessageTag(to,body,from);
    }
    public Tag getTag(){
        return tag;
    }

}
