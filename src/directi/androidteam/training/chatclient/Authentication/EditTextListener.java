package directi.androidteam.training.chatclient.Authentication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/27/12
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditTextListener implements TextWatcher {
    private EditText editText;

    public EditTextListener(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void afterTextChanged(Editable s) {
        return;
    }

    @Override
    public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        if (sequence.toString().equals("")) {
            this.editText.setError("This Field Cannot Be Left Blank");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        return;
    }
}
