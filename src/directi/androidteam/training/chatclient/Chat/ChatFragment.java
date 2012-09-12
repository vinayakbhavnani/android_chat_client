package directi.androidteam.training.chatclient.Chat;

import android.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import directi.androidteam.training.chatclient.PacketHandlers.MessageHandler;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 11/9/12
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatFragment extends ListFragment {
    private ArrayList<String> convo;
    private ArrayAdapter<String> adaptor;
    private String buddyid;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //String from = (String)getArguments().get("from");
        String from = "vinayak.bhavnani@gmail.com";
        convo = MessageHandler.getInstance().getFragList(from);
        adaptor = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1,convo);
        setListAdapter(adaptor);
        //buddyid = (String)getArguments().get("id");

    }

    public void insertMessage(String message){
        convo.add(message);
        adaptor.notifyDataSetChanged();
    }

    public String getBuddyid(){
        return buddyid;
    }

}
