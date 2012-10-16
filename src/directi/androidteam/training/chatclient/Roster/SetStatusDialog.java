package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import directi.androidteam.training.chatclient.R;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/9/12
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetStatusDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_status_dialog, null);
        builder.setTitle(R.string.set_status)
               .setView(view)
               .setPositiveButton(R.string.set_status, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       String status = ((EditText) view.findViewById(R.id.new_status)).getText().toString();
                       Account currentAccount = ((DisplayRosterActivity)(Activity)getActivity()).getCurrentAccount();
                       (new SendStatusCumPresence(getActivity())).execute(currentAccount.getJID(), status, currentAccount.getPresence());
                       currentAccount.setStatus(status);
                       ((DisplayRosterActivity)(Activity)getActivity()).displayStatus(status);
                       dismiss();
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dismiss();
                   }
               });
        return builder.create();
    }
}
