package directi.androidteam.training.chatclient.Authentication;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/18/12
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyTabListener implements ActionBar.TabListener {
    private Fragment fragment;
    public MyTabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("ioio","tab select");
        fragmentTransaction.add(fragment,"accounts");
        fragmentTransaction.show(fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("ioio","tab unselect");
        fragmentTransaction.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("ioio","tab reselect");
        return;
    }
}
