package directi.androidteam.training.chatclient.Util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/9/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketWriter extends Service {
    private static PrintWriter writer =  ConnectionHandler.getWriter();
    private static ArrayList<String> list =  new ArrayList<String>();

    public static void addToWriteQueue(String msg){
        list.add(msg);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void write(String str){
        writer.write(str);
        writer.flush();
    }
    public void onCreate() {
        while(true){
            if(!list.isEmpty()){
                String str = list.remove(0);
                write(str);
            }
        }
    }


}
