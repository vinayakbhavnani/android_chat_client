package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterResult;
import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.TagStore.VCardTag;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;
import directi.androidteam.training.chatclient.Roster.RosterManager;
import directi.androidteam.training.chatclient.Roster.SendPresence;
import directi.androidteam.training.chatclient.Roster.VCard;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.lib.xml.XMLHelper;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/7/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
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

    public void processPacketAux(Tag tag) {
        if (tag.getTagname().equals("iq")) {
            if(tag.getAttribute("type").equals("result")) {
                if (tag.contains("vCard")) {
                    final VCard vCard = new VCard();
                    vCard.populateFromTag(tag);
                    SendPresence.callerActivity.runOnUiThread(new Runnable() {
                        public void run() {
                        ((DisplayRosterActivity) SendPresence.callerActivity).displayVCard(vCard);
                        }
                    });
                } else {
                    RosterManager.getInstance().setRosterList(new RosterResult(tag));
                }
            }
        } else if(tag.getTagname().equals("presence")) {
            PresenceS presence = new PresenceS(tag);
            if(presence.getType() == null) {
                RosterManager.getInstance().updatePresence(presence);
                Tag vCardTag = new IQTag("v3", tag.getAttribute("from"), "get", new VCardTag("vcard-temp"));
                Log.d("asdfasdfasdf", (new XMLHelper()).buildPacket(vCardTag));
                PacketWriter.addToWriteQueue("<iq id='v3' to='" + tag.getAttribute("from") + "' type='get'><vCard xmlns='vcard-temp'/></iq>");
            }
        }
    }
}
