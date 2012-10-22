package directi.androidteam.training.chatclient.Roster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/11/12
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class VCardDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vCardsManager";
    private static final String TABLE_V_CARDS = "v_cards";
    private static final String KEY_ACCOUNT_CUM_JID = "account_cum_jid";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_AVATAR_EXISTS = "avatar_exist";
    private static final String YES = "avatar_exists";
    private static final String NO = "avatar_does_not_exist";

    public VCardDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_V_CARDS_TABLE = "CREATE TABLE " + TABLE_V_CARDS + "(" + KEY_ACCOUNT_CUM_JID + " TEXT, " + KEY_FULL_NAME + " TEXT, " + KEY_AVATAR_EXISTS + " TEXT" + ")";
        db.execSQL(CREATE_V_CARDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_V_CARDS);
        onCreate(db);
    }

    public void addVCard(String accountCumJID, String fullName, String avatarExists) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT_CUM_JID, accountCumJID);
        values.put(KEY_FULL_NAME, fullName);
        values.put(KEY_AVATAR_EXISTS, avatarExists);

        db.insert(TABLE_V_CARDS, null, values);
        db.close();
    }

    public String getFullName(String accountCumJID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_V_CARDS, new String[] { KEY_ACCOUNT_CUM_JID, KEY_FULL_NAME, KEY_AVATAR_EXISTS }, KEY_ACCOUNT_CUM_JID + "=?", new String[] { String.valueOf(accountCumJID) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        String fullName = null;
        try {
            fullName = cursor.getString(1);
        } catch (CursorIndexOutOfBoundsException e) {
        }

        cursor.close();
        db.close();
        return fullName;
    }

    public String avatarExists(String accountCumJID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_V_CARDS, new String[] { KEY_ACCOUNT_CUM_JID, KEY_FULL_NAME, KEY_AVATAR_EXISTS }, KEY_ACCOUNT_CUM_JID + "=?", new String[] { String.valueOf(accountCumJID) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        String exists = null;
        try {
            exists = cursor.getString(2);
        } catch (CursorIndexOutOfBoundsException e) {
        }

        cursor.close();
        db.close();
        return exists;
    }
}
