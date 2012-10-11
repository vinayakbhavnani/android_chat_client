package directi.androidteam.training.chatclient.Roster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterSet;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/9/12
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddContactDialog extends DialogFragment {
    public void addRosterEntry(String newJID) {
        if (newJID == null || newJID.equals(""))
            return;
        RosterEntry rosterEntry = new RosterEntry(newJID);
        RosterSet rosterSet = new RosterSet();
        rosterSet.addQuery(rosterEntry.getJid());
        PacketWriter.addToWriteQueue(rosterSet.getTag().setRecipientAccount(JID.getJid().split("/")[0]));
        PresenceS presenceS = new PresenceS();
        presenceS.addReceiver(newJID);
        presenceS.addType("subscribe");
        PacketWriter.addToWriteQueue(presenceS.getTag().setRecipientAccount(JID.getJid().split("/")[0]));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_contact_dialog, null);
        builder.setTitle(R.string.add_contact)
                .setView(view)
                .setPositiveButton(R.string.add_contact, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addRosterEntry(((EditText) view.findViewById(R.id.new_contact)).getText().toString());
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
