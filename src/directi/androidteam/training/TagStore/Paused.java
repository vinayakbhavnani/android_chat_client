package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Paused extends Tag{
    public Paused() {
        super("cha:paused",null,null,null);
        addAttribute("xmlns:cha","http://jabber.org/protocol/chatstates");
    }
}
