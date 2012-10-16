package directi.androidteam.training.chatclient.Roster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String KEY_PHOTO_SHA_ONE = "avatar_sha_one";

    public VCardDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_V_CARDS_TABLE = "CREATE TABLE " + TABLE_V_CARDS + "(" + KEY_PHOTO_SHA_ONE + " TEXT" + ")";
        db.execSQL(CREATE_V_CARDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_V_CARDS);
        onCreate(db);
    }

    public void addShaOne(String shaOne) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHOTO_SHA_ONE, shaOne);

        db.insert(TABLE_V_CARDS, null, values);
        db.close();
    }

    public boolean checkShaOne(String shaOne) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_V_CARDS, new String[] { KEY_PHOTO_SHA_ONE }, KEY_PHOTO_SHA_ONE + "=?", new String[] { String.valueOf(shaOne) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return true;
        }
        return false;
    }
}
