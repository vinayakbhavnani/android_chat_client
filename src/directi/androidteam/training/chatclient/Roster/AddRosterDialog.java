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
 * Date: 9/18/12
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddRosterDialog extends Dialog implements android.view.View.OnClickListener {
    public AddRosterDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Button)findViewById(R.id.Roster_add_new_entry_button)).setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Log.d("ROSTER UI :", "add new roster flow complete");
        EditText editText = (EditText)findViewById(R.id.Roster_new_jid);
        String newJID  = editText.getText().toString();
        Log.d("ROSTER UI :","new jid : "+newJID);
        dismiss();
        RosterManager rosterManager = RosterManager.getInstance();
        rosterManager.addRosterEntry(newJID);
        return;
    }
}

