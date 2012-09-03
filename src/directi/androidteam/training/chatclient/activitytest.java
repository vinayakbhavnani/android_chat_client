package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import directi.androidteam.training.Stanza.Message;
import directi.androidteam.training.lib.TCPHandler.Dum;
import directi.androidteam.training.lib.xml.XMLHelper;


public class activitytest extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XMLHelper xml = new XMLHelper();
        String xmlstring = xml.buildPacket(new Message("vinayak","heytest","sumit").getTag());
        Log.d("msg123",xml.buildPacket(xml.tearPacket(xmlstring)));
        setContentView(R.layout.main);
        //SocketReader.getInstance().getMessage();

         String initiate_conn="<stream:stream to=\"gmail.com\" version=\"1.0\" xmlns=\"jabber:client\" xmlns:stream=\"http://etherx.jabber.org/streams\">";
         String start_tls="<starttls xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>";
        new  Dum().execute(new Integer(1));
    }
}
