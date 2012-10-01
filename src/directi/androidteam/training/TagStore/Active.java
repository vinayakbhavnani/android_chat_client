package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Active extends Tag {
    public Active() {
        super("active",null,null,null);
        addAttribute("xmlns","http://jabber.org/protocol/chatstates");
    }
}
