package directi.androidteam.training.Main;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/29/12
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private final ID senderXmppID;
    private final ID[] receiverXmppIDs;
    private final MessageType messageType;
    private final String content;

    public Message(ID senderXmppID, ID[] receiverXmppIDs, MessageType messageType, String content) {
        this.senderXmppID = senderXmppID;
        this.receiverXmppIDs = receiverXmppIDs;
        this.messageType = messageType;
        this.content = content;
    }

    public ID[] getReceiverXmppIDs() {
        return receiverXmppIDs;
    }

    public ID getSenderXmppID() {
        return senderXmppID;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getContent() {
        return content;
    }
}
