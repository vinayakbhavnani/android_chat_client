package directi.androidteam.training.chatclient.Chat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bugsense.trace.BugSenseHandler;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.StanzaStore.MessageStanza;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.Chat.Listeners.ChatViewPageChangeListner;
import directi.androidteam.training.chatclient.Chat.Listeners.MsgTextChangeListener;
import directi.androidteam.training.chatclient.Constants;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;


/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatBox extends FragmentActivity {
    private static Context context;
    private static FragmentSwipeAdaptor frag_adaptor;
    private static ViewPager viewPager;
    private static Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate for ChatBox" , "entered");
        super.onCreate(savedInstanceState);
        Log.d("onCreate for ChatBox","super called succcessfullY");
        BugSenseHandler.initAndStartSession(this, Constants.BUGSENSE_API_KEY);

        setContentView(R.layout.chat);
        Log.d("onCreate for ChatBox" , "content view is set");
        context=this;
        frag_adaptor = new FragmentSwipeAdaptor(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(frag_adaptor);
        viewPager.setOnPageChangeListener(new ChatViewPageChangeListner(context));
        Log.d("onCreate for ChatBox","getIntent() will be called");
        Intent intent = getIntent();
        Log.d("onCreate for ChatBox","getExtras() will be called");
        Bundle bundle = intent.getExtras();
        Log.d("onCreate for ChatBox","will try to get from from bundle");
        if(bundle == null) {
            Log.d("onCreate for ChatBox","bundle is null");
        }
        String from =  (String) bundle.get("buddyid");
        Log.d("onCreate for ChatBox" , "from string extracted from intent = " + from);
        if(bundle.containsKey("notification"))  {
            int notificationID = (Integer) bundle.get("notificationID");
            Log.d("onCreate for ChatBox" , "cancel notification will be called");
            cancelNotification(notificationID);
        }
        if(from != null) {
            Log.d("onCreate for ChatBox","from is not null");
            MyFragmentManager.getInstance().addFragEntry(from);
            EditText editText = (EditText) findViewById(R.id.enter_message);
            editText.addTextChangedListener(new MsgTextChangeListener(from));
            switchFragment(from);
            sendDiscoInfoQuery(from);
            Log.d("onCreate for ChatBox","from != null if block finished");
        }
        ActionBar ab = getActionBar();
        Log.d("onCreate for ChatBox" , "action bar obtained");
        ab.hide();
        Log.d("DDDD","oncreate done");
    }

    private void sendDiscoInfoQuery(String from) {
        String queryAttr = "http://jabber.org/protocol/disco#info";
        RosterGet rosterGet = new RosterGet();
        rosterGet.setReceiver(from).setQueryAttribute("xmlns",queryAttr);
        rosterGet.send();
    }

    public void updateHeader(int i){
        TextView hleft = (TextView)findViewById(R.id.chatboxheader_left);
        TextView hright = (TextView)findViewById(R.id.chatboxheader_right);

        String left = MyFragmentManager.getInstance().getJidByFragId(i - 1);
        String right = MyFragmentManager.getInstance().getJidByFragId(i + 1);
        if(left!=null)
            hleft.setText(left.split("@")[0]);
        else
            hleft.setText("");
        if(right!=null)
            hright.setText(right.split("@")[0]);
        else
            hright.setText("");
    }

    public static void adaptorNotify(final ChatFragment cfrag){
        Activity a = (Activity) context;
        if(context!=null && cfrag!=null)
        a.runOnUiThread(new Runnable() {
            public void run() {
                cfrag.notifyAdaptor();
            }
        }
        );
    }

    public static void notifyChat(MessageStanza ms, String from){
        if(viewPager.getCurrentItem()!= MyFragmentManager.getInstance().JidToFragId(ms.getFrom())) {
            ChatNotifier cn = new ChatNotifier(context);
            cn.notifyChat(ms);
            Log.d("xcxc","notficatn done when chatcontext is not null");
        }
        MyFragmentManager.getInstance().addFragEntry(from);
        Log.d("xcxc","before insert msg");
        MessageManager.getInstance().insertMessage(from,ms);
    }

    public static void cancelNotification(int notificationID){
        ChatNotifier cn = new ChatNotifier(context);
        cn.cancelNotification(notificationID);
    }

    @Override
    public void onNewIntent(Intent intent){
        Log.d("onNewIntent" , " got inside");
        super.onNewIntent(intent);
        Log.d("onNewIntent","super method successfully executed");
        if(intent.getExtras().containsKey("error")){
            Log.d("onNewIntent","intent contains error key");
            notifyConnectionError();
            return;
        }
        if (intent.getExtras().containsKey("finish")){
            Log.d("onNewIntent" ,"intent contains finish key");
            this.finish();
        }
        Bundle bundle =  intent.getExtras();
        String from = (String)bundle.get("buddyid");
        Log.d("onNewIntent","from string obtained from extras : " + from)  ;
        if(intent.getExtras().containsKey("notification")) {
            Log.d("onNewIntent","intent contains notification key " );
            int notificationID = (Integer) bundle.get("notificationID");
            cancelNotification(notificationID);
            Log.d("onNewIntent" , "finished executing cancel notification");
        }
        if(from!=null)
        {
            Log.d("onNewIntent" , "from is not null");
            EditText editText = (EditText) findViewById(R.id.enter_message);
            editText.addTextChangedListener(new MsgTextChangeListener(from));
            MyFragmentManager.getInstance().addFragEntry(from);
            switchFragment(from);
            sendDiscoInfoQuery(from);
            Log.d("onNewIntent","finished executing within from != null if block ");
        }
    }

    public void notifyConnectionError(){
        TextView textView = (TextView)findViewById(R.id.chatbox_networknotification);
        textView.setVisibility(0);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("Chatboxresumed","true");
    }

    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.sendmessage_button) {
             sendChat();
        }
        else if(id==R.id.chatlistitem_status) {
            resendMessage();
        }
        else if(id == R.id.chatboxheader_roster) {
            GotoRoster();
        }
    }

    private void sendChat(){
        EditText mess = (EditText) findViewById(R.id.enter_message);
        String message = mess.getText().toString();
        if(message==null || message.equals(""))
            return;
        int currentItem = viewPager.getCurrentItem();

        String jid = MyFragmentManager.getInstance().getJidByFragId(currentItem);
        MessageStanza messxml = new MessageStanza(jid,message);
        messxml.formActiveMsg();
        messxml.send();

        PacketStatusManager.getInstance().pushMsPacket(messxml);
        MyFragmentManager.getInstance().addFragEntry(jid);
        MessageManager.getInstance().insertMessage(jid, messxml);

        viewPager.setCurrentItem(currentItem);

        mess.setText("");
    }

    private void resendMessage(){
        TextView tv = (TextView)findViewById(R.id.chatlistitem_status);
        tv.setVisibility(0);
    }

    public static Context getContext() {
        return context;
    }

    private void GotoRoster(){
        Intent intent = new Intent(ChatApplication.getAppContext(), DisplayRosterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        Object[] objects = MessageManager.getInstance().getMessageStore().keySet().toArray();
        for (Object object : objects) {
            MessageStanza messageStanza = new MessageStanza((String) object);
            messageStanza.formInActiveMsg();
            messageStanza.send();
        }
    }
    private void switchFragment(String from){
        int frag = MyFragmentManager.getInstance().JidToFragId(from);
        updateHeader(frag);
        viewPager.setCurrentItem(frag);
    }

    public static void notifyFragmentAdaptorInNewUIThread() {
        if(ChatBox.getContext()==null)
            return;
        Activity a = (Activity) context;
        if(context!=null)
        a.runOnUiThread(new Runnable() { public void run() {
            frag_adaptor.notifyDataSetChanged();
        }}
        );
    }

    public static void notifyFragmentAdaptorInSameThread() {
        frag_adaptor.notifyDataSetChanged();
    }

    public static void finishActivity(){
        Intent intent = new Intent(context,ChatBox.class);
        intent.putExtra("finish",true);
        context.startActivity(intent);
    }

    public static void composeToast(final String s) {
        Activity application = (Activity) context;
        if(context!=null)
        application.runOnUiThread(new Runnable() {
            public void run() {
                if(toast!=null)
                    toast.cancel();
                toast = Toast.makeText(ChatApplication.getAppContext(), s, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        );
    }
}
