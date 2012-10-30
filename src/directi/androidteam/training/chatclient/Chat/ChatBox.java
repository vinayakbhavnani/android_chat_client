package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
import directi.androidteam.training.chatclient.Constants;
import directi.androidteam.training.chatclient.GlobalTabActivity;
import directi.androidteam.training.chatclient.Notification.TalkToNotifier;
import directi.androidteam.training.chatclient.R;


/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 3/9/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatBox extends FragmentActivity {
    private static Context context;
    private static Context applicContext;
    private static FragmentSwipeAdaptor frag_adaptor;
    private static ViewPager viewPager;
    private static Toast toast;
    private static FragmentManager fragmentManager;
    public static final String BUDDY_ID = "buddyid";
    public static final String ACCOUNT_UID = "accountUID";
    private static final String LOGTAG = "ChatBox";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOGTAG,"inside oncreate");
        super.onCreate(savedInstanceState);
        Log.d(LOGTAG, "called super");
        BugSenseHandler.initAndStartSession(this, Constants.BUGSENSE_API_KEY);
        Log.d(LOGTAG, "setting content view");
        setContentView(R.layout.chat);
        Log.d(LOGTAG, "set content view");
        applicContext=this.getApplicationContext();
        context = this;
        Log.d(LOGTAG,"got app context");
        fragmentManager = getSupportFragmentManager();
        Log.d(LOGTAG,"got fragment manager");
        frag_adaptor = new FragmentSwipeAdaptor(fragmentManager);
        Log.d(LOGTAG,"got frag adaptor");
        viewPager = (ViewPager)findViewById(R.id.pager);
        if(viewPager == null) {
        Log.d(LOGTAG,"viewPager is null")  ;
        }

        viewPager.setAdapter(frag_adaptor);
        Intent intent = getIntent();
        Log.d(LOGTAG,"got intent");
        String from = intent.getStringExtra(BUDDY_ID);
        /*
        Bundle extras = intent.getExtras();
        Log.d(LOGTAG,"got extras");
        String from = (String) extras.get(BUDDY_ID);
        */
        Log.d(LOGTAG,"can read from string = " + from);
        if(from != null) {
            String accountUID = intent.getStringExtra(ACCOUNT_UID);
            if(accountUID!=null)
                ChatStore.getInstance().addEntry(from,accountUID);

            Log.d(LOGTAG,"pass if");
            MyFragmentManager.getInstance().addFragEntry(from);
            Log.d(LOGTAG,"cancelling notification");
            cancelNotification(from);
            Log.d(LOGTAG,"cancelled notification");
        }
        viewPager.setOnPageChangeListener(new ChatViewPageChangeListner(context,fragmentManager));
        Log.d(LOGTAG,"switching fragment");
        switchFragment(from);
        Log.d(LOGTAG,"switched fragment");
        sendDiscoInfoQuery(from);
        Log.d(LOGTAG,"successfully exiting");
    }

/*
@Override
public boolean onCreateOptionsMenu(Menu menu) {
MenuInflater inflater = getMenuInflater();
inflater.inflate(R.menu.global_navigation_menu, menu);
return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem menuItem) {
switch (menuItem.getItemId()) {
case R.id.global_navigation_account:
Log.d(Constants.DEBUG_CHATBOX,"account clicked");
return true;
case R.id.global_navigation_chat:
Log.d(Constants.DEBUG_CHATBOX,"chat clicked");
return true;
case R.id.global_navigation_contact:
Log.d(Constants.DEBUG_CHATBOX,"contact clicked");
return true;
default:
return super.onOptionsItemSelected(menuItem);
}
}
*/


    private void sendDiscoInfoQuery(String from) {
        String queryAttr = "http://jabber.org/protocol/disco#info";
        RosterGet rosterGet = new RosterGet();
        rosterGet.setReceiver(from).setQueryAttribute("xmlns",queryAttr);
        rosterGet.send(from);
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

    public static Context getApplicContext() {
        return applicContext;
    }

    public static void notifyChat(final MessageStanza ms, final String from){
        TalkToNotifier ttn = TalkToNotifier.getInstance(applicContext) ;
        ttn.sendMessageNotification(from,ms.getBody(),ms.getTag().getRecipientAccount());
        if(viewPager.getCurrentItem()!= MyFragmentManager.getInstance().JidToFragId(ms.getFrom().split("/")[0])) {

        }
/*

dbAccess db = new dbAccess(); db.addMessage(ms);
MyFragmentManager.getInstance().addFragEntry(from);
MessageManager.getInstance().appendMessageStore(from,ms);
if(viewPager.getCurrentItem()== MyFragmentManager.getInstance().JidToFragId(ms.getFrom().split("@")[0])){
notifyFragmentAdaptorInSameThread();
}
*/
        if(context!=null)
            ((Activity)context).runOnUiThread(new Runnable() {
                public void run() {
                    MessageManager.getInstance().insertMessage(from, ms);
                }
            });

    }

    public static void cancelNotification(String from){
        TalkToNotifier ttn = TalkToNotifier.getInstance(applicContext) ;
        ttn.cancelMessageNotification(from);
    }

    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        if(intent.getExtras().containsKey("error")){
            notifyConnectionError();
            return;
        }
        if (intent.getExtras().containsKey("finish")){
            this.finish();
        }

        String from = (String)intent.getExtras().get("buddyid");
        cancelNotification(from);
        if(from!=null)
        {
            if((String) getIntent().getExtras().get("accountUID")!=null)
                ChatStore.getInstance().addEntry(from, (String) getIntent().getExtras().get("accountUID"));
            MyFragmentManager.getInstance().addFragEntry(from);
            switchFragment(from);
            sendDiscoInfoQuery(from);
        }
    }

    public void notifyConnectionError(){
        TextView textView = (TextView)findViewById(R.id.chatbox_networknotification);
        textView.setVisibility(0);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("adad","resumed");
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
        MessageStanza msgStanza = new MessageStanza(jid,message);
        msgStanza.formActiveMsg();
        msgStanza.send(jid);

        PacketStatusManager.getInstance().pushMsPacket(msgStanza);
        MyFragmentManager.getInstance().addFragEntry(jid);
        MessageManager.getInstance().insertMessage(jid, msgStanza);

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

    public static void setContext(Context myContext ) {
        context = myContext;
    }

    private void GotoRoster(){
        Intent intent = new Intent(ChatApplication.getAppContext(), GlobalTabActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
/*
Object[] objects = MessageManager.getInstance().getMessageStore().keySet().toArray();
for (Object object : objects) {
MessageStanza messageStanza = new MessageStanza((String) object);
messageStanza.formInActiveMsg();
//messageStanza.send();
}
*/
/*
String[] jids = ChatStore.getInstance().getAllAcctUID();
for (String jid : jids) {
MessageStanza messageStanza = new MessageStanza(jid);
messageStanza.formInActiveMsg();
messageStanza.send(jid);
}
*/

    }
    private void switchFragment(String from){
        int frag = MyFragmentManager.getInstance().JidToFragId(from);
        viewPager.setCurrentItem(frag);
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

    public static void removeFragmentviaFragManager(String jid) {
        frag_adaptor.setPosition(true);
        frag_adaptor = new FragmentSwipeAdaptor(fragmentManager);
        viewPager.setAdapter(frag_adaptor);

    }

}



