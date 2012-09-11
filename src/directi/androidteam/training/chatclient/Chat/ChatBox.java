package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.ConnectionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatBox extends Activity {
    private final String buddy="vinayak.bhavnani@gmail.com";



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

    }

    public void SendChat(View view){
        EditText mess = (EditText) findViewById(R.id.message);
        String message = mess.getText().toString();
        ConnectionHandler.getWriter().write(new MessageStanza(buddy,"heybuddy").getXml());
        ConnectionHandler.getWriter().flush();
    }
}
