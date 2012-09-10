package directi.androidteam.training.chatclient.Roster;

import android.util.Log;
import directi.androidteam.training.StanzaStore.RosterResult;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/10/12
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Roster {
    public Roster() {
    }

    public void displayRoster(RosterResult rosterResult){
        ArrayList<String> listOfRosters = rosterResult.getListOfRosters();
        for (String listOfRoster : listOfRosters) {
            Log.d("Roster : ", listOfRoster);
        }
    }
}
