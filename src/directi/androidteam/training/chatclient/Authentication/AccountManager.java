package directi.androidteam.training.chatclient.Authentication;

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
        this.userAccounts = new HashMap<String, Account>();
    }
    public static AccountManager getInstance(){
        return accountManager;
    }

    public void addAccount(Account account){
        this.userAccounts.put(account.accountJid,account);
    }
    public Account getAccount(String jid){
        return this.userAccounts.get(jid);
    }
}
