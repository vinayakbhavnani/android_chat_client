package directi.androidteam.training.chatclient.Chat;

import android.R;
import android.app.ListFragment;
import android.content.Context;
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
    private ArrayList<String> sconvo;
    private ArrayList<ChatListItem> convo;
    private ChatListAdaptor adaptor;
    private String buddyid="talk.to";

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getArguments()!=null){
            buddyid = (String)getArguments().get("from");
        //buddyid = "vinayak.bhavnani@gmail.com";
            //buddyid = "vinayak.bhavnani@gmail.com";
            sconvo = MessageHandler.getInstance().getFragList(buddyid);
            convo = toChatListItemList(sconvo);

        }
        else
            convo = new ArrayList<ChatListItem>();
        ChatListItem[] random=new ChatListItem[1];
        random[0] = new ChatListItem("aa","hey",true);


        adaptor = new ChatListAdaptor(getActivity(),convo);
        setListAdapter(adaptor);
        //buddyid = (String)getArguments().get("id");

    }

    public void insertMessage(String message){
        sconvo.add(message);
        notifyAdaptor();
    }

    public void notifyAdaptor(){
        convo.add(new ChatListItem(buddyid,sconvo.get(sconvo.size()-1),true));
        adaptor.notifyDataSetChanged();
    }

    public String getBuddyid(){
        return buddyid;
    }

    private ArrayList<ChatListItem> toChatListItemList(ArrayList<String> list){
        ArrayList<ChatListItem> conv;
        conv = new ArrayList<ChatListItem>();
        for (String s : list) {
            ChatListItem cli = new ChatListItem(buddyid,s,true);
            conv.add(cli);
        }
        return  conv;
    }

}
