package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.ConnectionHandler;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatBox extends Activity {
    private final String buddy="vinayak.bhavnani@gmail.com";
    private ArrayList<String> chatlist;
    private ArrayAdapter<String> adaptor;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        ListView list = (ListView) findViewById(R.id.chatlist);
        chatlist = new ArrayList<String>();
        adaptor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,chatlist);
        list.setAdapter(adaptor);

    }

    public void SendChat(View view){
        EditText mess = (EditText) findViewById(R.id.message);
        String message = mess.getText().toString();
        String messxml = new MessageStanza(buddy,message).getXml();
        PacketWriter.addToWriteQueue(messxml);
        chatlist.add(message);
        adaptor.notifyDataSetChanged();
    }
}
