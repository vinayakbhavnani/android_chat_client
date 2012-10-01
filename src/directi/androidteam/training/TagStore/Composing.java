package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Composing extends Tag {
    public Composing() {
        super("cha:composing",null,null,null);
        addAttribute("xmlns:cha","http://jabber.org/protocol/chatstates");
    }
}
