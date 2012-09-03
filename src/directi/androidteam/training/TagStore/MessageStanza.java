package directi.androidteam.training.TagStore;

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
    public MessageStanza(Tag tag) {
        this.tag = tag;
    }
    public Tag getTag(){
        return tag;
    }

}
