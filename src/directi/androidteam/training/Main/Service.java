package directi.androidteam.training.Main;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/30/12
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Service implements XMPPServiceI {
    @Override
    public void sendMessage(Message message) {
        MessageRequest messageRequest = createMessage(message);
        MessageResponse messageResponse = sendMessageAndParseResponse(messageRequest);

    }
    public MessageRequest createMessage(Message message) {
        MessageRequest messageRequest = new MessageRequest();
        for (ID ID : message.getReceiverXmppIDs()) {
            messageRequest.addXmppID(ID.getXmppID());
        }
        messageRequest.setContent(message.getContent());
        messageRequest.setMessageType(message.getMessageType());
        if(message.getSenderXmppID()!=null){
            messageRequest.setSenderXmppID(message.getSenderXmppID().getXmppID());
        }
        return messageRequest;
    }
    public MessageResponse sendMessageAndParseResponse(MessageRequest messageRequest){
        MessageResponse messageResponse = new MessageResponse();
        Byte[] bytes = messageRequest.toByteArray();
        messageResponse.fromByteArray(bytes);
        return messageResponse;
    }
}
