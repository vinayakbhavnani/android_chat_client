package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Authentication.UserListActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import directi.androidteam.training.StanzaStore.JID;
import directi.androidteam.training.StanzaStore.RosterGet;
import directi.androidteam.training.chatclient.Constants;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;
import java.util.UUID;

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
        ImageView myImage = (ImageView) findViewById(R.id.Roster_myimage);
        attachIcon(myImage);
        Log.d("XXXX","oncreate roster");
        requestForRosters();
    }
    private int dpToPx(int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }
    public void attachIcon(ImageView view) {
        Drawable drawing = view.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = dpToPx(Constants.login_icon_size);
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        view.setImageDrawable(result);
        view.setScaleType(ImageView.ScaleType.FIT_START);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private void requestForRosters() {
        Log.d("ROSTER :","entered request for ROSTER_MANAGER");
        RosterGet rosterGet = new RosterGet();
     //   rosterGet.setSender(JID.jid).setID("google-roster-1").setQueryAttribute("xmlns","jabber:iq:roster");
        rosterGet.setSender(JID.jid).setID(UUID.randomUUID().toString()).setQueryAttribute("xmlns","jabber:iq:roster").setQueryAttribute("xmlns:gr","google:roster").setQueryAttribute("gr:ext","2");
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
        Log.d("XXXX","show AllRosters Called");
        intent.putExtra("display", "all");
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.d("ROSTER INTENT :", "New Intent Started");
        ImageView myImage = (ImageView) findViewById(R.id.Roster_myimage);
        attachIcon(myImage);

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
            RosterItemAdapter adapter = new RosterItemAdapter(this,rosterManager.getRosterList());
            rosterList.setAdapter(adapter);
             rosterList.setTextFilterEnabled(true);

        }
    }

    public void goToAccounts(View view) {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

    public void signOut(View view) {
    }

    protected Dialog onCreateDialog(int id) {
        AddRosterDialog dialog = new AddRosterDialog(context);
        dialog.setContentView(R.layout.roster_add_dialog);
        dialog.setTitle("Add Your Friend");
        return dialog;
    }
    public void addRosterEntry(View view){
        showDialog(1);
    }
}

