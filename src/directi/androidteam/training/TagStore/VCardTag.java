package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/3/12
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class VCardTag extends Tag {
    public VCardTag(String XMLNameSpace) {
        this.tagname = "vCard";
        this.addAttribute("xmlns", XMLNameSpace);
    }

    public VCardTag(Tag tag) {
        this.tagname = "vCard";
        this.attributes = tag.getAttributes();
        this.childTags = tag.getChildTags();
        this.content = tag.getContent();
        this.setRecipientAccount(tag.getRecipientAccount());
    }
}
