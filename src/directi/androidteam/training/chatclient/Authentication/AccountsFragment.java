package directi.androidteam.training.chatclient.Authentication;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/18/12
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountsFragment extends Fragment implements Subscriber{
    private static ArrayList<String> loginList;
    private static ArrayList<String> logoutList;
    private static AccountListAdaptor adaptor;

    public AccountsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("ioio", "oncreate - acct fragment");
        AccountManager.getInstance().addSubscribers(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("ioio", "onacitvity created- acct fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ioio", "oncreate view- acct fragment");
        View view = inflater.inflate(R.layout.accounts,container,false);

        ListView lv = (ListView)view.findViewById(R.id.accountScreen_list);
        adaptor = new AccountListAdaptor(getActivity());
        lv.setAdapter(adaptor);

        Button accountAddButton = (Button) view.findViewById(R.id.accountScreen_add);
        accountAddButton.setOnClickListener(new AcctScreenAddOnClickListener(getActivity()));
        setLoginList();
        setLogoutList();
        Log.d("ioio","pppp");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ioio", "onpause acct frag");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ioio","onstop acct frag");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ioio","ondestroy view acct frag");
    }

    public void setLoginList(){
        loginList = new ArrayList<String>();
        loginList.add("Login");
        loginList.add("Edit Password");
        loginList.add("Remove Account");
        loginList.add("Remove Account");
        loginList.add("Set Status");
    }
    public void setLogoutList(){
        logoutList = new ArrayList<String>();
        logoutList.add("Logout");
        logoutList.add("Edit Password");
        logoutList.add("Remove Account");
        logoutList.add("Remove Account");
        logoutList.add("Set Status");
    }

    public static ArrayList<String> getLoginList() {
        return loginList;
    }

    public static ArrayList<String> getLogoutList() {
        return logoutList;
    }

    public static AccountListAdaptor getAdaptor() {
        return adaptor;
    }

    @Override
    public void onResume(){
        super.onResume();
        adaptor.notifyDataSetChanged();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("ioio","ondetroy acct frag");
        AccountManager.getInstance().saveAccountState();
        AccountManager.getInstance().removeSubscribers(this);
    }

    @Override
    public void receivedNotification(PublicationType publicationType, String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adaptor.notifyDataSetChanged();
            }
        });
    }


}
