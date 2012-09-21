package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 21/9/12
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class dummyactivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.d("dummyactivity","created");
        super.onCreate(savedInstanceState);
        moveTaskToBack(true);
        String from = (String) getIntent().getExtras().get("buddyid");
        Log.d("dummyextras",from);
        //Intent intent = new Intent(this,ChatBox.class);
        //intent.putExtra("buddyid", (String) getIntent().getExtras().get("buddyid"));
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //startActivity(intent);
        //ChatBox.openChat(from);
        Log.d("chatboxspawn","true");
    }
}
