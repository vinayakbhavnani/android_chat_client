package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.eventHandlers.TextChangeListenerForSearch;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/26/12
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchRosterEntry extends Activity {
    private static Context context;
    private static RosterItemAdapter adapter;
    public SearchRosterEntry() {
        context = this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rostersearch);
        EditText editText = (EditText) findViewById(R.id.searchrosterentryedittext);
        editText.addTextChangedListener(new TextChangeListenerForSearch());
        ListView listView = (ListView) findViewById(R.id.rostersearchlist);
        adapter = new RosterItemAdapter(context);
        RosterManager rosterManager = RosterManager.getInstance();
        ArrayList<RosterEntry> rosterEntries = rosterManager.searchRosterEntries("");
        updateRosterList(rosterEntries);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
    }
    public static void updateRosterList(final ArrayList<RosterEntry> rosterList) {
        Activity a = (Activity) context;
        a.runOnUiThread(new Runnable() {
            public void run() {
                adapter.setRosterEntries(rosterList);
                adapter.notifyDataSetChanged();
            }
        }
        );
    }

}
