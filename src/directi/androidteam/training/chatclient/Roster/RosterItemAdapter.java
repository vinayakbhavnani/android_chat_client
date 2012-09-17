package directi.androidteam.training.chatclient.Roster;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
//        super(context, R.layout.rosterlistitem, R.id.roster_item, rosterEntries);
        super(context, R.layout.rosterlistitem, rosterEntries);
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
            v.setTag(rosterItemHolder);
        }
        else {
            rosterItemHolder = (RosterItemHolder) v.getTag();
        }
        RosterEntry rosterEntry = rosterEntries.get(position);
        if(rosterEntry!=null){
            attachIcon(rosterItemHolder.rosterImg);
           // rosterItemHolder.rosterImg.setImageResource(R.drawable.android);
            rosterItemHolder.rosterJid.setText(rosterEntry.getJid());
        }
        return v;
    }
    static class RosterItemHolder{
        ImageView rosterImg;
        TextView rosterJid;
    }
    private int dpToPx(int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }
    private void attachIcon(ImageView view) {
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