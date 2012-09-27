package directi.androidteam.training.chatclient.Roster.eventHandlers;

import android.text.Editable;
import android.text.TextWatcher;
import directi.androidteam.training.chatclient.Roster.RosterEntry;
import directi.androidteam.training.chatclient.Roster.RosterManager;
import directi.androidteam.training.chatclient.Roster.SearchRosterEntry;

import java.util.ArrayList;

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
        ArrayList<RosterEntry> rosterEntries = rosterManager.searchRosterEntries(charSequence.toString());
        SearchRosterEntry.updateRosterList(rosterEntries);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}