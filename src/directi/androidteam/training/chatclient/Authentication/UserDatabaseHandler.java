package directi.androidteam.training.chatclient.Authentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import directi.androidteam.training.db.DBManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/13/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDatabaseHandler  {
    private static final String TABLE_USERS = "users";
    private static final String KEY_USERNAME= "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_STATE = "login_status";
    private static final String LOGGED_IN = "online";
    private static final String LOGGED_OUT = "offline";

    public UserDatabaseHandler(Context context) {

    }

    public void addUser(User user) {
        if (!(getPassword(user.getUsername()).equals("NO_SUCH_USER"))) {
            updateState(user.getUsername(), LOGGED_IN);
            return;
        } else {
            SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();

            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, user.getUsername());
            values.put(KEY_PASSWORD, user.getPassword());
            values.put(KEY_STATE, LOGGED_IN);

            db.insert(TABLE_USERS, null, values);
            db.close();
        }
    }

    public String getPassword(String username) {
        SQLiteDatabase db = DBManager.getDbManager().getReadableSQLiteDB();

        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_USERNAME, KEY_PASSWORD}, KEY_USERNAME + "=?", new String[] {username}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        String pwd;
        try {
            pwd = cursor.getString(1);
        } catch (CursorIndexOutOfBoundsException e) {
            cursor.close();
            db.close();
            return "NO_SUCH_USER";
        }
        cursor.close();
        db.close();
        return pwd;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                user.setState(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public int updateState(String username, String state) {
        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();

        ContentValues values = new ContentValues();
        values.put(KEY_STATE, state);

        int ans =  db.update(TABLE_USERS, values, KEY_USERNAME + " = ?", new String[] {String.valueOf(username)});
        db.close();
        return ans;
    }
}
