package directi.androidteam.training.Main;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/29/12
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageFactory {
    private ID senderXmppID;
    private ID[] receiverXmppIDs;
    private MessageType messageType;
    private String content;

    public MessageFactory setSenderXmppID(ID senderXmppID) {
        this.senderXmppID = senderXmppID;
        return this;
    }

    public MessageFactory setReceiverXmppIDs(ID[] receiverXmppIDs) {
        this.receiverXmppIDs = receiverXmppIDs;
        return this;
    }

    public MessageFactory setMessageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public MessageFactory setContent(String content) {
        this.content = content;
        return this;
    }

    public Message getMessage() {
        return new Message(senderXmppID, receiverXmppIDs, messageType, content);
    }
}
