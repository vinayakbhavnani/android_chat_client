package directi.androidteam.training.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/2/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBManager extends SQLiteOpenHelper {
    String TABLE_NAME = "messageTable";
    String KEY_JID = "jid";
    String KEY_MESSAGE = "message";
    String KEY_ID = "id";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_JID + " TEXT," + KEY_MESSAGE + " TEXT," + KEY_ID + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public SQLiteDatabase getWritableSQLiteDB() {
        return this.getWritableDatabase();
    }
    public SQLiteDatabase getReadableSQLiteDB() {
        return this.getReadableDatabase();
    }
}
