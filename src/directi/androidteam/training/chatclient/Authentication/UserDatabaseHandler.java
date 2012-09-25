package directi.androidteam.training.chatclient.Authentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/13/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_USERS = "users";
    private static final String KEY_USERNAME= "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_STATE = "login_status";
    private static final String LOGGED_IN = "online";
    private static final String LOGGED_OUT = "offline";

    public UserDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_STATE + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        if (!(getPassword(user.getUsername()).equals("NO_SUCH_USER"))) {
            updateState(user.getUsername(), LOGGED_IN);
            return;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, user.getUsername());
            values.put(KEY_PASSWORD, user.getPassword());
            values.put(KEY_STATE, LOGGED_IN);

            db.insert(TABLE_USERS, null, values);
            db.close();
        }
    }

    public String getPassword(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

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

        SQLiteDatabase db = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATE, state);

        return db.update(TABLE_USERS, values, KEY_USERNAME + " = ?", new String[] {String.valueOf(username)});
    }
}
