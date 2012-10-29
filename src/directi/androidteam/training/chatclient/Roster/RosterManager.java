package directi.androidteam.training.chatclient.Roster;

import directi.androidteam.training.TagStore.Presence;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.GlobalTabActivity;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RosterManager {
    private Roster roster = new Roster(new LexicalCumPresenceComparator());
    private GlobalTabActivity globalTabActivity;
    private static RosterManager rosterManager = new RosterManager();
    private final Lock lock = new ReentrantLock();

    public static RosterManager getInstance() {
        return rosterManager;
    }

    public RosterItem getRosterItem(String accountUID, String bareJID) {
        return roster.searchRosterItem(accountUID, bareJID);
    }

    public void setGlobalTabActivity(GlobalTabActivity globalTabActivity) {
        this.globalTabActivity = globalTabActivity;
    }

    public ArrayList<RosterItem> getRoster() {
        return roster.getRoster();
    }

    public void updateRosterList(Tag rosterResult) {
        String accountUID = rosterResult.getRecipientAccount();
        for (Tag tag : rosterResult.getChildTags()) {
            if (tag.getAttribute("subscription").equals("both")) {
                RosterItem rosterItem = new RosterItem();
                rosterItem.setBareJID(tag.getAttribute("jid"));
                rosterItem.setAccount(accountUID);
                rosterItem.setPresence("unavailable");
                rosterItem.setStatus("");
                rosterItem.setVCard(new VCard(tag.getAttribute("jid")));
                this.lock.lock();
                this.roster.insertRosterItem(rosterItem);
                this.lock.unlock();
            }
        }
        updateRosterDisplay();
    }

    public void updatePresence(Presence presence) {
        RosterItem rosterItem = this.roster.searchRosterItem(presence.getRecipientAccount(), presence.getFrom().split("/")[0]);
        if (rosterItem == null) {return;}
        rosterItem.setStatus(presence.getStatus());
        if (presence.getShow() == null) {
            rosterItem.setPresence("chat");
        } else {
            rosterItem.setPresence(presence.getShow());
        }
        this.lock.lock();
        this.roster.insertRosterItem(rosterItem);
        this.lock.unlock();
        updateRosterDisplay();
    }

    public void updatePhoto(VCard vCard, String accountUID, String from) {
        RosterItem rosterItem = this.roster.searchRosterItem(accountUID, from);
        if (rosterItem == null) {return;}
        if (vCard.getAvatar() != null) {
            rosterItem.setVCard(vCard);
        }
        this.lock.lock();
        this.roster.insertRosterItem(rosterItem);
        this.lock.unlock();
        updateRosterDisplay();
    }

    private void updateRosterDisplay() {
        if (this.globalTabActivity != null) {
            this.globalTabActivity.runOnUiThread(new Runnable() {
                public void run() {
                    globalTabActivity.updateRosterList(roster.getRoster());
                }
            });
        }
    }

    public void removeAccount(String accountUID) {
        roster.deleteRosterItemsWithAccount(accountUID);
        updateRosterDisplay();
    }
}
