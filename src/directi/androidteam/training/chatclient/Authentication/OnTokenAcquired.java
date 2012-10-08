package directi.androidteam.training.chatclient.Authentication;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class OnTokenAcquired implements AccountManagerCallback<Bundle> {
    Activity con;
    public OnTokenAcquired(Activity context){
        con = context;
    }
    @Override
    public void run(AccountManagerFuture<Bundle> result) {
        // Get the result of the operation from the AccountManagerFuture.
        try{
            Log.d("callback","callback");
        Bundle bundle = result.getResult();
        String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
        (new ConnectGTalk(con)).execute("vinayak.bhavnani@gmail.com", token);
        }
        catch (Exception e){}
        // The token is a named value in the bundle. The name of the value
        // is stored in the constant AccountManager.KEY_AUTHTOKEN.


    }
}