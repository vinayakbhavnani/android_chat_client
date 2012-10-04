package directi.androidteam.training.chatclient.Chat.dbAccess;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.db.DBManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/2/12
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class dbAccess {

    public void addMessage(MessageStanza messageStanza) {
        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();
        ContentValues values = new ContentValues();
        values.put(DBManager.KEY_1_JID_SENDER,messageStanza.getFrom());
        values.put(DBManager.KEY_1_JID_RECEIVER,messageStanza.getTo());
        values.put(DBManager.KEY_1_MESSAGE,messageStanza.getBody());
        values.put(DBManager.KEY_1_ID,messageStanza.getID());
        values.put(DBManager.KEY_1_TIME,messageStanza.getTime());
        db.insert(DBManager.TABLE_1_NAME,null,values);
        db.close();
        return;
    }

    public ArrayList<MessageStanza> getAllMsg() {
        SQLiteDatabase db = DBManager.getDbManager().getReadableSQLiteDB();
        Cursor cursor = db.query(DBManager.TABLE_1_NAME, null, null , null, null, null, null);
        ArrayList<MessageStanza> messageStanzas = new ArrayList<MessageStanza>();
        if (cursor==null)
            return messageStanzas;
        cursor.moveToFirst();
        try {
             do {
                 String to = cursor.getString(1);
                 String body = cursor.getString(2);
                 MessageStanza messageStanza = new MessageStanza(to,body);
                 messageStanza.setCreater(cursor.getString(0));
                 messageStanza.setFrom(cursor.getString(0));
                 messageStanza.setStatus(true);
                 messageStanza.setID(cursor.getString(3));
                 messageStanza.setTime(cursor.getLong(4));
                 messageStanzas.add(messageStanza);
             } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException e) {
            cursor.close();
            db.close();
            return messageStanzas;
        }
        cursor.close();
        db.close();
        return messageStanzas;
    }

    public ArrayList<MessageStanza> getMsgByJID(String jid) {
        return null;
    }

    public void removeMsg(String id) {
        SQLiteDatabase db = DBManager.getDbManager().getWritableSQLiteDB();
    }
}
