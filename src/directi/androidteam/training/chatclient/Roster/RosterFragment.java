package directi.androidteam.training.chatclient.Roster;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import directi.androidteam.training.chatclient.R;

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
        (new RequestRoster(getActivity())).execute();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ioio", "rosterfragment oncreate view- acct fragment");
        View view = inflater.inflate(R.layout.accounts,container,false);

        TextView textView = new TextView(getActivity());
        textView.setText("SimpleFragmentText");
        textView.setVisibility(View.VISIBLE);
        textView.setTextSize(20);
        return textView;
    }
}
