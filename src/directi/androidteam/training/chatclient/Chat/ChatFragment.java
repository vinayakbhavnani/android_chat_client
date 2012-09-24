package directi.androidteam.training.chatclient.Chat;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.chatclient.PacketHandlers.MessageHandler;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.RosterEntry;
import directi.androidteam.training.chatclient.Roster.RosterManager;

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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("fragmentcreated","new fragement");
        if(getArguments()!=null){
            buddyid = (String)getArguments().get("from");
        //buddyid = "vinayak.bhavnani@gmail.com";
            //buddyid = "vinayak.bhavnani@gmail.com";
            Log.d("buddyid",buddyid);
            sconvo = MessageHandler.getInstance().getFragList(buddyid);
            convo = toChatListItemList(sconvo);

        }
        else
            convo = new ArrayList<ChatListItem>();




        //


        //buddyid = (String)getArguments().get("id");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        adaptor = new ChatListAdaptor(getActivity(),convo);

        //getListView().setBackgroundColor(0x000000);
        ListView lv = getListView();
        LayoutInflater linf = getLayoutInflater(savedInstanceState);
        ViewGroup header = (ViewGroup)linf.inflate(R.layout.chatlistheader,lv,false);
        TextView tv = (TextView)(header.findViewById(R.id.chatfragment_jid));
        tv.setText(buddyid);
        TextView status = (TextView)(header.findViewById(R.id.chatfragment_status));
        RosterEntry re = RosterManager.getInstance().searchRosterEntry(buddyid);
        if(re!=null){
            status.setText(re.getStatus());
            Log.d("statusmess",re.getStatus());
        }
        else status.setText("null");
        lv.addHeaderView(header,null,false);
        setListAdapter(adaptor);
    }

    public static ChatFragment getInstance(String from){
        ChatFragment curfrag = new ChatFragment();
        MessageHandler.getInstance().getChatLists().get(from).registerFragment(curfrag);
        Bundle args = new Bundle();
        args.putString("from",from);
        Log.d("XXXX", "from is " + from);
        curfrag.setArguments(args);
        return curfrag;
    }

    public void insertMessage(MessageStanza message){
        Log.d("XXX","chat fragment for: "+buddyid);
        sconvo.add(message);
        //notifyAdaptor();
    }
    public void addChatItem(MessageStanza message){
        convo.add(new ChatListItem(message.getFrom(),message.getBody(),isSender(message)));

        //adaptor.notifyDataSetChanged();
        ChatBox.adaptorNotify(adaptor);
        Log.d("chatlistitemsize",message.getBody());
    }
    public boolean isSender(MessageStanza message){
        return message.getFrom().equals(JID.jid.split("/")[0]);
    }

    public void notifyAdaptor(){


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

    @Override
    public void onResume(){
        super.onResume();
        notifyAdaptor();
        Log.d("fragmentresume",buddyid);

    }

    @Override
    public void onPause(){
        super.onPause();
        //notifyAdaptor();
        Log.d("fragmentpause",buddyid);

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
