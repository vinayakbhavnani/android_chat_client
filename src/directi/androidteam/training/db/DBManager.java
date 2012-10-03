package directi.androidteam.training.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import directi.androidteam.training.ChatApplication;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/2/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDB";
    private static final int DATABASE_VERSION = 1;


    private String TABLE_1_NAME = "messageTable";
    private String KEY_1_JID = "jid";
    private String KEY_1_MESSAGE = "message";
    private String KEY_1_ID = "id";

    private String TABLE_2_NAME = "users";
    private String KEY_2_USERNAME = "username";
    private String KEY_2_PASSWORD = "password";
    private String KEY_2_STATE = "login_status";

    private static DBManager dbManager = new DBManager();

    private DBManager() {
        super(ChatApplication.getAppContext(), DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_1_NAME + "(" + KEY_1_JID + " TEXT," + KEY_1_MESSAGE + " TEXT," + KEY_1_ID + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
        CREATE_TABLE = "CREATE TABLE " + TABLE_2_NAME + "(" + KEY_2_USERNAME + " TEXT," + KEY_2_PASSWORD + " TEXT," + KEY_2_STATE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_2_NAME);
        onCreate(sqLiteDatabase);
    }
    public SQLiteDatabase getWritableSQLiteDB() {
        return this.getWritableDatabase();
    }
    public SQLiteDatabase getReadableSQLiteDB() {
        return this.getReadableDatabase();
    }

    public static DBManager getDbManager() {
        return dbManager;
    }
}
