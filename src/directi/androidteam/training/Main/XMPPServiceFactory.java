package directi.androidteam.training.Main;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/30/12
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class XMPPServiceFactory {
    public static XMPPServiceI getXMPPService() {
        return new Service();
    }
}
