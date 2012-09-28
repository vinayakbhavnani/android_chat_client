package directi.androidteam.training.chatclient.Authentication;

import android.view.View;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/27/12
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditTextFocusChangeListener implements View.OnFocusChangeListener {
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (!(hasFocus)) {
            if (((EditText)view).getText().toString().equals("")) {
                ((EditText)view).setError("This Field Cannot Be Left Blank");
            }
        }
    }
}
