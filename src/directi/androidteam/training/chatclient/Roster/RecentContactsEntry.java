package directi.androidteam.training.chatclient.Roster;


/**
 * Created with IntelliJ IDEA.
 * User: tarang
 * Date: 17/10/12
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecentContactsEntry {
    public String jid;
    public String type;

    public RecentContactsEntry(String JID, String type) {
        jid = JID;
        this.type = type;

    }



    public String getJid() {
        return jid;
    }


}