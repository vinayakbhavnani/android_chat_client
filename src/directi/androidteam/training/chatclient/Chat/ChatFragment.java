package directi.androidteam.training.chatclient.Chat;

import android.R;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.MessageStanza;
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
    private ArrayList<MessageStanza> sconvo;
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
        getListView().setBackgroundColor(0xFF00FF00);

        //buddyid = (String)getArguments().get("id");

    }

    public void insertMessage(MessageStanza message){
        sconvo.add(message);
        notifyAdaptor();
    }

    public boolean isSender(MessageStanza message){
        return message.getFrom().equals(JID.jid.split("/")[0]);
    }

    public void notifyAdaptor(){
        MessageStanza message = sconvo.get(sconvo.size()-1);
        convo.add(new ChatListItem(message.getFrom(),message.getBody(),isSender(message)));
        adaptor.notifyDataSetChanged();
        ListView lv = getListView();
        lv.setFocusable(true);

        if(lv.getChildCount()!=0){
            lv.getChildAt(lv.getChildCount()-1).setFocusable(true);
            lv.setSelection(lv.getChildCount()-1);
        }
    }

    public String getBuddyid(){
        return buddyid;
    }

    private ArrayList<ChatListItem> toChatListItemList(ArrayList<MessageStanza> list){
        ArrayList<ChatListItem> conv;
        conv = new ArrayList<ChatListItem>();
        for (MessageStanza s : list) {
            ChatListItem cli = new ChatListItem(s.getFrom(),s.getBody(),isSender(s));
            conv.add(cli);
        }
        return  conv;
    }

}
