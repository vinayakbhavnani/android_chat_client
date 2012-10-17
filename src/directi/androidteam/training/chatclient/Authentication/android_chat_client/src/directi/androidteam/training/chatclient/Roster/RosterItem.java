package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Roster;

import android.graphics.Bitmap;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/9/12
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterItem {
    private String account;
    private String bareJID;
    private String status;
    private String presence;
    private VCard vCard;

    public String getBareJID() {
        return this.bareJID;
    }

    public void setBareJID(String bareJID) {
        this.bareJID = bareJID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPresence() {
        return this.presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getName() {
        return this.vCard.getName();
    }

    public Bitmap getAvatar() {
        return this.vCard.getAvatar();
    }

    public void setVCard(VCard vCard) {
        this.vCard = vCard;
    }
}
