package directi.androidteam.training.chatclient.Roster;

import android.util.Log;
import directi.androidteam.training.StanzaStore.JID;
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
    private ArrayList<RosterEntry> dndList = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> awayList = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> otherList = new ArrayList<RosterEntry>();
    private ArrayList<RosterEntry> frndReqList = new ArrayList<RosterEntry>();
    public HashMap<String,RosterEntry> rosterLookup = new HashMap<String, RosterEntry>();
    public static RosterManager ROSTER_MANAGER = new RosterManager();
    private HashMap<String,String> requestID = new HashMap<String, String>();
    private RosterManager() {
    }
    public static RosterManager getInstance(){
        return ROSTER_MANAGER;
    }

    public static void flush () {
        ROSTER_MANAGER = new RosterManager();
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
                if(tag.getAttribute("subscription").equals("both")) {
                    String jid = tag.getAttribute("jid");
                    if(jid==null)
                        continue;
                    RosterEntry rosterEntry = new RosterEntry(jid);
                    rosterEntries.add(rosterEntry);
                    rosterLookup.put(jid,rosterEntry);
                    ArrayList<Tag> childTags = tag.getChildTags();
                    if(childTags!=null)
                    for (Tag childTag : childTags) {
                        if(childTag.getTagname().equals("group")) {
                            addRosterToGroup(jid,childTag.getContent());
                            Log.d("GROUP : ", "jid : "+ jid+" groupname : "+childTag.getContent());
                        }
                    }
                }
                else if(tag.getAttribute("subscription").equals("to")) {
                    String jid = tag.getAttribute("jid");
                    if(jid==null)
                        continue;
                    RosterEntry rosterEntry = new RosterEntry(jid);
                    frndReqList.add(rosterEntry);
                }
            }
        }
        Log.d("ssss","inside roster manager -set roster list.. will update adapter list n display it");
        RequestRoster.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity)RequestRoster.callerActivity).updateRosterList(getRosterList());
            }
        });
    }
    public ArrayList<RosterEntry> getRosterList(){
        ArrayList<RosterEntry> tempList = new ArrayList<RosterEntry>();
        tempList.addAll(chatList);
        tempList.addAll(dndList);
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
        PacketWriter.addToWriteQueue(rosterSet.getTag());
        requestID.put(rosterSet.getID(), "0");
    }
    public void addRosterEntry(String newJID) {
        if(newJID ==null || newJID.equals(""))
            return;
        RosterEntry rosterEntry = new RosterEntry(newJID);
        addRosterEntry(rosterEntry);
        PresenceS presenceS = new PresenceS();
        presenceS.addReceiver(newJID);
        presenceS.addType("subscribe");
        PacketWriter.addToWriteQueue(presenceS.getTag());
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
        PacketWriter.addToWriteQueue(rosterSet.getTag());
        Log.d("ssss", "inside roster manager -delete in roster list.. will update adapter list n display it");
        ((DisplayRosterActivity)SendPresence.callerActivity).updateRosterList(getRosterList());
    }

    public void updatePresence(PresenceS presence) {
        String from = presence.getFrom();
        Log.d("update presence","function started");
        from = from.split("/")[0];
        if(from==null || rosterLookup==null || !rosterLookup.containsKey(from))
            return;
        Log.d("ccc","no nulls from : "+ from);
        RosterEntry rosterEntry = rosterLookup.get(from);
        String prevAvail = rosterEntry.getPresence();
        String avail = presence.getAvailability();
        int flag =0 ;
        if(avail!=null) {
            rosterEntry.setPresence(avail);
            Log.d("roster manager ","statys"+from+" "+avail);
            if(dndList.contains(rosterEntry)) {
                dndList.remove(rosterEntry);
            }
            if(chatList.contains(rosterEntry)) {
                chatList.remove(rosterEntry);
            }
            if(awayList.contains(rosterEntry)) {
                awayList.remove(rosterEntry);
            }
            if(prevAvail==null)
                prevAvail="";
            if(avail.equals("chat")){
                    if (prevAvail.equals("chat"))
                        flag =1 ;
                        chatList.add(rosterEntry);
            }
            else if(avail.equals("away")) {
                    if (prevAvail.equals("away"))
                        flag = 1;
                        awayList.add(rosterEntry);
            }
            else if(avail.equals("dnd")) {
                if (prevAvail.equals("dnd"))
                    flag = 1;
                awayList.add(rosterEntry);
            }
        }
        String status = presence.getStatus();
        if(status==null && flag==1)
            return;
        if(status!=null) {
            String prevStatus = rosterEntry.getStatus();
            if(prevStatus.equals(status) && flag==1)
                return;
            rosterEntry.setStatus(status);
            Log.d("roster manager ","statys"+from+" "+status);
        }
        Log.d("ssss","inside roster manager -update presence of roster list.. will update adapter list n display it");
        SendPresence.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity)SendPresence.callerActivity).updateRosterList(getRosterList());
            }
        });
    }

    public void updatePhoto(VCard vCard) {
        String from = vCard.getBareJID();
        if(from==null || rosterLookup==null || !rosterLookup.containsKey(from))
            return;
        Log.d("ccc","no nulls from : "+ from);
        RosterEntry rosterEntry = rosterLookup.get(from);
        String prevAvail = rosterEntry.getPresence();
        int flag =0 ;
        if(vCard.getAvatar()!=null) {
            rosterEntry.avatar = vCard.getAvatar();
//            if(dndList.contains(rosterEntry)) {
//                dndList.remove(rosterEntry);
//            }
//            if(chatList.contains(rosterEntry)) {
//                chatList.remove(rosterEntry);
//            }
//            if(awayList.contains(rosterEntry)) {
//                awayList.remove(rosterEntry);
//            }
//            if(prevAvail==null)
//                prevAvail="";
//            if(avail.equals("chat")){
//                if (prevAvail.equals("chat"))
//                    flag =1 ;
//                chatList.add(rosterEntry);
//            }
//            else if(avail.equals("away")) {
//                if (prevAvail.equals("away"))
//                    flag = 1;
//                awayList.add(rosterEntry);
//            }
//            else if(avail.equals("dnd")) {
//                if (prevAvail.equals("dnd"))
//                    flag = 1;
//                awayList.add(rosterEntry);
//            }
        }
//        String status = presence.getStatus();
//        if(status==null && flag==1)
//            return;
//        if(status!=null) {
//            String prevStatus = rosterEntry.getStatus();
//            if(prevStatus.equals(status) && flag==1)
//                return;
//            rosterEntry.setStatus(status);
//            Log.d("roster manager ","statys"+from+" "+status);
//        }
        Log.d("ssss","inside roster manager -update presence of roster list.. will update adapter list n display it");
        SendPresence.callerActivity.runOnUiThread(new Runnable() {
            public void run() {
                ((DisplayRosterActivity)SendPresence.callerActivity).updateRosterList(getRosterList());
            }
        });
    }

    public RosterEntry searchRosterEntry(String jid) {
        RosterEntry rosterEntry = rosterLookup.get(jid);
        return rosterEntry;
    }
    public void acceptFrndReq(String jid) {
        return;
    }
    public void addRosterToGroup(String jid, String groupName) {
        if(jid==null || jid.equals("") || groupName==null || groupName.equals(""))
            return;
        RosterGroupManager rosterGroupManager = RosterGroupManager.getInstance();
        RosterGroup rosterGroup = rosterGroupManager.getRosterGroupByName(groupName);
        if(rosterGroup==null) {
            rosterGroup = new RosterGroup(groupName);
            rosterGroupManager.addNewGroup(rosterGroup);
        }
        RosterEntry rosterEntry = rosterLookup.get(jid);
        rosterGroup.addRosterEntry(rosterEntry);
    }
    public void addNewGroup(String groupName) {
        RosterGroup rosterGroup = new RosterGroup(groupName);
        RosterGroupManager.getInstance().addNewGroup(rosterGroup);
    }

    public ArrayList<RosterEntry> searchRosterEntries(String newJID) {
        ArrayList<RosterEntry> rosterEntriesResult = new ArrayList<RosterEntry>();
        for (RosterEntry rosterEntry : rosterEntries) {
            if(rosterEntry.getJid().contains(newJID))
                rosterEntriesResult.add(rosterEntry);
        }
        return rosterEntriesResult;
    }

}
