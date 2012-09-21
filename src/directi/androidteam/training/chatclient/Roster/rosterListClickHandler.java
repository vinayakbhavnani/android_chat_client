package directi.androidteam.training.chatclient.Roster;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/21/12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class rosterListClickHandler implements  AdapterView.OnItemClickListener {
    ListView rosterList;
    public rosterListClickHandler(ListView rosterList) {
        this.rosterList = rosterList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("kkk","listener working");
        RosterEntry rosterEntry = (RosterEntry) rosterList.getItemAtPosition(i);
        if(rosterEntry!=null)
            Log.d("kkk",rosterEntry.getJid());
        else Log.d("kkk","null...:(");
    }
}
