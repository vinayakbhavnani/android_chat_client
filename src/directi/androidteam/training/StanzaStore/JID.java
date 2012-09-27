package directi.androidteam.training.StanzaStore;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/11/12
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class JID {
    private static String jid = null;
    private JID () {

    }
    public static String getJid() {
        return jid;
    }

    public static void setJID(String content) {
        jid = content;
    }
}
