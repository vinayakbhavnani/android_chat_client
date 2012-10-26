package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Chat.Listeners;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.gen.directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Chat.MyFragmentManager;
import directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.chatclient.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/1/12
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatViewPageChangeListner implements ViewPager.OnPageChangeListener {
    Context context;
    public ChatViewPageChangeListner(Context context) {
        this.context = context;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        return;
    }

    @Override
    public void onPageSelected(int i) {
        Log.d(Constants.DEBUG_CHATBOX, "page listener - index: " + i);
        updateHeader(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        return;
    }

    public void updateHeader(int i){
        TextView hleft = (TextView)((Activity)context).findViewById(R.id.chatboxheader_left);
        TextView hright = (TextView)((Activity)context).findViewById(R.id.chatboxheader_right);

        String left = MyFragmentManager.getInstance().FragToJid(i - 1);
        String right = MyFragmentManager.getInstance().FragToJid(i+1);
        if(left!=null)
            hleft.setText(left.split("@")[0]);
        else
            hleft.setText("");
        if(right!=null)
            hright.setText(right.split("@")[0]);
        else
            hright.setText("");
    }
}