package directi.androidteam.training.chatclient.Authentication;

import android.app.ActionBar;
import android.app.Activity;
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
    private Activity activity;
    private String className;
    public MyTabListener(Fragment fragment,Activity activity,String className) {
        this.fragment = fragment;
        this.activity = activity;
        this.className = className;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("ioio","tab select");
        if(fragment==null){
            fragment = Fragment.instantiate(activity,className);
            fragmentTransaction.add(fragment,"accounts");
        }
        else {
            fragmentTransaction.attach(fragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("ioio","tab unselect");
        if(fragment!=null) {
            fragmentTransaction.remove(fragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("ioio","tab reselect");
        return;
    }
}
