package directi.androidteam.training.chatclient.Roster;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/20/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchRosterEntryDialog extends Dialog implements android.view.View.OnClickListener  {
    public SearchRosterEntryDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Button)findViewById(R.id.Roster_enter_search_button)).setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Log.d("ROSTER UI :", "search roster flow complete");
        EditText editText = (EditText)findViewById(R.id.Roster_enter_search);
        String newJID  = editText.getText().toString();
        Log.d("ROSTER UI :","new jid : "+newJID);
        dismiss();
        RosterManager rosterManager = RosterManager.getInstance();
        RosterEntry rosterEntry = rosterManager.searchRosterEntry(newJID);
        if(rosterEntry!=null) {
            Log.d("ROSTER UI : ","call to chat window");
        }
        else
        Log.d("ROSTER UI SEARCH","roster entry not found");
        return;
    }

}
