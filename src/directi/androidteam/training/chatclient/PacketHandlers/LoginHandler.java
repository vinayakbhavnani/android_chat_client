package directi.androidteam.training.chatclient.PacketHandlers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.TagStore.JIDTag;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Authentication.*;
import directi.androidteam.training.chatclient.GlobalTabActivity;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.lib.xml.XMLHelper;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/18/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginHandler implements Handler {
    private static final LoginHandler loginHandler = new LoginHandler();

    private boolean containsGrandChild(Tag tag, String grandChildTagName) {
        if (tag.getChildTags().get(0).getChildTags().get(0).getTagname().equals(grandChildTagName)) {
            return true;
        } else {
            return false;
        }
    }

    private String extractJID(Tag iqTag) {
        JIDTag jidTag  = new JIDTag(iqTag.getChildTags().get(0).getChildTags().get(0));
        Log.d("JID intialize", jidTag.getContent());
        return jidTag.getContent().split("/")[0];
    }

    private boolean contains(Tag parent, String childTagName) {
        if (parent.getChildTags() != null) {
            for (int i = 0; i < parent.getChildTags().size(); i++) {
                if (parent.getChildTags().get(i).getTagname().equals(childTagName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void processPacket(Tag tag) {
        Log.d("packetrec",tag.toXml());
        if(tag.getTagname().equals("message")){
        } else if (tag.getTagname().equals("stream:stream") || tag.getTagname().equals("success") || tag.getTagname().equals("failure")) {
            processPacketAux(tag);
        } else if (tag.getTagname().equals("iq") && contains(tag, "bind")) {
            processPacketAux(tag);
        } else {
        }
    }

    public void processPacketAux(Tag tag) {
        Log.d("loginflow",new XMLHelper().buildPacket(tag));
        if (tag.getTagname().equals("stream:stream")) {
            if (containsGrandChild(tag, "bind")) {
                Log.d("Login Flow", "Stream tag with bind tag received.");
                //PacketWriter.addToWriteQueue((new IQTag("tn281v37", "set", new BindTag("urn:ietf:params:xml:ns:xmpp-bind"))));
                AccountManager.getInstance().getAccount(tag.getRecipientAccount()).getXmppLogin().sendIQwithBind();
            } else if (containsGrandChild(tag, "mechanisms")) {
                String auth = '\0' + ConnectGTalk.username + '\0' + ConnectGTalk.password;
                Log.d("Login Flow", "Stream tag with mechanisms tag received.");
                //PacketWriter.addToWriteQueue((new AuthTag("urn:ietf:params:xml:ns:xmpp-sasl", "PLAIN", Base64.encodeBytes(auth.getBytes()))));
                AccountManager.getInstance().getAccount(tag.getRecipientAccount()).getXmppLogin().sendAuthPacket();
            }
        } else if (tag.getTagname().equals("success")) {
            Log.d("Login Flow", "Success tag received.");

            //PacketWriter.addToWriteQueue(new StreamTag("stream:stream","gmail.com","jabber:client","http://etherx.jabber.org/streams","1.0"));
            AccountManager.getInstance().getAccount(tag.getRecipientAccount()).getXmppLogin().restartStream();
        } else if (tag.getTagname().equals("failure")) {
            Log.d("Login Flow", "Failure tag received."+tag.getContent());
//            Intent intent = new Intent(ChatApplication.getAppContext(), LoginErrorActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            ChatApplication.getAppContext().startActivity(intent);
//            if (ConnectGTalk.callerActivity != null) {
//                ConnectGTalk.callerActivity.setResult(Activity.RESULT_OK);
//                ConnectGTalk.callerActivity.finish();
//            }
            if (contains(tag,"temporary-auth-failure")){
                Log.d("resendauth","token");
                AccountManager.getInstance().getAccount(tag.getRecipientAccount()).getXmppLogin().sendAuthPacket();
                return;
            }
            ConnectGTalk.callerActivity.runOnUiThread(new Runnable() {
                public void run() {
                    ConnectGTalk.callerActivity.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                    (Toast.makeText(ConnectGTalk.callerActivity, "Wrong username or password. Please try again.", Toast.LENGTH_LONG)).show();
                }
            });
        } else if (tag.getTagname().equals("iq")) {
            Log.d("Login Flow", "Iq tag with a child bind tag received.");
            String bareJID = extractJID(tag);
            //PacketWriter.addToWriteQueue(new IQTag("sess_1", "talk.google.com", "set", new SessionTag("urn:ietf:params:xml:ns:xmpp-session")));
            AccountManager.getInstance().getAccount(tag.getRecipientAccount()).getXmppLogin().sendStartSession();
            /*MessageStanza ms = new MessageStanza("dummy.android.chat@gmail.com","testerauth");
            ms.getTag().setRecipientAccount("vinayak.bhavnani@gmail.com");
            PacketWriter.addToWriteQueue(ms.getTag());*/
            UserDatabaseHandler db = new UserDatabaseHandler(ChatApplication.getAppContext());
            db.addUser(new User(ConnectGTalk.username, ConnectGTalk.password));

            Intent intent = new Intent(ChatApplication.getAppContext(), GlobalTabActivity.class);//DisplayRosterActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(LoginActivity.USERNAME, ConnectGTalk.username);
            intent.putExtra("bareJID", bareJID);
           ChatApplication.getAppContext().startActivity(intent);
            if (ConnectGTalk.callerActivity != null) {
                ConnectGTalk.callerActivity.setResult(Activity.RESULT_OK);
                ConnectGTalk.callerActivity.finish();
            }
        }
    }

    public static LoginHandler getInstance() {
        return loginHandler;
    }
}
