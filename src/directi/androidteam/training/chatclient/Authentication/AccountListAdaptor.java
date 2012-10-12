package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import directi.androidteam.training.chatclient.Chat.ChatListItem;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 8/10/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountListAdaptor extends BaseAdapter {
    public static int resourceId = R.layout.accountitem;
    Context context;
    public AccountListAdaptor(Context context){
        super();
        this.context = context;

    }

    public Context getContext(){
        return this.context;
    }

    @Override
    public int getCount() {
       return AccountManager.getInstance().getnumAccount();
    }

    @Override
    public Account getItem(int i) {
        return AccountManager.getInstance().getAccountfromList(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position,View view,ViewGroup viewg){
        View row = view;

        AccountListHolder holder = null;
        Account cli = getItem(position);


        /* if(row == null)
        {*/
       // Log.d("isSenderCli", cli.isSender() + "" + cli.getMessage() + " " + (new Integer(position)).toString());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        row = inflater.inflate(resourceId, viewg, false);

        holder = new AccountListHolder();


        holder.username = (TextView)row.findViewById(R.id.accountitem_jid);
        holder.icon = (ImageView)row.findViewById(R.id.accountitem_icon);
        holder.status = (TextView)row.findViewById(R.id.accountitem_presence);
        holder.settings = (ImageView)row.findViewById(R.id.accountitem_settings);

        row.setTag(holder);


        /*}
        else
        {
            Log.d("isSenderView","View "+cli.getMessage());

            holder = (ChatListHolder)row.getTag();
        }*/

        holder.username.setText(cli.accountJid);
        holder.settings.setImageResource(R.drawable.settings);
        holder.settings.setTag(cli.getAccountJid());
        if(cli.isLoginStatus().equals(LoginStatus.ONLINE)){
            holder.status.setText("online");
            holder.status.setTextColor(R.color.Black);
        }
        else{
            holder.status.setText("offline");
            holder.status.setTextColor(R.color.Gray);
        }
        holder.icon.setImageResource(cli.getServiceIcon());


        return row;
    }
    static class AccountListHolder{
        TextView username;
        ImageView icon;
        TextView status;
        ImageView settings;
    }
}
