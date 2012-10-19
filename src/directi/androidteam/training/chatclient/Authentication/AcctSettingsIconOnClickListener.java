package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/18/12
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcctSettingsIconOnClickListener implements View.OnClickListener {
    private ArrayList<String> loginList;
    private ArrayList<String> logoutList;
    private AccountListAdaptor adaptor;
    private Activity activity;
    public AcctSettingsIconOnClickListener(ArrayList<String> loginList, ArrayList<String> logoutList, AccountListAdaptor adaptor, Activity activity) {
        this.loginList = loginList;
        this.logoutList = logoutList;
        this.adaptor = adaptor;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Account account = AccountManager.getInstance().getAccount((String)view.getTag());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Settings");
        final ArrayList<String> temp;
        if(account.isLoginStatus().equals(LoginStatus.ONLINE)){
            temp = logoutList;
        }
        else
            temp = loginList;
        builder.setItems(temp.toArray(new CharSequence[temp.size()]),new DialogListener(temp,account,adaptor));
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
