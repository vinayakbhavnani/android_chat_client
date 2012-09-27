package directi.androidteam.training.chatclient.Roster.eventHandlers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.Roster.RosterEntry;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/21/12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class rosterListClickHandler implements  AdapterView.OnItemClickListener {
    ListView rosterList;
    Context context;
    public rosterListClickHandler(ListView rosterList,Context context) {
        this.rosterList = rosterList;
        this.context=context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("kkk","listener working");
        RosterEntry rosterEntry = (RosterEntry) rosterList.getItemAtPosition(i);
        if(rosterEntry!=null)
            Log.d("kkk",rosterEntry.getJid());
        else Log.d("kkk","null...:(");
        //ChatBox.openChat(rosterEntry.getJid());
        Intent  intent = new Intent(ChatApplication.getAppContext(),ChatBox.class);
        intent.putExtra("buddyid",rosterEntry.getJid());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
}
