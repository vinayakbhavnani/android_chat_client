package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Roster;

import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.TagStore.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/8/12
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Account {
    private String JID;
    private String status;
    private String presence;
    private Tag queryTag;

    public Account(String JID, String status, String presence, Tag queryTag) {
        this.JID = JID;
        this.status = status;
        this.presence = presence;
        this.queryTag = queryTag;
    }

    public String getJID() {
        return this.JID;
    }

    public String getStatus() {
        return this.status;
    }

    public String getPresence() {
        return this.presence;
    }

    public Tag getQueryTag() {
        return this.queryTag;
    }

    public void setJID (String JID) {
        this.JID = JID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public void setQueryTag(Tag queryTag) {
        this.queryTag = queryTag;
    }
}
