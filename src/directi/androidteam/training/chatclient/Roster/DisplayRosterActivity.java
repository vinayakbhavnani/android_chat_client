package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.PacketWriter;
import directi.androidteam.training.lib.xml.XMLHelper;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/3/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayRosterActivity extends Activity {
    private static Context context;
    public DisplayRosterActivity(){
        context =this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        //requestForServices();
        requestForRosters();
    }
    public void f() throws XmlPullParserException {
        String testxml = "<iq>   </iq>";//  <query>       <item>hdjsh</item>    <item>jfhjsh</item>     </query>   </iq>";
        StringReader str = new StringReader(testxml);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(str);
        XMLHelper xmlHelper = new XMLHelper();
     //   Tag tag = xmlHelper.tearTag(xpp);
  //      Log.d("XPP : ",xpp.getName()); this is failing thus the input is not correct..:(
        //Log.d("testpacket",xmlHelper.buildPacket(tag));
        Tag t  = xmlHelper.tearPacket(testxml);
        Log.d("XML : Test", xmlHelper.buildPacket(t));
    }

    private void requestForRosters() {
        Log.d("ROSTER :","entered request for ROSTER_MANAGER");
        RosterGet rosterGet = new RosterGet();
     //   rosterGet.setSender(JID.jid).setID("google-roster-1").setQueryAttribute("xmlns","jabber:iq:roster");
        rosterGet.setSender(JID.jid).setID("google-roster-1").setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext","2");
        PacketWriter.addToWriteQueue(rosterGet.getXml());
        Log.d("ROSTER :","done requesting");
    }
    private void requestForServices(){
        Log.d("DEBUG :","entered request for services");
        RosterGet rosterGet = new RosterGet();
        rosterGet.setReceiver("talk.google.com").setQueryAttribute("xlmns", "http://jabber.org/protocol/disco#info");
        PacketWriter.addToWriteQueue(rosterGet.getXml());
    }

    public static void showAllRosters() {
        Intent intent = new Intent(context,DisplayRosterActivity.class);
        intent.putExtra("display","all");
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.d("ROSTER INTENT :", "New Intent Started");
        ListView rosterList = (ListView) findViewById(R.id.rosterlist);
        String rosterToBeDisplayed = (String)intent.getExtras().get("display");
        if(rosterToBeDisplayed.equals("all")){
            Log.d("ROSTER INTENT ALL :", "New Intent Started - ALL");
            RosterManager rosterManager = RosterManager.getInstance();
            ArrayList<RosterEntry> rosterEntries = rosterManager.displayRoster("default");
            ArrayList<String> values = new ArrayList<String>();
            for (RosterEntry rosterEntry : rosterEntries) {
                values.add(rosterEntry.getJid());
            }
           // ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.rosterlistitem,R.id.roster_item,values);//android.R.layout.simple_list_item_1,android.R.id.text1,values);
            RosterItemAdapter adapter = new RosterItemAdapter(this,rosterManager.getRosterList());
            rosterList.setAdapter(adapter);
            rosterList.setTextFilterEnabled(true);

        }
    }
}
