package directi.androidteam.training.chatclient.Chat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 13/9/12
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatListAdaptor extends ArrayAdapter<ChatListItem> {
    Context context;
    ArrayList<ChatListItem> chatListItems;
    public ChatListAdaptor(Context context, ArrayList<ChatListItem> objects) {
        super(context, R.layout.chatlistitem, objects);
        this.chatListItems=objects;
        this.context=context;

    }

   @Override
    public View getView(int position,View view,ViewGroup viewg){
       Log.d("viewofchatlist","hey");
       View row = view;
       int layoutResourceId = chatListItems.get(position).getResourceID();
       ChatListHolder holder = null;
       if(row == null)
       {
           LayoutInflater inflater = ((Activity)context).getLayoutInflater();
           row = inflater.inflate(layoutResourceId, viewg, false);

           holder = new ChatListHolder();
           holder.username = (TextView)row.findViewById(R.id.send_mess_name);
           holder.message = (TextView)row.findViewById(R.id.send_mess_body);
           holder.time = (TextView)row.findViewById(R.id.chatlistitem_time);
           holder.status = (TextView)row.findViewById(R.id.chatlistitem_status);
           row.setTag(holder);
       }
       else
       {
           holder = (ChatListHolder)row.getTag();
       }

       ChatListItem cli = chatListItems.get(position);
       if(!cli.isSender())
        holder.username.setText(cli.getUsername().split("@")[0]);
       holder.message.setText(cli.getMessage());
       holder.time.setText(cli.getTime());
       if(!cli.isStatus())
           holder.status.setVisibility(0);


        return row;
   }
   static class ChatListHolder{
       TextView username;
       TextView message;
       TextView time;
       TextView status;
   }
}
