package directi.androidteam.training.chatclient.Roster;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.GlobalTabActivity;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/19/12
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterFragment extends Fragment {
    public RosterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("ioio", "oncreate - roster fragment");
        RosterManager.getInstance().setDisplayRosterActivity((GlobalTabActivity)getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ioio", "rosterfragment oncreate view- acct fragment");
        View view = inflater.inflate(R.layout.roster,container,false);
        ListView listView = ((ListView)view.findViewById(R.id.roster_list));
        if(listView!=null) {
            listView.setAdapter(new RosterItemAdapter(getActivity(), R.layout.rosterlistitem, new ArrayList<RosterItem>()));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    onListItemClick((ListView) adapterView, view, position, id);
                }
            });
        }
        return view;

/*

        TextView textView = new TextView(getActivity());
        textView.setText("SimpleFragmentText");
        textView.setVisibility(View.VISIBLE);
        textView.setTextSize(20);
        return textView;
*/
    }


    public void onListItemClick(ListView view, View v, int position, long id) {
        RosterItem rosterItem = (RosterItem) view.getItemAtPosition(position);
        Intent intent = new Intent(ChatApplication.getAppContext(), ChatBox.class);
        intent.putExtra("buddyid", rosterItem.getBareJID());
        intent.putExtra("accountUID", rosterItem.getAccount());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
