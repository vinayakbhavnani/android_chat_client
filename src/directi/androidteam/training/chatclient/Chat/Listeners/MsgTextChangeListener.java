package directi.androidteam.training.chatclient.Chat.Listeners;

import android.text.Editable;
import android.text.TextWatcher;
import directi.androidteam.training.StanzaStore.MessageStanza;

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
    String AccountUID;

    public MsgTextChangeListener(String from, String AccountUID) {
        this.to = from;
        this.AccountUID = AccountUID;
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
            messageStanza.send(AccountUID);
            msgSent = true;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        return;
    }
}
