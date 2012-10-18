package directi.androidteam.training.chatclient.Roster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/3/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayRosterActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        ((ListView)findViewById(R.id.roster_list)).setAdapter(new RosterItemAdapter(this, R.layout.rosterlistitem, new ArrayList<RosterItem>()));
        ((ListView)findViewById(R.id.roster_list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                onListItemClick((ListView) adapterView, view, position, id);
            }
        });
        (new RequestRoster(this)).execute();
    }

    public void onListItemClick(ListView view, View v, int position, long id) {
        RosterItem rosterItem = (RosterItem) view.getItemAtPosition(position);
        Intent intent = new Intent(ChatApplication.getAppContext(), ChatBox.class);
        intent.putExtra("buddyid", rosterItem.getBareJID());

        intent.putExtra("accountUID",rosterItem.getAccount());

        //TODO : there will be an account field in roster item , use that to put extra values about the account in the intent as needed

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rostermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add_contact:
                //TODO : add feature to ask for which account to add the contact to
                (new AddContactDialog()).show(getSupportFragmentManager(), "add_contact_dialog_box_tag");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void updateRosterList(ArrayList<RosterItem> rosterList) {
        RosterItemAdapter rosterItemAdapter = ((RosterItemAdapter)(((ListView)findViewById(R.id.roster_list)).getAdapter()));
        rosterItemAdapter.setRosterItems(rosterList);
        rosterItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}


