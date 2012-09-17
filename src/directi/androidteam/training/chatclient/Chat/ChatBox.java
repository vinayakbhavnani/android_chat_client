package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.R;
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
    private static Context context;
    //private final String buddy="vinayak.bhavnani@gmail.com";
    private ArrayList<String> chatlist;
    private ArrayAdapter<String> adaptor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        context=this;
        switchFragment((String)getIntent().getExtras().get("buddyid"));
        //ListView list = (ListView) findViewById(R.id.chatlist);
        //chatlist = new ArrayList<String>();
        //adaptor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,chatlist);
        //list.setAdapter(adaptor);

    }

    public static void openChat(String from){
        Intent intent = new Intent(context, ChatBox.class);
        intent.putExtra("buddyid",from);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onNewIntent(Intent intent){
        Log.d("newintent","intent");
        super.onNewIntent(intent);
        String from = (String)intent.getExtras().get("buddyid");
        if(from!=null)
            switchFragment(from);
    }

    public void SendChat(View view){
        EditText mess = (EditText) findViewById(R.id.message);
        String message = mess.getText().toString();
        String buddy = ((ChatFragment)getFragmentManager().findFragmentById(R.id.chatlist)).getBuddyid();
        MessageStanza messxml = new MessageStanza(buddy,message);
        PacketWriter.addToWriteQueue(messxml.getXml());
        //chatlist.add(message);
        //adaptor.notifyDataSetChanged();
        ChatFragment fragment =  (ChatFragment)getFragmentManager().findFragmentById(R.id.chatlist);
        fragment.insertMessage(messxml);
    }

    private void switchFragment(String from){
        Log.d("switchfrag","switched");
        ChatFragment curfrag = (ChatFragment)getFragmentManager().findFragmentById(R.id.chatlist);
        if(curfrag.getBuddyid().equals(from)){
            curfrag.notifyAdaptor();
            return;
        }
        curfrag = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("from",from);
        curfrag.setArguments(args);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.chatlist, curfrag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
