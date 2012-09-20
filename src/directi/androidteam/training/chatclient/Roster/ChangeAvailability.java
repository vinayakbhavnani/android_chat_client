package directi.androidteam.training.chatclient.Roster;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/19/12
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChangeAvailability extends Dialog implements android.view.View.OnClickListener {
    public ChangeAvailability(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Button)findViewById(R.id.Roster_add_new_entry_button)).setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Log.d("ROSTER UI :", "change availability flow complete");
        Spinner spinner = (Spinner) findViewById(R.id.chatlist); //add spinner
        EditText editText = (EditText)findViewById(R.id.Roster_new_jid);
        String avail  = editText.getText().toString();
        Log.d("ROSTER UI :","new status : "+avail);
        dismiss();
        RosterManager rosterManager = RosterManager.getInstance();
        rosterManager.changeAvailability(avail);
        return;
    }
}
