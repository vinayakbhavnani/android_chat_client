package directi.androidteam.training.chatclient.Chat.Listeners;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import directi.androidteam.training.chatclient.Chat.MyFragmentManager;
import directi.androidteam.training.chatclient.R;

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
        Log.d("qwqw", "page listener - index: " + i);
        updatePageUI(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        return;
    }

    private void updatePageUI(int i){
        String from = MyFragmentManager.getInstance().getJidByFragId(i);
        if(from!=null)
        {
            EditText editText = (EditText) ((Activity)context).findViewById(R.id.enter_message);
            editText.addTextChangedListener(new MsgTextChangeListener(from));
        }

        TextView hleft = (TextView)((Activity)context).findViewById(R.id.chatboxheader_left);
        TextView hright = (TextView)((Activity)context).findViewById(R.id.chatboxheader_right);

        String left = MyFragmentManager.getInstance().getJidByFragId(i - 1);
        String right = MyFragmentManager.getInstance().getJidByFragId(i + 1);
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
