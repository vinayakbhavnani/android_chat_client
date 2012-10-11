package directi.androidteam.training.chatclient.Roster;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/26/12
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextChangeListenerForSearch implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        return;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        RosterManager rosterManager = RosterManager.getInstance();
      //  ArrayList<RosterEntry> rosterEntries = rosterManager.searchRosterEntries(charSequence.toString());
       // SearchRosterEntry.updateRosterList(rosterEntries);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        return;
    }
}
