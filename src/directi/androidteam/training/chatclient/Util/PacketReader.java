package directi.androidteam.training.chatclient.Util;

import android.content.Intent;
import android.util.Log;
import directi.androidteam.training.ChatApplication;
import directi.androidteam.training.chatclient.Authentication.*;
import directi.androidteam.training.chatclient.Chat.ChatBox;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/9/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketReader implements ServiceThread{
    private BufferedReader reader;
    String accountjid;
    public PacketReader(BufferedReader r,String jid) {
        this.reader = r;
        this.accountjid=jid;

    }

    @Override
    public void execute() {
        Log.d("Service Thread","I am Packet Reader");


        try {

            XMLHelper helper = new XMLHelper();

            if(helper.tearxmlPacket(reader,accountjid)==null){
                //Intent intent = new Intent(ChatApplication.getAppContext(), ChatBox.class);
                //intent.putExtra("error","connection");
//                ChatBox.getContext().startActivity(intent);
                Account account  = AccountManager.getInstance().getAccount(accountjid);
                account.setLoginStatus(LoginStatus.OFFLINE);
                if(account.getPersistedLoginStatus().equals(LoginStatus.ONLINE) && NetworkManager.connected){
                    new LoginTask(AccountManager.getInstance().getAccount(accountjid));
                }
                }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void read(){
        while(true)
            try {
                String response = "";
                int c;
                while (!response.contains(">")) {
                    c = this.reader.read();
                    response = response + (char)c;

            }
                Log.d("packetinxml", response);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }
}
