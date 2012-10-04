package directi.androidteam.training.chatclient.Util;

import android.util.Log;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Chat.PacketStatusManager;
import directi.androidteam.training.lib.xml.XMLHelper;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/9/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketWriter implements ServiceThread{
    private static PrintWriter writer;
    private static ArrayList<String> list =  new ArrayList<String>();

    public PacketWriter(PrintWriter w) {
        writer = w;
    }

    public static void addToWriteQueue(String msg){
        list.add(msg);
    }

    public void write(String str){

        writer.write(str);
        writer.flush();
        if(writer.checkError()){
            XMLHelper xmlHelper = new XMLHelper();
            Tag tag = xmlHelper.tearPacket(str);
            String id = tag.getAttribute("id");
            PacketStatusManager.getInstance().setFailure(id);
        }


    }
    @Override
    public void execute() {
        Log.d("Service Thread : ", "I am PacketWriter");
        write("<presence/>");
        while(true){
            if(!list.isEmpty()){
                String str = list.remove(0);
                Log.d("PacketWriter",str);
                write(str);
            }
        }
    }


}
