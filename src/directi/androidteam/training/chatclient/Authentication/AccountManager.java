package directi.androidteam.training.chatclient.Authentication;

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
    private AccountManager(){
        fetchAccountAllDB();
    }
    public static AccountManager getInstance(){
        return accountManager;
    }

    public void addAccount(Account account){
        this.userAccounts.put(account.accountJid,account);
        DBAccount dba = new DBAccount();
        dba.addAccountdb(account);
    }
    public void removeAccount(Account account){
        this.userAccounts.remove(account.getAccountJid());
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
}
