package directi.androidteam.training.chatclient.Authentication;

import android.content.Context;
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
    Context context;
    public DialogListener(ArrayList<String> list , Account account,AccountListAdaptor adaptor,Context context){
        this.account = account;
        this.options = list;
        this.adaptor = adaptor;
        this.context = context;
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
            //account.sendAvail("available");
        }
        else if(option.equals("Remove Account"))
            AccountManager.getInstance().removeAccount(account);
        else if(option.equals("Set Status")){
            DialogBuilder.createSetStatusDialog(context,account);

        }
        adaptor.notifyDataSetChanged();
        dialogInterface.dismiss();

    }


}
