package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Query extends Tag{
    public Query(Tag tag) {
        super("query", tag.getAttributes(), tag.getChildTags(), tag.getContent());
    }
    public Query(){
        super("query",null,null,null);
    }
}
