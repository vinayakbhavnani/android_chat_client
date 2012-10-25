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
        Tag NTag = tag.getChildTag("N");
        if (NTag != null) {
            String fullName = "";
            Tag givenTag = NTag.getChildTag("GIVEN");
            if (givenTag != null) {
                String givenName = givenTag.getContent();
                if (givenName != null) {
                    fullName += (givenName + " ");
                }
            }
            Tag middleTag = NTag.getChildTag("MIDDLE");
            if (middleTag != null) {
                String middleName = middleTag.getContent();
                if (middleName != null) {
                    fullName += (middleName + " ");
                }
            }
            Tag familyTag = NTag.getChildTag("FAMILY");
            if (familyTag != null) {
                String familyName = familyTag.getContent();
                if (familyName != null) {
                    fullName += familyName;
                }
            }
            if (!(fullName.equals(""))) {
                this.name = fullName;
            }
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
