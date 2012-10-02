package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 30/8/12
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class BodyTag extends Tag {
    public BodyTag(String body){
        tagname="body";
        content=body;
        attributes=null;
        childTags=null;
    }
    public void setBody(String body) {
        content = body;
    }
    public BodyTag(Tag tag) {
        super(tag.getTagname(),tag.getAttributes(),tag.getChildTags(),tag.getContent());
    }
}
