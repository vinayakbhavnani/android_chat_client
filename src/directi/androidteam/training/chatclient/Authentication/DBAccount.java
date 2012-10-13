package directi.androidteam.training.chatclient.Authentication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import directi.androidteam.training.db.DBManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 9/10/12
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBAccount {
    public static final String ACCOUNT_USERNAME = "username";
    public static final String ACCOUNT_AUTHSTRING = "authstring";
    public static final String ACCOUNT_LOGINSTATUS = "loginstatus";
    public static final String ACCOUNT_SERVICE = "service";

    public boolean addAccountdb(Account account){
        SQLiteDatabase sqldb = DBManager.getDbManager().getWritableSQLiteDB();
        ContentValues values = new ContentValues();
        values.put(ACCOUNT_USERNAME,account.getAccountJid());
        values.put(ACCOUNT_AUTHSTRING,account.getPasswd());
        values.put(ACCOUNT_LOGINSTATUS,account.isLoginStatus().toString());
        values.put(ACCOUNT_SERVICE,account.service);
        sqldb.insert(DBManager.TABLE_2_NAME,null,values);
        sqldb.close();
        return true;
    }

    public boolean removeAccountdb(Account account){
        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();
        db.delete(DBManager.TABLE_2_NAME,ACCOUNT_USERNAME+"=?",new String[]{account.getAccountJid()});
        db.close();
        return true;
    }

    public HashMap<String,Account> getAllAccounts(){
        HashMap<String,Account> accountlist = new HashMap<String, Account>();

        String selectQuery = "SELECT * FROM " + DBManager.TABLE_2_NAME;

        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Account account = null;
        if (cursor.moveToFirst()) {
            do {
                Log.d("accountfoundDB",cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3));
                String service = cursor.getString(3);
                account = Account.createAccount(cursor.getString(0),cursor.getString(1),service,cursor.getString(2));
                accountlist.put(cursor.getString(0),account);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accountlist;
    }

    public void saveAccountState(Collection<Account> accounts){
        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();
        for(Account account:accounts){
            ContentValues values = new ContentValues();
            Log.d("loginstatussave",account.isLoginStatus().toString());
            values.put(ACCOUNT_LOGINSTATUS,account.isLoginStatus().toString());
            db.update(DBManager.TABLE_2_NAME,values,ACCOUNT_USERNAME+"=?",new String[]{account.getAccountJid()});
        }
        db.close();
    }
}
