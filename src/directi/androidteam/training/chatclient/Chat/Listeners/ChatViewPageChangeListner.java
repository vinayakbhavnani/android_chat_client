package directi.androidteam.training.chatclient.Chat.Listeners;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.TextView;
import directi.androidteam.training.chatclient.Chat.ChatStore;
import directi.androidteam.training.chatclient.Chat.MyFragmentManager;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Roster.RosterItem;
import directi.androidteam.training.chatclient.Roster.RosterManager;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatViewPageChangeListner implements ViewPager.OnPageChangeListener {
    Context context;
    FragmentManager fragmentManager;
    public ChatViewPageChangeListner(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        return;
    }

    @Override
    public void onPageSelected(int i) {
        updatePageUI(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        return;
    }

    private void updatePageUI(int i){
        String from = MyFragmentManager.getInstance().getJidByFragId(i);
        String accountUID = ChatStore.getInstance().getAcctUID(from);

        if(from!=null)
        {
            EditText editText = (EditText) ((Activity)context).findViewById(R.id.enter_message);
            editText.addTextChangedListener(new MsgTextChangeListener(from,accountUID));
        }

        TextView hleft = (TextView)((Activity)context).findViewById(R.id.chatboxheader_left);
        TextView hright = (TextView)((Activity)context).findViewById(R.id.chatboxheader_right);

        String left = MyFragmentManager.getInstance().getJidByFragId(i - 1);
        String right = MyFragmentManager.getInstance().getJidByFragId(i + 1);
        if(left!=null) {
            RosterItem rosterItem = RosterManager.getInstance().getRosterItem(ChatStore.getInstance().getAcctUID(left), left);
            if(rosterItem==null) {
                hleft.setText(left.split("@")[0]);
            }
            else {
                hleft.setText(rosterItem.getName());
            }
        }
        else {
            hleft.setText("");
        }
        if(right!=null) {
                RosterItem rosterItem = RosterManager.getInstance().getRosterItem(ChatStore.getInstance().getAcctUID(right), right);
            if(rosterItem==null) {
                hright.setText(right.split("@")[0]);
            }
            else {
                hright.setText(rosterItem.getName());
            }
        }
        else {
            hright.setText("");
        }
    }
}
