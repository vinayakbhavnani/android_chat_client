package directi.androidteam.training.chatclient.Authentication;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 16/10/12
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Publisher {
    public void addSubscriber(Subscriber o);
    public void removeSubscriber(Subscriber o);
    public void publish();


}
