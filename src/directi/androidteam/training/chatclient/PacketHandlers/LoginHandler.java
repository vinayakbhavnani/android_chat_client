package directi.androidteam.training.chatclient.PacketHandlers;

import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.TagStore.*;
import directi.androidteam.training.chatclient.Authentication.LoginActivity;
import directi.androidteam.training.chatclient.Authentication.LoginErrorActivity;
import directi.androidteam.training.chatclient.Authentication.User;
import directi.androidteam.training.chatclient.Authentication.UserDatabaseHandler;
import directi.androidteam.training.chatclient.Roster.DisplayRosterActivity;
import directi.androidteam.training.chatclient.Util.Base64;
import directi.androidteam.training.chatclient.Util.PacketWriter;
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

    private void extractJID(Tag iqTag) {
        JIDTag jidTag  = new JIDTag(iqTag.getChildTags().get(0).getChildTags().get(0));
        Log.d("JID intialize", jidTag.getContent());
    }

    public void processPacket(Tag tag) {
        if (tag.getTagname().equals("stream:stream")) {
            if (containsGrandChild(tag, "bind")) {
                Log.d("Login Flow", "Stream tag with bind tag received.");
                PacketWriter.addToWriteQueue((new XMLHelper()).buildPacket(new IQTag("tn281v37", "set", new BindTag("urn:ietf:params:xml:ns:xmpp-bind"))));
            } else if (containsGrandChild(tag, "mechanisms")) {
                String auth = '\0' + LoginActivity.uname + '\0' + LoginActivity.pwd;
                Log.d("Login Flow", "Stream tag with mechanisms tag received.");
                PacketWriter.addToWriteQueue((new XMLHelper()).buildPacket(new AuthTag("urn:ietf:params:xml:ns:xmpp-sasl", "PLAIN", Base64.encodeBytes(auth.getBytes()))));
            }
        } else if (tag.getTagname().equals("success")) {
            Log.d("Login Flow", "Success tag received.");
            PacketWriter.addToWriteQueue("<stream:stream" +
                    " to='gmail.com'" +
                    " xmlns='jabber:client'" +
                    " xmlns:stream='http://etherx.jabber.org/streams'" +
                    " version='1.0'>");
        } else if (tag.getTagname().equals("failure")) {
            Log.d("Login Flow", "Failure tag received.");
            Intent intent = new Intent(ChatApplication.getAppContext(), LoginErrorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ChatApplication.getAppContext().startActivity(intent);
        } else if (tag.getTagname().equals("iq")) {
            Log.d("Login Flow", "Iq tag with a child bind tag received.");
            extractJID(tag);
            PacketWriter.addToWriteQueue((new XMLHelper()).buildPacket(new IQTag("sess_1", "talk.google.com", "set", new SessionTag("urn:ietf:params:xml:ns:xmpp-session"))));
            UserDatabaseHandler db = new UserDatabaseHandler(ChatApplication.getAppContext());
            db.addUser(new User(LoginActivity.uname, LoginActivity.pwd));
            db.close();

            Intent intent = new Intent(ChatApplication.getAppContext(), DisplayRosterActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(LoginActivity.USERNAME, LoginActivity.uname);
            intent.putExtra("buddyid","vinayak.bhavnani@gmail.com");
            ChatApplication.getAppContext().startActivity(intent);
        }
    }

    public static LoginHandler getInstance() {
        return loginHandler;
    }
}
