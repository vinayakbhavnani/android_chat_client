package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.os.Bundle;


public class activitytest extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    XMLHelper xml = new XMLHelper();
        String xmlstring = xml.buildPacket(new Message("vinayak","heytest","sumit").getTag());

        xml.tearPacket(xmlstring);
        new testtask().execute();
        //new customConnection();

       // Log.d("msg123",xml.buildPacket(xml.tearPacket(xmlstring)));

  //      String xmlstring = xml.buildPacket(new Message("vinayak","heytest","sumit").getTag());
        Log.d("msg123",xml.buildPacket(xml.tearPacket(xmlstring)));
        setContentView(R.layout.main);
        //SocketReader.getInstance().getMessage();

         String initiate_conn="<stream:stream to=\"gmail.com\" version=\"1.0\" xmlns=\"jabber:client\" xmlns:stream=\"http://etherx.jabber.org/streams\">";
         String start_tls="<starttls xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>";
        new  Dum().execute(new Integer(1));
    }
}
class testtask extends AsyncTask {

    @Override
    protected Object doInBackground(Object... objects) {
        Log.d("execute","background");
        //new customConnection();
        new smackLogin().execute();
        return null;
    } */
}
}
