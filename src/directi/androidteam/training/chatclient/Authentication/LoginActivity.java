package directi.androidteam.training.chatclient.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import directi.androidteam.training.chatclient.Constants;
import directi.androidteam.training.chatclient.R;

public class LoginActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        attachIcon(R.id.gtalk_icon);
        attachIcon(R.id.facebook_icon);
        attachIcon(R.id.yahoo_icon);
    }

    private void attachIcon(int icon_id) {
        ImageView view = (ImageView) findViewById(icon_id);
        Drawable drawing = view.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = dpToPx(Constants.login_icon_size);
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        view.setImageDrawable(result);
        view.setScaleType(ImageView.ScaleType.FIT_START);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private int dpToPx(int dp)
    {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }


    /**
     * Called when the user clicks the Login button
     */
    public void loginUser(View view) {
        Intent intent = new Intent(this, DisplayRosterActivity.class);
        EditText username_edit_text = (EditText) findViewById(R.id.username);
        EditText password_edit_text = (EditText) findViewById(R.id.password);
        String username = username_edit_text.getText().toString();
        String password = password_edit_text.getText().toString();
        intent.putExtra(Constants.USERNAME_KEY, Constants.username);
        (new ConnectGTalk()).execute(username, Constants.password);
        startActivity(intent);
    }
}
