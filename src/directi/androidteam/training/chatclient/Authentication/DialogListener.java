package directi.androidteam.training.chatclient.Authentication;

import android.content.DialogInterface;
import android.util.Log;
import directi.androidteam.training.TagStore.Presence;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 10/10/12
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DialogListener implements DialogInterface.OnClickListener {
    ArrayList<String> options;
    Account account;
    AccountListAdaptor adaptor;
    public DialogListener(ArrayList<String> list , Account account,AccountListAdaptor adaptor){
        this.account = account;
        this.options = list;
        this.adaptor = adaptor;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Log.d("dialogclick",options.get(i));
        String option =  options.get(i);
        if(option.equals("Login"))
            new LoginTask(account).execute();
        else if(option.equals("Logout"))
            account.Logout();
        else if(option.equals("Edit Password")) {
            Log.d("editpass","TODO");
            account.sendAvail("available");
        }
        else if(option.equals("Remove Account"))
            AccountManager.getInstance().removeAccount(account);
        else if(option.equals("Set Status")){
            account.sendStatus("status10");

        }
        adaptor.notifyDataSetChanged();
        dialogInterface.dismiss();

    }

    private void setStatus(String status) {
        Presence presence = new Presence();
        presence.setStatus(status);
        presence.setRecipientAccount(account.getAccountUid());
  //      presence.setShow("dnd");//account.getShow());
        account.setStatus(status);
        PacketWriter.addToWriteQueue(presence);
    }
    private void setAvail(String avail) {
        Presence presence = new Presence();
        presence.setShow(avail);
        presence.setRecipientAccount(account.getAccountUid());
//        presence.setStatus("stat2");//account.getStatus());
        account.setShow(avail);
        PacketWriter.addToWriteQueue(presence);
    }
}
