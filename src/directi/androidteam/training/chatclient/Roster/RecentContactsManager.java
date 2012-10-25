package directi.androidteam.training.chatclient.Roster;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: tarang
 * Date: 16/10/12
 * Time: 7:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecentContactsManager {
    private ArrayList<RecentContactsEntry> RecentContacts = new ArrayList<RecentContactsEntry>();
    private static int COUNTER;

    public static RecentContactsManager RECENT_CONTACTS_MANAGER = new RecentContactsManager();

    RecentContactsManager()
    {

    }

    public RecentContactsManager getInstance()
    {
        COUNTER = 0;
        return RECENT_CONTACTS_MANAGER;
    }

    public void insert(String JID, String type)
    {
        RecentContactsEntry E =  new RecentContactsEntry(JID,type);

        for(RecentContactsEntry RE : RecentContacts)
        {
            if(RE.equals(E))
            {
                RecentContacts.remove(RE);
                break;
            }
        }

        RecentContacts.add(E);

        if(RecentContacts.size() > 40)
        {
            RecentContacts.remove(RecentContacts.size()-1);
        }
    }

    public RecentContactsEntry getNext()
    {
        return RecentContacts.get(COUNTER);
    }

    public void reset()
    {
        COUNTER = 0;
        RecentContacts.clear();
    }


}
