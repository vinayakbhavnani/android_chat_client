package directi.androidteam.training.chatclient.Roster.eventHandlers;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.RosterEntry;
import directi.androidteam.training.chatclient.Roster.RosterManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/20/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchRosterEntryDialog extends Dialog implements android.view.View.OnClickListener  {
    Context context;
    public SearchRosterEntryDialog(Context context) {
        super(context);
        this.context = context;
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
        ArrayList<RosterEntry> rosterEntries = rosterManager.searchRosterEntries(newJID);
        RosterEntry rosterEntry = rosterEntries.get(0);
        if(rosterEntry!=null) {
            Log.d("ROSTER UI : ","call to chat window");
            Intent intent = new Intent(context,ChatBox.class);
            intent.putExtra("buddyid",rosterEntry.getJid());
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else
        Log.d("ROSTER UI SEARCH","roster entry not found");
        return;
    }

}
