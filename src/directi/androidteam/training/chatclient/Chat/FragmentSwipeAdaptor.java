package directi.androidteam.training.chatclient.Chat;




//import android.support.v4.app.FragmentManager;
//import 	android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

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
        String from = directi.androidteam.training.chatclient.Chat.FragmentManager.getInstance().FragToJid(i);
        Log.d("DDDD","fragswipeadap - getitem" + from);
        return ChatFragment.getInstance(from);
    }

    @Override
    public int getCount() {
       return directi.androidteam.training.chatclient.Chat.FragmentManager.getInstance().getSizeofActiveChats();
    }

    @Override
    public int getItemPosition(Object item){
        return POSITION_NONE;
    }
}
