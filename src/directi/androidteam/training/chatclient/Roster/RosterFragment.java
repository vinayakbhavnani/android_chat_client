package directi.androidteam.training.chatclient.Roster;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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

public class RosterFragment extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        RosterManager.getInstance().setGlobalTabActivity((GlobalTabActivity)getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.roster, container, false);
        ListView listView = ((ListView)view.findViewById(R.id.roster_list));
        if(listView != null) {
            listView.setAdapter(new RosterItemAdapter(getActivity(), R.layout.rosterlistitem, new ArrayList<RosterItem>()));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    onListItemClick((ListView) adapterView, view, position, id);
                }
            });
            updateRosterList(RosterManager.getInstance().getRoster());
        }
        return view;
    }

    private void updateRosterList(ArrayList<RosterItem> rosterList) {
        if(view == null) {return;}
        RosterItemAdapter rosterItemAdapter = ((RosterItemAdapter)(((ListView)view.findViewById(R.id.roster_list)).getAdapter()));
        rosterItemAdapter.setRosterItems(new ArrayList<RosterItem>(rosterList));
        rosterItemAdapter.notifyDataSetChanged();
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
