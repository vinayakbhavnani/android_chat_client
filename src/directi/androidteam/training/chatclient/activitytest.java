package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import directi.androidteam.training.TagStore.MessageStanza;
import directi.androidteam.training.lib.xml.XMLHelper;


public class activitytest extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XMLHelper xml = new XMLHelper();
        String xmlstring = xml.buildPacket(new MessageStanza("vinayak","heytest","sumit").getTag());
        Log.d("msg123",xml.buildPacket(xml.tearPacket(xmlstring)));
        setContentView(R.layout.main);

    }
}
