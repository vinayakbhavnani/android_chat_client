package directi.androidteam.training.chatclient.Chat;

import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 13/9/12
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatListItem {
    private String username;
    private boolean sender;
    private static final int sendResource = R.layout.chatlistitem;
    private static final int receiveResource = R.layout.chatlistitem_r;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSender() {
        return sender;
    }

    public void setSender(boolean sender) {
        this.sender = sender;
    }

    public ChatListItem(String name,String mess,boolean send){
        this.message=mess;
        this.sender=send;
        this.username=name;
    }

    public int getResourceID(){
        if(isSender())
            return sendResource;
        else
            return receiveResource;
    }
}
