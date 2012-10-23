package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import directi.androidteam.training.ChatApplication;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 10/18/12
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcctScreenAddOnClickListener implements View.OnClickListener {
    Activity activity;
    public AcctScreenAddOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ChatApplication.getAppContext(),LoginActivity.class);
        activity.startActivity(intent);
    }
}
