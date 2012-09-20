package directi.androidteam.training.chatclient.Roster;

import android.util.Log;
import directi.androidteam.training.StanzaStore.PresenceS;
import directi.androidteam.training.StanzaStore.RosterResult;
import directi.androidteam.training.StanzaStore.RosterSet;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Util.PacketWriter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/10/12
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterManager {
    public ArrayList<Tag> rosterList  = new ArrayList<Tag>();
    public ArrayList<RosterEntry> rosterEntries = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> chatList = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> busyList = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> awayList = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> otherList = new ArrayList<RosterEntry>();
    public HashMap<String,RosterEntry> rosterLookup = new HashMap<String, RosterEntry>();
    public static final RosterManager ROSTER_MANAGER = new RosterManager();
    private HashMap<String,String> requestID = new HashMap<String, String>();
    private RosterManager() {
    }
    public static RosterManager getInstance(){
        return ROSTER_MANAGER;
    }

    public void setRosterList(RosterResult rosterResult){
        Log.d("ROSTER Manager","setRosterList starts");
        ArrayList<Tag> list = rosterResult.getListOfRosters();
        String id = rosterResult.getID();
        if(list==null){              //            Log.d("setRoster tagname :",tag.getTagname());
            return;
        }
        String type = rosterResult.getType();
        if(type!=null && type.equals("result"))
        requestID.put(id,"1");
        else
        requestID.put(id,"2");
        rosterList = list;
        for (Tag tag : list) {
            if(tag.getTagname().equals("item")){
                if(!tag.getAttribute("subscription").equals("none")) {
                    String jid = tag.getAttribute("jid");
                    if(jid==null)
                        return;
                    RosterEntry rosterEntry = new RosterEntry(jid);
                    rosterEntries.add(rosterEntry);
                    rosterLookup.put(jid,rosterEntry);
                }
            }
        }
        DisplayRosterActivity.showAllRosters();
    }
    public ArrayList<RosterEntry> getRosterList(){
        ArrayList<RosterEntry> tempList = new ArrayList<RosterEntry>();
        tempList.addAll(chatList);
        tempList.addAll(busyList);
        tempList.addAll(awayList);
        return tempList;
    }
    private void copyList(ArrayList<RosterEntry> tempList,String type){
        for (RosterEntry rosterEntry : rosterEntries) {
            if(rosterEntry.getPresence().equals("away"))
                tempList.add(rosterEntry);
        }
    }
    public ArrayList<RosterEntry> displayRoster(String groupName){
        Log.d("setRoster : ","displayRoster");
        RosterGroupManager rosterGroupManager = RosterGroupManager.getInstance();
        RosterGroup rosterGroup = rosterGroupManager.getRosterGroupByName(groupName);
        ArrayList<RosterEntry> rosterEntries =  rosterGroup.getRosterEntries();
        for (RosterEntry rosterEntry : rosterEntries) {
            Log.d("JID :",rosterEntry.getJid());
        }
        return rosterEntries;
    }
    public void addRosterEntry(RosterEntry rosterEntry){
        RosterSet rosterSet = new RosterSet();
        rosterSet.addQuery(rosterEntry.getJid());
        PacketWriter.addToWriteQueue(rosterSet.getXml());
        requestID.put(rosterSet.getID(),"0");
    }
    public void addRosterEntry(String newJID) {
        if(newJID ==null || newJID.equals(""))
            return;
        RosterEntry rosterEntry = new RosterEntry(newJID);
        addRosterEntry(rosterEntry);
    }
    public void deleteRosterEntry(String JID) {
        if(JID==null || rosterLookup==null || JID.equals("") || !rosterLookup.containsKey(JID))
            return;
        RosterEntry rosterEntry = rosterLookup.get(JID);
        rosterEntries.remove(rosterEntry);
        rosterLookup.remove(JID);
        RosterSet rosterSet = new RosterSet();
        rosterSet.addQuery(rosterEntry.getJid());
        rosterSet.addSubscription("remove");
        PacketWriter.addToWriteQueue(rosterSet.getXml());
        DisplayRosterActivity.showAllRosters();
    }

    public void updatePresence(PresenceS presence) {
        String from = presence.getFrom();
        Log.d("update presence","function started");
        from = from.split("/")[0];
        if(from==null || rosterLookup==null || !rosterLookup.containsKey(from))
            return;
        Log.d("ccc","no nulls from : "+ from);
        RosterEntry rosterEntry = rosterLookup.get(from);
        String avail = presence.getAvailability();
        if(avail!=null) {
            rosterEntry.setPresence(avail);
            Log.d("roster manager ","statys"+from+" "+avail);
            if(busyList.contains(rosterEntry))
                busyList.remove(rosterEntry);
            if(chatList.contains(rosterEntry))
                chatList.remove(rosterEntry);
            if(awayList.contains(rosterEntry))
                awayList.remove(rosterEntry);
            if(avail.equals("busy"))
                busyList.add(rosterEntry);
            if(avail.equals("chat"))
                chatList.add(rosterEntry);
            if(avail.equals("away"))
                awayList.add(rosterEntry);
        }
        String status = presence.getStatus();
        if(status!=null) {
            rosterEntry.setStatus(status);
            Log.d("roster manager ","statys"+from+" "+status);
        }
        DisplayRosterActivity.showAllRosters();
    }

    public void changeAvailability(String avail) {
        MyProfile myProfile = MyProfile.getInstance();
        myProfile.setAvailability(avail);
        myProfile.setStatusAndPresence();
    }
    public RosterEntry searchRosterEntry(String jid) {
        RosterEntry rosterEntry = rosterLookup.get(jid);
        return rosterEntry;
    }
}
