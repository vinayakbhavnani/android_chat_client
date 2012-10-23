package directi.androidteam.training.chatclient.Chat;


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
    static FragmentManager fragmentManager;
    public FragmentSwipeAdaptor(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {
        MyFragmentManager manager = MyFragmentManager.getInstance();
        String from = manager.getJidByFragId(i);
        Fragment fragment = manager.getFragByJID(from);
        fragmentManager.beginTransaction().add(fragment, from).commit();
        return fragment;
    }

    @Override
    public int getCount() {
       return MyFragmentManager.getInstance().getSizeofActiveChats();
    }

    @Override
    public void destroyItem(android.view.ViewGroup container, int position, java.lang.Object object) {
        super.destroyItem(container,position,object);
        Log.d("xcxc", "destroy swiper : position = "+position);
    }


    public static Fragment getFragment(String jid) {
        return fragmentManager.findFragmentByTag(jid);
    }
}
