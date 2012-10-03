package directi.androidteam.training.chatclient.Chat;




//import android.support.v4.app.FragmentManager;
//import 	android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import directi.androidteam.training.chatclient.Constants;
import directi.androidteam.training.chatclient.PacketHandlers.MessageHandler;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 17/9/12
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public  class FragmentSwipeAdaptor extends FragmentStatePagerAdapter {
    public FragmentSwipeAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        String from = MessageHandler.getInstance().FragToJid(i);
        Log.d(Constants.DEBUG_CHATBOX,"fragmentswipe - from "+from);
        return ChatFragment.getInstance(from);
    }

    @Override
    public int getCount() {
       return MessageHandler.getInstance().getChatLists().size();
    }

    @Override
    public int getItemPosition(Object item){
        return POSITION_NONE;
    }
}
