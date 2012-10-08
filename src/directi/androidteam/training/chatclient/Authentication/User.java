package directi.androidteam.training.chatclient.Authentication;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 9/13/12
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String username;
    private String password;
    private String state;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
