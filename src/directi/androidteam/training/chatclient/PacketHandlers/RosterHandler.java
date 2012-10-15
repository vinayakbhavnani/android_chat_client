package directi.androidteam.training.chatclient.PacketHandlers;

import android.content.Context;
import android.util.Log;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.TagStore.IQTag;
import directi.androidteam.training.TagStore.Query;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.TagStore.VCardTag;
import directi.androidteam.training.chatclient.Roster.*;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RosterHandler implements Handler {
    private static RosterHandler rosterHandler = new RosterHandler();
    private HashMap<String, String> jidToShaOneMap = new HashMap<String, String>();

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
                String shaOne = this.jidToShaOneMap.get(tag.getAttribute("from").split("/")[0]);
                this.jidToShaOneMap.remove(tag.getAttribute("from").split("/")[0]);
                try {
                    FileOutputStream fileOutputStream = RequestRoster.callerActivity.openFileOutput(shaOne, Context.MODE_PRIVATE);
                    fileOutputStream.write(tag.getChildTag("vCard").getChildTag("PHOTO").getChildTag("BINVAL").getContent().getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    Log.d("FileNotFoundException", e.toString());
                } catch (IOException e) {
                    Log.d("IOException", e.toString());
                } catch (NullPointerException e) {
                    Log.d("NullPointerException", e.toString());
                }
            }
        } else if(tag.getTagname().equals("presence")) {
            PresenceS presence = new PresenceS(tag);
            if(presence.getType() != null && presence.getType().equals("unavailable")) {
                presence.addAvailability("unavailable");
            }
            RosterManager.getInstance().updatePresence(presence);
            String shaOne = tag.getChildTag("x").getChildTag("photo").getContent();
            try {
                FileInputStream fileInputStream = RequestRoster.callerActivity.openFileInput(shaOne);
                StringBuffer fileContent = new StringBuffer("");
                byte [] buffer = new byte[1024];
                int length;
                while ((length = fileInputStream.read(buffer)) != -1) {
                    fileContent.append(new String(buffer));
                }
                fileInputStream.close();
                String encodedAvatar = new String(fileContent);
                VCard vCard = new VCard();
                vCard.setAvatar(vCard.decodeAvatar(encodedAvatar));
                RosterManager.getInstance().updatePhoto(vCard, tag.getAttribute("from").split("/")[0]);
            } catch (FileNotFoundException e) {
                this.jidToShaOneMap.put(tag.getAttribute("from").split("/")[0], shaOne);
                Tag vCardTag = new IQTag("getVCard", tag.getAttribute("from"), "get", new VCardTag("vcard-temp"));
                PacketWriter.addToWriteQueue(vCardTag.setRecipientAccount(tag.getAttribute("to").split("/")[0]));
            } catch (IOException e) {
                Log.d("IOException", e.toString());
            } catch (NullPointerException e) {
                this.jidToShaOneMap.put(tag.getAttribute("from").split("/")[0], shaOne);
                Tag vCardTag = new IQTag("getVCard", tag.getAttribute("from"), "get", new VCardTag("vcard-temp"));
                PacketWriter.addToWriteQueue(vCardTag.setRecipientAccount(tag.getAttribute("to").split("/")[0]));
            }
        }
    }
}
