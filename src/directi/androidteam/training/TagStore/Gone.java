package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/3/12
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Gone extends Tag{
    public Gone() {
        super("gone", null,null,null);
        addAttribute("xmlns","http://jabber.org/protocol/chatstates");
    }
}
