package directi.androidteam.training.chatclient.Chat.Listeners;

import android.text.Editable;
import android.text.TextWatcher;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MsgTextChangeListener implements TextWatcher {
    private Boolean msgSent =false;
    private String to;

    public MsgTextChangeListener(String from) {
        this.to = from;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        return;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(charSequence.length()==1 && !msgSent) {
            MessageStanza messageStanza = new MessageStanza(to);
            messageStanza.formComposingMsg();
            PacketWriter.addToWriteQueue(messageStanza.getXml());
            msgSent = true;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        return;
    }
}
