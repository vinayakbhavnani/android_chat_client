package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/10/12
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountManager {
    private static final AccountManager accountManager = new AccountManager();
    private HashMap<String,Account> userAccounts;
    public Activity initialActivity;
    private AccountManager(){
        fetchAccountAllDB();

    }
    public static AccountManager getInstance(){
        return accountManager;
    }

    public void addAccount(Account account){
        this.userAccounts.put(account.accountUid,account);
        DBAccount dba = new DBAccount();
        dba.addAccountdb(account);
    }
    public void removeAccount(Account account){
        this.userAccounts.remove(account.getAccountUid());
        DBAccount dba = new DBAccount();
        dba.removeAccountdb(account);
    }
    public Account getAccount(String jid){
        return this.userAccounts.get(jid);
    }

    public ArrayList<Account> getAccountList(){
       Collection<Account> collection =  userAccounts.values();

       ArrayList<Account> accounts = new ArrayList<Account>(collection);
       return accounts;
    }

    private void fetchAccountAllDB(){
        DBAccount dba = new DBAccount();
        this.userAccounts = dba.getAllAccounts();
    }

    public int getnumAccount(){
        return userAccounts.keySet().size();
    }

    public Account getAccountfromList(int i){
        Collection<Account> collection =  userAccounts.values();

        ArrayList<Account> accounts = new ArrayList<Account>(collection);
        return accounts.get(i);
    }

    public boolean isGoogleAccountAdded(String username){
        for (Account account : userAccounts.values()) {
            if(username.equals(account.accountUid))
                return true;
        }
        return false;
    }

    public int loginAccounts(){
        int ret = 0;
        Collection<Account> collection = userAccounts.values();
        if(collection.size()==0)
            ret = -1;
        if(!NetworkManager.connected)
            return ret;
        for(Account acc:collection){
            Log.d("loginstatus", acc.getPersistedLoginStatus().toString());
            if(acc.getPersistedLoginStatus().equals(LoginStatus.ONLINE) && acc.isLoginStatus().equals(LoginStatus.OFFLINE)){
                new LoginTask(acc).execute();
                ret++;
            }
        }
        return ret;
    }

    public void saveAccountState(){
        DBAccount dba = new DBAccount();
        dba.saveAccountState(userAccounts.values());
    }

    public void addSubscribers(Subscriber subscriber){
        if(userAccounts.size()>0){
            String s = (String)userAccounts.keySet().toArray()[0];
            userAccounts.get(s).addSubscriber(subscriber);

        }
    }

    public void removeSubscribers(Subscriber subscriber){
        if(userAccounts.size()>0){
            String s = (String)userAccounts.keySet().toArray()[0];
            userAccounts.get(s).removeSubscriber(subscriber);

        }
    }
}
