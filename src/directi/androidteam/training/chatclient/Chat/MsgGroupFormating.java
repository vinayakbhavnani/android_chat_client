package directi.androidteam.training.chatclient.Chat;

import directi.androidteam.training.StanzaStore.MessageStanza;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/4/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MsgGroupFormating {
    private MessageStanza last;
    private MessageStanza present;
    public MsgGroupFormating(MessageStanza lastMessageStanza, MessageStanza ms) {
        this.last = lastMessageStanza;
        this.present = ms;
    }

    public Boolean formatMsg() {
        if(last.getMsgMergedCount()>2) {
            return false;
        }
        if(present.getTime() - last.getTime() < 10000)
            return true;
        else return false;
    }

    public ArrayList<MessageStanza> formatMsgList(ArrayList<MessageStanza> messageStanzas) {
        if(messageStanzas==null || messageStanzas.size()==1)
            return messageStanzas;
        ArrayList<MessageStanza> newMesgStanza = new ArrayList<MessageStanza>();
        MessageStanza m = messageStanzas.get(0);
        newMesgStanza.add(m);
        messageStanzas.remove(0);
        last = newMesgStanza.get(0);
        last.setMsgMergedCount(0);
        for (MessageStanza messageStanza : messageStanzas) {
            present = messageStanza;
            Boolean b = formatMsg();
            if(b==false) {
                newMesgStanza.add(messageStanza);
                messageStanza.setMsgMergedCount(0);
                last = messageStanza;
            }
        }
        return newMesgStanza;
    }
}
