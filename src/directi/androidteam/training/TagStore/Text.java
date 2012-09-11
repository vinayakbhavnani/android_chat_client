package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/11/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Text extends Tag{
    public Text() {
        super("text",null,null,null);
    }
    public Text(Tag tag) {
        super("text",tag.attributes,tag.getChildTags(),tag.content);
    }

}
