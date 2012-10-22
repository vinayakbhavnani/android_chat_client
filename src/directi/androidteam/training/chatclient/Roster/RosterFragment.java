package directi.androidteam.training.chatclient.Roster;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ioio", "rosterfragment oncreate view- acct fragment");
/*
        if(container!=null)
           container.removeAllViews();
*/
        TextView textView = new TextView(getActivity());
        textView.setText("SimpleFragmentText");
        textView.setVisibility(View.VISIBLE);
        textView.setTextSize(20);
        return textView;
    }
}
