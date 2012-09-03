package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import directi.androidteam.training.TagStore.MessageStanza;

import directi.androidteam.training.lib.TCPHandler.customConnection;
import directi.androidteam.training.lib.TCPHandler.smackLogin;

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

        xml.tearPacket(xmlstring);
        new testtask().execute();
        //new customConnection();

        Log.d("msg123",xml.buildPacket(xml.tearPacket(xmlstring)));

        setContentView(R.layout.main);

    }
}
class testtask extends AsyncTask {

    @Override
    protected Object doInBackground(Object... objects) {
        Log.d("execute","background");
        //new customConnection();
        new smackLogin().execute();
        return null;
    }
}
