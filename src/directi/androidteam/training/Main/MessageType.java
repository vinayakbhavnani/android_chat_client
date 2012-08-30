package directi.androidteam.training.Main;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/29/12
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public enum MessageType {
    CHAT,GROUP_CHAT,ERROR;
    String getMessageType(){
        return name();
    }
}
