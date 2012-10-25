package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Authentication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.gen.directi.androidteam.training.chatclient.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/13/12
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private ArrayList<User> items;
    private Context context;

    public UserAdapter(Context context, int textViewResourceID, ArrayList<User> items) {
        super(context, textViewResourceID, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.userlistitem, null);
        }
        User user = items.get(position);
        if (user != null) {
            TextView tv = (TextView)view.findViewById(R.id.user_item);
            tv.setText(user.getUsername());
        }
        return view;
    }
}
