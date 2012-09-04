package directi.androidteam.training.lib.TCPHandler;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 31/8/12
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class customConnection {
    /*Socket tcpsocket;
    DataOutputStream out;
    DataInputStream in;
    public customConnection(){
        try {
            tcpsocket = new Socket("gmail.com",5552);
            OutputStream output = tcpsocket.getOutputStream();
            out = new DataOutputStream(output);
            InputStream input = tcpsocket.getInputStream();
            in = new DataInputStream(input);
            out.writeBytes("<stream></stream>");

            while (tcpsocket.isConnected()){
            if(in.available()>0){
                byte[] buffer = new byte[in.available()];
                int numbytes = in.read(buffer);
                Log.d("message",new String(buffer));
            }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    } */
}
