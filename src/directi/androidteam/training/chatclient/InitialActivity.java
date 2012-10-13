package directi.androidteam.training.chatclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import directi.androidteam.training.chatclient.Authentication.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/19/12
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class InitialActivity extends Activity {

    @Override
    public void onPause() {
        super.onPause();
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar_loading);
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showLoading() {
        this.setContentView(R.layout.loading);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar_loading);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer option = AccountManager.getInstance().loginAccounts();
        Log.d("initialActivity Option",option.toString());
        Intent intent;
        switch (option){
            case -1 :
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case 0 :
                intent = new Intent(this,DisplayAccounts.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                showLoading();
                AccountManager.getInstance().initialActivity = this;
                break;
        }
    }
}
