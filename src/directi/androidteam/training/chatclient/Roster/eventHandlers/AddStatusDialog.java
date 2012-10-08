package directi.androidteam.training.chatclient.Roster.eventHandlers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.Account;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;
import directi.androidteam.training.chatclient.Roster.SendStatusCumPresence;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/19/12
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddStatusDialog extends Dialog implements android.view.View.OnClickListener {
    private Context context;
    public AddStatusDialog(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Button)findViewById(R.id.Roster_add_new_status_button)).setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        String status = ((EditText) findViewById(R.id.Roster_enter_status)).getText().toString();
        Account currentAccount = ((DisplayRosterActivity)context).getCurrentAccount();
        (new SendStatusCumPresence((Activity)context)).execute(currentAccount.getJID(), status, currentAccount.getPresence());
        currentAccount.setStatus(status);
        ((DisplayRosterActivity)context).setStatus(status);
        dismiss();
        return;
    }
}
