package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Inactive extends Tag {
    public Inactive() {
        super("inactive",null,null,null);
        addAttribute("xmlns","http://jabber.org/protocol/chatstates");
    }
}
