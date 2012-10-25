package directi.androidteam.training.chatclient.Authentication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 25/10/12
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class DialogBuilder {
    public static void createSetStatusDialog(Context context, final Account account){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Set Status");
        final EditText input = new EditText(context);
        builder.setView(input);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String statusText = input.getText().toString();
                account.sendStatus(statusText);
                Log.d("SetStatus","Ok "+statusText);
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("SetStatus","Cancel");
            }
        });
        builder.show();
    }
}
