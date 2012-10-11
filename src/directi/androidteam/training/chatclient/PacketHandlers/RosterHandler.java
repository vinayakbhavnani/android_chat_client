package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.TagStore.VCardTag;
import directi.androidteam.training.chatclient.Roster.*;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.util.UUID;

public class RosterHandler implements Handler {
    private static RosterHandler rosterHandler = new RosterHandler();

    public static RosterHandler getInstance() {
        return rosterHandler;
    }

    @Override
    public void processPacket(Tag tag) {
        if(tag.getTagname().equals("message")){
        } else if (tag.getTagname().equals("stream:stream") || tag.getTagname().equals("success") || tag.getTagname().equals("failure")) {
        } else if (tag.getTagname().equals("iq") && tag.contains("bind")) {
        } else {
            processPacketAux(tag);
        }
    }

    public void processPacketAux(final Tag tag) {
        if (tag.getTagname().equals("iq")) {
            if (tag.contains("query")) {
                final Tag queryTag = tag.getChildTag("query");
                if (queryTag.getAttribute("xmlns").equals("jabber:iq:roster")) {
                    RosterManager.getInstance().setRosterList(tag);
                    PacketWriter.addToWriteQueue((new IQTag(UUID.randomUUID().toString(), tag.getAttribute("to").split("/")[0], "get", new Query("google:shared-status", "2")).setRecipientAccount(tag.getAttribute("to").split("/")[0])));
                } else if (queryTag.getAttribute("xmlns").equals("google:shared-status")) {
                    (new SendPresence(RequestRoster.callerActivity)).execute(tag.getAttribute("to"), queryTag.getChildTag("status").getContent(), queryTag.getChildTag("show").getContent());
                    ((DisplayRosterActivity) RequestRoster.callerActivity).setCurrentAccount(tag.getAttribute("to"), queryTag.getChildTag("status").getContent(), queryTag.getChildTag("show").getContent(), tag);
                    SendPresence.callerActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            ((DisplayRosterActivity) SendPresence.callerActivity).displayJID(tag.getAttribute("to").split("/")[0]);
                            ((DisplayRosterActivity) SendPresence.callerActivity).displayStatus(queryTag.getChildTag("status").getContent());
                            ((DisplayRosterActivity) SendPresence.callerActivity).displayPresence(queryTag.getChildTag("show").getContent());
                        }
                    });
                }
            } else if (tag.contains("vCard")) {
                VCard vCard = new VCard();
                vCard.populateFromTag(tag);
                RosterManager.getInstance().updatePhoto(vCard, tag.getAttribute("from").split("/")[0]);
            }
        } else if(tag.getTagname().equals("presence")) {
            Log.d("hhhhhhhhhhhhhhhhhh", (new XMLHelper()).buildPacket(tag));
            PresenceS presence = new PresenceS(tag);
            if(presence.getType() == null) {              //this is to filter out unavailable users/contacts
                RosterManager.getInstance().updatePresence(presence);
                Tag vCardTag = new IQTag("getVCard", tag.getAttribute("from"), "get", new VCardTag("vcard-temp"));
                PacketWriter.addToWriteQueue(vCardTag.setRecipientAccount(tag.getAttribute("to").split("/")[0]));
            }
        }
    }
}
