package directi.androidteam.training.chatclient.Chat;

import android.util.Log;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String time;
    private boolean status=true;
    private String id;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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
        return this.sender;
    }

    public void setSender(boolean sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChatListItem(MessageStanza message){
        this.message=message.getBody();
        this.sender=ChatFragment.isSender(message);
        this.username=message.getFrom();
        Date date = new Date(message.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.time = dateFormat.format(new Date(message.getTime()));
        this.id = message.getID();
        this.status = message.isStatus();
        Log.d("isSenderCreate",this.message+" "+this.isSender());

    }

    public int getResourceID(){
        if(isSender())
            return sendResource;
        else
            return receiveResource;
    }
}
