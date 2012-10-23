package directi.androidteam.training.chatclient.Roster;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.TagStore.VCardTag;
import directi.androidteam.training.chatclient.R;
import directi.androidteam.training.chatclient.Util.Base64;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 10/2/12
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class VCard {
    private String name;
    private String avatarType;
    private Bitmap avatar;
    private static Bitmap defaultAvatar = BitmapFactory.decodeResource(ChatApplication.getAppContext().getResources(), R.drawable.default_user);

    public VCard(String name) {
        this.name = name;
        this.avatar = defaultAvatar;
    }

    public Bitmap getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return this.name;
    }

    public void populateFromTag(VCardTag tag) {
        Tag FNTag = tag.getChildTag("FN");
        if (FNTag != null) {
            this.name = FNTag.getContent();
        }
        Tag PHOTOTag = tag.getChildTag("PHOTO");
        if (PHOTOTag != null) {
            Tag TYPETag = PHOTOTag.getChildTag("TYPE");
            if (TYPETag != null) {
                this.avatarType = TYPETag.getContent().split("/")[1];
            }
            Tag BINVALTag = PHOTOTag.getChildTag("BINVAL");
            if (BINVALTag != null) {
                this.avatar = decodeAvatar(BINVALTag.getContent());
            }
        }
    }

    public Bitmap decodeAvatar(String encodedAvatar) {
        try {
            byte [] decodedAvatarBinVal = Base64.decode(encodedAvatar);
            return BitmapFactory.decodeByteArray(decodedAvatarBinVal, 0, decodedAvatarBinVal.length);
        } catch (IOException e) {
            Log.d("IOException", e.toString());
        }
        return null;
    }
}
