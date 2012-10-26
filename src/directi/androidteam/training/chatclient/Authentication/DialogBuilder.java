package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 25/10/12
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class DialogBuilder {
    public static void createSetStatusDialog(Context context, final Account account){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Set Status");
        final EditText input = new EditText(context);
        builder.setView(input);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String statusText = input.getText().toString();
                account.sendStatus(statusText);
                Log.d("SetStatus","Ok "+statusText);
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("SetStatus","Cancel");
            }
        });
        builder.show();
    }

    public static void createImportAccountDialog(final Context context){
        final android.accounts.Account[] accounts = android.accounts.AccountManager.get(context).getAccountsByType("com.google");
        final ArrayList<android.accounts.Account> selectedAccounts = new ArrayList<android.accounts.Account>();
        CharSequence[] accountnames = new CharSequence[accounts.length];
        for(int i=0;i<accounts.length;i++){
            accountnames[i] = accounts[i].name;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Import Accounts");
        builder.setMultiChoiceItems(accountnames,null,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
                    Log.d("selectAccount","OK");
                    selectedAccounts.add(accounts[i]);
                }
                else{
                    Log.d("selectAccount","NO");
                    selectedAccounts.remove(accounts[i]);
                }

            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Import",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ImportGoogleAccount iga = new ImportGoogleAccount((Activity)context);
                iga.execute(selectedAccounts);
            }
        });
        builder.show();
    }
}
