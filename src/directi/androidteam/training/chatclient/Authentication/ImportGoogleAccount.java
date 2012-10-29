package directi.androidteam.training.chatclient.Authentication;

import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import directi.androidteam.training.chatclient.Authentication.Account;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 25/10/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImportGoogleAccount extends AsyncTask<ArrayList<android.accounts.Account>,Void,Boolean> {
    private Activity parentActivity;

    public ImportGoogleAccount(Activity parent){
        this.parentActivity = parent;
    }
    @Override
    protected Boolean doInBackground(ArrayList<android.accounts.Account>... voids) {
        importAccounts(voids[0]);
        return null;
    }

    public void importAccounts(ArrayList<android.accounts.Account> accounts ){
        Log.d("importaccounts","OK");
        for (android.accounts.Account account : accounts) {
            importSingleAccount(account);
        }


    }

    public void importSingleAccount(android.accounts.Account account){
        Log.d("importaccount",account.name);
        Account newaccount = Account.createAccount(account.name,getAuthToken(account),"gtalkauth",LoginStatus.OFFLINE.toString());
        AccountManager.getInstance().addAccount(newaccount);
        new LoginTask(newaccount).execute();
    }

    public android.accounts.Account getAndroidAccount(String username){
        android.accounts.Account[] accounts = android.accounts.AccountManager.get(parentActivity).getAccountsByType("com.google");
        for (android.accounts.Account account : accounts) {
            if(account.name.equals(username))
                return account;
        }
        return null;

    }

    public String getAuthToken(android.accounts.Account myaccount){
        AccountManagerFuture<Bundle> accFut = android.accounts.AccountManager.get(parentActivity).getAuthToken(myaccount,"mail",null,parentActivity,null,null);
        try{
            Bundle authTokenBundle = accFut.getResult();
            String authToken = authTokenBundle.get(android.accounts.AccountManager.KEY_AUTHTOKEN).toString();
            android.accounts.AccountManager.get(parentActivity).invalidateAuthToken("com.google",authToken);
            accFut = android.accounts.AccountManager.get(parentActivity).getAuthToken(myaccount,"mail",null,parentActivity,null,null);
            authTokenBundle = accFut.getResult();
            authToken = authTokenBundle.get(android.accounts.AccountManager.KEY_AUTHTOKEN).toString();
            String username=myaccount.name;
            Log.d("myusername", username);
            String password=authToken;
            Log.d("authtoken",password);
            return password;
            //Account account = Account.createAccount();
        }
        catch (Exception e){Log.d("Entering","NO");}

        return null;
    }


}
