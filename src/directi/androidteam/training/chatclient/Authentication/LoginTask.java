package directi.androidteam.training.chatclient.Authentication;

import android.os.AsyncTask;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 12/10/12
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginTask extends AsyncTask<Object,Void,Boolean> {
    Account account;
    public LoginTask(Account acc){
        this.account = acc;
    }
    @Override
    protected Boolean doInBackground(Object... objects) {
        account.Login();
        return true;
    }
}
