package directi.androidteam.training.chatclient.PacketHandlers;

import android.util.Log;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterPush;
import directi.androidteam.training.StanzaStore.RosterResult;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Roster.RosterManager;

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

    private boolean contains(Tag parent, String childTagName) {
        if (parent.getChildTags() != null) {
            for (int i = 0; i < parent.getChildTags().size(); i++) {
                if (parent.getChildTags().get(i).getTagname().equals(childTagName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void processPacket(Tag tag) {
        if(tag.getTagname().equals("message")){
        } else if (tag.getTagname().equals("stream:stream") || tag.getTagname().equals("success") || tag.getTagname().equals("failure")) {
        } else if (tag.getTagname().equals("iq") && contains(tag, "bind")) {
        } else {
            processPacketAux(tag);
        }
    }

    public void processPacketAux(Tag tag) {
        String tagName = tag.getTagname();
        Log.d("packet - name ",tag.getTagname());
        if (tagName.equals("iq")){
            String type = tag.getAttribute("type");
            if (type.equals("get")){
                Log.d("Packet Error","It is RosterManager Get Packet. This packet is always sent from Client side!");
            }
            else if(type.equals("set")){
                Log.d("Packet ACK","RosterManager Push Packet.");
                RosterPush rosterPush = new RosterPush(tag);
                String jid = rosterPush.getJID();
            }
            else if (type.equals("error")){
                Log.d("Packet Error","Error Reported From Server Side possibly due to RosterManager Result");
            }
            else if(type.equals("result")){
                RosterResult rosterResult = new RosterResult(tag);
                RosterManager rosterManager = RosterManager.getInstance();
                rosterManager.setRosterList(rosterResult);
            }
            else {
                Log.d("Packet Error","Unidentified IQ Packet, type = "+type);
            }
        }
        else if(tagName.equals("presence")){
            PresenceS presence = new PresenceS(tag);
            String type = presence.getType();
            if(type==null) {
                Log.d("Packet Error","type was null");
                RosterManager.getInstance().updatePresence(presence);
                return;
            }
            if(type.equals("error")){
                Log.d("Packet Error","Error From Server Side In Presence Packet");
            }
            else {
                Log.d("Packet ACK","Presence Packet");
            }
        }
        else {
            Log.d("Packet Error","Unidentified Packet, tagname = "+tagName);
        }
    }
}
