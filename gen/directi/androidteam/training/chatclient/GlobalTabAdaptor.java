package directi.androidteam.training.chatclient;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/19/12
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalTabAdaptor extends PagerAdapter {
    private FragmentManager fragmentManager;
    public GlobalTabAdaptor(FragmentManager supportFragmentManager) {
        super();
        this.fragmentManager = supportFragmentManager;
    }

    @Override
    public int getCount() {
        return GlobalTabActivity.NUM_TABS;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return false;
    }
}
