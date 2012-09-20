package directi.androidteam.training.chatclient.Roster;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 9/12/12
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterGroupManager {
    private HashMap<String,RosterGroup> rosterGroupHashMap;
    private static final RosterGroupManager rosterGroupManager = new RosterGroupManager();

    private RosterGroupManager(){
        rosterGroupHashMap = new HashMap<String, RosterGroup>();
        RosterGroup rosterGroup = new RosterGroup("default");
        rosterGroupHashMap.put("default",rosterGroup);
    }
    public Boolean addNewGroup(RosterGroup rosterGroup){
        if(rosterGroupHashMap==null)
            rosterGroupHashMap = new HashMap<String, RosterGroup>();
        if(rosterGroupHashMap.containsKey(rosterGroup.getGroupName()))
            return false;
        rosterGroupHashMap.put(rosterGroup.getGroupName(),rosterGroup);
        return true;
    }
    public Boolean addNewGroup(String groupName) {
        RosterGroup rosterGroup = new RosterGroup(groupName);
        return addNewGroup(rosterGroup);
    }
    public Boolean ifGroupExists(RosterGroup rosterGroup){
        if(rosterGroupHashMap==null || !rosterGroupHashMap.containsKey(rosterGroup.getGroupName()))
            return false;
        return true;
    }
    public static RosterGroupManager getInstance() {
        return rosterGroupManager;
    }
    public RosterGroup getRosterGroupByName(String groupName) {
        if(rosterGroupHashMap==null){
            return null;
        }
        return rosterGroupHashMap.get(groupName);
    }
}
