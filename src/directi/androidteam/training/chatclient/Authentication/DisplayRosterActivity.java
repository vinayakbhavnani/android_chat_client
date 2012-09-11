package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.PacketWriter;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/3/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayRosterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        Intent intent = getIntent();
        String username =  intent.getStringExtra(LoginActivity.USERNAME);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Welcome " + username);
        setContentView(textView);
        requestForRosters(username);
    }

    private void requestForRosters(String username) {
        Log.d("DEBUG :","entered request for roster");
        RosterGet rosterGet = new RosterGet(username+"@gmail.com","roster_1");
        PacketWriter.addToWriteQueue(rosterGet.getXml());
    }

}
