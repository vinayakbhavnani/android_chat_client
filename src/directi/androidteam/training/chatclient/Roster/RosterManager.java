package directi.androidteam.training.chatclient.Roster;

import directi.androidteam.training.TagStore.Presence;
import directi.androidteam.training.TagStore.Tag;

public class RosterManager {
    private Roster roster = new Roster(new LexicalCumPresenceComparator());
    private static RosterManager rosterManager = new RosterManager();

    public static RosterManager getInstance() {
        return rosterManager;
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
                rosterItem.setVCard(new VCard());
                this.roster.insertRosterItem(rosterItem);
            }
        }
        RequestRoster.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity) RequestRoster.callerActivity).updateRosterList(roster.getRoster());
            }
        });
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
        this.roster.insertRosterItem(rosterItem);
        RequestRoster.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity) RequestRoster.callerActivity).updateRosterList(roster.getRoster());
            }
        });
    }

    public void updatePhoto(VCard vCard, String accountUID, String from) {
        RosterItem rosterItem = this.roster.searchRosterItem(accountUID, from);
        if (rosterItem == null) {return;}
        if (vCard.getAvatar() != null) {
            rosterItem.setVCard(vCard);
        }
        RequestRoster.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity) RequestRoster.callerActivity).updateRosterList(roster.getRoster());
            }
        });
    }

    public RosterItem getRosterItem(String accountUID, String bareJID) {
        return roster.searchRosterItem(accountUID, bareJID);
    }

    public void removeAccount(String accountUID) {
        roster.deleteRosterItemsWithAccount(accountUID);
        RequestRoster.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity) RequestRoster.callerActivity).updateRosterList(roster.getRoster());
            }
        });
    }
}
