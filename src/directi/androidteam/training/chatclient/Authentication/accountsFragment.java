package directi.androidteam.training.chatclient.Authentication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/18/12
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class accountsFragment extends Fragment implements Subscriber{
    private ArrayList<String> loginList;
    private ArrayList<String> logoutList;
    private AccountListAdaptor adaptor;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        AccountManager.getInstance().addSubscribers(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("SimpleFragmentText");
        textView.setVisibility(View.VISIBLE);
        adaptor = new AccountListAdaptor(getActivity());
        return textView;
/*
            View view;
//        if(container==null) {
            view = inflater.inflate(R.layout.accounts,container,false);
            ListView lv = (ListView)view.findViewById(R.id.accountScreen_list);
            adaptor = new AccountListAdaptor(getActivity());
            lv.setAdapter(adaptor);
        //          ImageView settings_icon_click = (ImageView) view.findViewById(R.id.accountitem_settings);
//            settings_icon_click.setOnClickListener(new AcctSettingsIconOnClickListener(loginList, logoutList, adaptor, getActivity()));
            Button accountAddButton = (Button) view.findViewById(R.id.accountScreen_add);
            accountAddButton.setOnClickListener(new AcctScreenAddOnClickListener(getActivity()));
            setLoginList();
            setLogoutList();
            Log.d("ioio","pppp");
*/
/*
        }
        else
            view = container;
*//*

        return view;
*/
    }

    public void setLoginList(){
        loginList = new ArrayList<String>();
        loginList.add("Login");
        loginList.add("Edit Password");
        loginList.add("Remove Account");
    }
    public void setLogoutList(){
        logoutList = new ArrayList<String>();
        logoutList.add("Logout");
        logoutList.add("Edit Password");
        logoutList.add("Remove Account");
    }

    @Override
    public void onResume(){
        super.onResume();
        adaptor.notifyDataSetChanged();
    }


    @Override
    public void receivedNotification(Publisher s) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adaptor.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        AccountManager.getInstance().saveAccountState();
        AccountManager.getInstance().removeSubscribers(this);
    }

}
