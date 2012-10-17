package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Authentication;

import android.content.Context;
import android.widget.ArrayAdapter;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.gen.directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 8/10/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountListAdaptor extends ArrayAdapter<Account> {
    public AccountListAdaptor(Context context , ArrayList<Account> accounts){
        super(context, R.layout.accountitem,accounts);


    }
}
