package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import directi.androidteam.training.chatclient.Constants;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/13/12
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterItemAdapter extends ArrayAdapter<RosterEntry>{
    Context context;
    ArrayList<RosterEntry> rosterEntries;
    public RosterItemAdapter(Context context, ArrayList<RosterEntry> rosterEntries) {
        super(context, R.layout.rosterlistitem, rosterEntries);
        Log.d("XXXX", "roster refresh called with size " + rosterEntries.size());
        this.context = context;
        this.rosterEntries = rosterEntries;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        RosterItemHolder rosterItemHolder;
        if(convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v = inflater.inflate(R.layout.rosterlistitem,null);

            rosterItemHolder = new RosterItemHolder();
            rosterItemHolder.rosterImg = (ImageView) v.findViewById(R.id.roster_image);
            rosterItemHolder.rosterJid = (TextView) v.findViewById(R.id.roster_item);
            rosterItemHolder.availButton = (Button) v.findViewById(R.id.roster_availability_button);
            rosterItemHolder.rosterStatus = (TextView) v.findViewById(R.id.roster_status);
            v.setTag(rosterItemHolder);
        }
        else {
            rosterItemHolder = (RosterItemHolder) v.getTag();
        }
        RosterEntry rosterEntry = rosterEntries.get(position);
        if(rosterEntry!=null){
            attachIcon(rosterItemHolder.rosterImg);
            rosterItemHolder.rosterJid.setText(rosterEntry.getJid());
            if(rosterEntry.getPresence()!=null) {
                Log.d("ssss","jid : "+rosterEntry.getJid()+"  presence :"+rosterEntry.getPresence() + "status"+rosterEntry.getStatus());
            if(rosterEntry.getPresence().equals("dnd")) {
                rosterItemHolder.availButton.setBackgroundColor(Color.RED);
            }
            else if(rosterEntry.getPresence().equals("away")) {
                rosterItemHolder.availButton.setBackgroundColor(Color.YELLOW);
            }
            else if(rosterEntry.getPresence().equals("chat")) {
                rosterItemHolder.availButton.setBackgroundColor(Color.GREEN);
            }
            else {
                rosterItemHolder.availButton.setBackgroundColor(Color.GRAY);
            }
            }
        else {
                rosterItemHolder.availButton.setBackgroundColor(Color.GRAY);
         }
            if(rosterEntry.getStatus()!=null) {
                rosterItemHolder.rosterStatus.setText(rosterEntry.getStatus());
            }

        }
        return v;
    }
    static class RosterItemHolder{
        ImageView rosterImg;
        TextView rosterJid;
        TextView rosterStatus;
        Button availButton;
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
}
