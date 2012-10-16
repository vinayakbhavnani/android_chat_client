package directi.androidteam.training.chatclient.Authentication;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 16/10/12
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void trigger(Subject s);
}
