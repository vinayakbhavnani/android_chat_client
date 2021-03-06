package directi.androidteam.training.chatclient.Util;


import android.util.Log;
import directi.androidteam.training.TagStore.Tag;
import directi.androidteam.training.chatclient.Authentication.AccountManager;
import directi.androidteam.training.chatclient.Chat.PacketStatusManager;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 4/9/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketWriter implements ServiceThread{
    private static PrintWriter writer;
    private static ArrayList<Tag> list =  new ArrayList<Tag>();
    private static HashMap<String,PrintWriter> outputStreams = new HashMap<String, PrintWriter>();

    public PacketWriter() {
        //writer = w;
    }

    public static void addToWriteQueue(Tag msg){
        list.add(msg);
    }

    public void write(Tag tag){
        PrintWriter out = outputStreams.get(tag.getRecipientAccount());
        Log.d("packetwriter","entry "+tag.toXml());
        if(out!=null){

            String str = tag.toXml();
            out.write(tag.toXml());
            Log.d("packetwriter","streamfound " +str );
            out.flush();
            if(out.checkError()){
                String id = tag.getAttribute("id");
                if(id!=null)
                    PacketStatusManager.getInstance().setFailure(id);
            }
            if(tag.getTagId()!=null && tag.getTagId().equals("streamclose")){
                AccountManager.getInstance().getAccount(tag.getRecipientAccount()).freeResources();
            }
        }
    }
    @Override
    public void execute() {
        Log.d("Service Thread : ", "I am PacketWriter");
        //write("<presence/>");
        while(true){
            if(!list.isEmpty()){
                Tag tag = list.remove(0);
                //Log.d("PacketWriter",tag.toXml());
                write(tag);
            }
        }
    }

    public static void addStream(PrintWriter out , String account){
        outputStreams.put(account,out);
    }
    public static boolean removeStream(String account){
        if(outputStreams.containsKey(account)){
            outputStreams.remove(account);
            return true;
        }
        return false;
    }


}
