package directi.androidteam.training.chatclient.Authentication.android_chat_client.src.directi.androidteam.training.lib.TCPHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/31/12
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatSocket {
    private static Socket socket = null;
    private ChatSocket(){

    }
    public static Socket getSocket() {
        if(socket==null){
            try {
                socket = new Socket("talk.google.com",5222);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return socket;
    }
}
