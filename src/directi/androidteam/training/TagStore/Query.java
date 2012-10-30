package directi.androidteam.training.TagStore;

public class Query extends Tag {
    public Query(Tag tag) {
        super("query", tag.getAttributes(), tag.getChildTags(), tag.getContent());
        this.setRecipientAccount(tag.getRecipientAccount());
    }

    public Query() {
        super("query", null, null, null);
    }

    public Query(String XMLNameSpace, String version) {
        this.tagname = "query";
        this.addAttribute("xmlns", XMLNameSpace);
        this.addAttribute("version", version);
    }
}
