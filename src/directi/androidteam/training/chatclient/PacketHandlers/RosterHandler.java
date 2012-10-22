package directi.androidteam.training.chatclient.PacketHandlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.TagStore.*;
import directi.androidteam.training.chatclient.Authentication.Account;
import directi.androidteam.training.chatclient.Authentication.AccountManager;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.RosterManager;
import directi.androidteam.training.chatclient.Roster.VCard;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RosterHandler implements Handler {
    private static RosterHandler rosterHandler = new RosterHandler();
    private Map<String, String> jidToShaOneMap = Collections.synchronizedMap(new HashMap<String, String>());
    public static Bitmap defaultUserImage = BitmapFactory.decodeResource(ChatApplication.getAppContext().getResources(), R.drawable.default_user);

    public static RosterHandler getInstance() {
        return rosterHandler;
    }

    @Override
    public void processPacket(Tag tag) {
        if(tag.getTagname().equals("message")){
        } else if (tag.getTagname().equals("stream:stream") || tag.getTagname().equals("success") || tag.getTagname().equals("failure")) {
        } else if (tag.getTagname().equals("iq") && tag.contains("bind")) {
        } else if (tag.getChildTags() == null) {
        } else {
            if (tag.getTagname().equals("iq")) {
                processIqPacket(new IQTag(tag));
            } else if (tag.getTagname().equals("presence")) {
                processPresencePacket(new Presence(tag));
            }
        }
    }

    private void processIqPacket(final IQTag tag) {
        Account account = AccountManager.getInstance().getAccount(tag.getRecipientAccount());
        account.setFullJID(tag.getAttribute("to"));
        account.setQueryTag(tag);
        if (tag.contains("query")) {
            processQueryPacket(new Query(tag.getChildTag("query").setRecipientAccount(tag.getRecipientAccount())), account);
        } else if (tag.contains("vCard")) {
            processVCardPacket(new VCardTag(tag.getChildTag("vCard").setRecipientAccount(tag.getRecipientAccount())), tag.getAttribute("from").split("/")[0]);
        }
    }

    private void processPresencePacket(Presence presence) {
        if(presence.getType() != null && presence.getType().equals("unavailable")) {
            presence.setShow("unavailable");
        }
        RosterManager.getInstance().updatePresence(presence);
        setPhoto(presence.getRecipientAccount(), presence.getAvatarShaOne(), presence.getFrom());
    }

    private void setPhoto(String accountUID, String shaOne, String senderFullJID) {
        String senderBareJID = senderFullJID.split("/")[0];
        if (shaOne == null) {return;}
        try {
            VCard vCard = new VCard();
            vCard.setAvatar(vCard.decodeAvatar(getCachedAvatar(shaOne)));
            RosterManager.getInstance().updatePhoto(vCard, accountUID, senderBareJID);
        } catch (FileNotFoundException e) {
            if (!(this.jidToShaOneMap.containsKey(senderBareJID))) {
                this.jidToShaOneMap.put(senderBareJID, shaOne);
                Tag vCardTag = new IQTag("getVCard", senderFullJID, "get", new VCardTag("vcard-temp"));
                PacketWriter.addToWriteQueue(vCardTag.setRecipientAccount(accountUID));
            }
        } catch (IOException e) {
            Log.d("IOException", e.toString());
        }
    }

    private void processVCardPacket(VCardTag vCardTag, String senderBareJID) {
        VCard vCard = new VCard();
        vCard.populateFromTag(vCardTag);
        RosterManager.getInstance().updatePhoto(vCard, vCardTag.getRecipientAccount(), senderBareJID);
        String shaOne = this.jidToShaOneMap.get(senderBareJID);
        this.jidToShaOneMap.remove(senderBareJID);
        try {
            FileOutputStream fileOutputStream = ChatApplication.getAppContext().openFileOutput(shaOne, Context.MODE_PRIVATE);
            fileOutputStream.write(vCardTag.getChildTag("PHOTO").getChildTag("BINVAL").getContent().getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.d("FileNotFoundException", e.toString());
        } catch (IOException e) {
            Log.d("IOException", e.toString());
        }

    }

    private void processQueryPacket(final Query queryTag, final Account account) {
        if (queryTag.getAttribute("xmlns").equals("jabber:iq:roster")) {
            RosterManager.getInstance().updateRosterList(queryTag);
            PacketWriter.addToWriteQueue(new Presence(UUID.randomUUID().toString(), account.getFullJID(), account.getStatus(), account.getShow()).setRecipientAccount(account.getAccountUid()));
        }
    }

    private String getCachedAvatar(String shaOne) throws IOException {
        String encodedAvatar = "";
        FileInputStream fileInputStream = ChatApplication.getAppContext().openFileInput(shaOne);
        StringBuffer fileContent = new StringBuffer("");
        byte [] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) != -1) {
            fileContent.append(new String(buffer));
        }
        fileInputStream.close();
        encodedAvatar = new String(fileContent);
        return encodedAvatar;
    }
}