package directi.androidteam.training.Main;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/30/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageRequest {
    IEncoder encoder ;

    public MessageRequest() {
        this.encoder = new XMLEncoder();
    }

    public MessageRequest(IEncoder encoder) {
        this.encoder = encoder;
    }

    public void addXmppID(String xmppID) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setContent(String content) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setMessageType(MessageType messageType) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setSenderXmppID(String xmppID) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public Byte[] toByteArray() {
        return new Byte[0];  //To change body of created methods use File | Settings | File Templates.
    }
}
