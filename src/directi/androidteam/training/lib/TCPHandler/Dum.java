package directi.androidteam.training.lib.TCPHandler;

import android.os.AsyncTask;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/31/12
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dum extends AsyncTask<Integer,Integer,Long>{
    @Override
    public Long doInBackground(Integer... integers) {
        String initiate_conn="<stream:stream to=\"gmail.com\" version=\"1.0\" xmlns=\"jabber:client\" xmlns:stream=\"http://etherx.jabber.org/streams\">";
        String start_tls="<starttls xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>";
        String string3 = "<stream:stream to=\"gmail.com\" xml:lang=\"en\" version=\"1.0\" xmlns:stream=\"http://etherx.jabber.org/streams\" xmlns=\"jabber:client\">";

        try {
        Socket connection = new Socket("talk.google.com", 5222);

  DataInputStream input = new DataInputStream(connection.getInputStream());
  BufferedReader d = new BufferedReader(new InputStreamReader(input,"UTF-8"));
  BufferedWriter to_server = new BufferedWriter(
          new OutputStreamWriter(connection.getOutputStream(),"UTF-8")
  );
  String responseLine="";
  to_server.write(initiate_conn);
  to_server.flush();
  int in;
  while(!(responseLine.contains("</stream:features>")))
  {
      responseLine += (char)d.read();
  }
  System.out.println("Server: " + responseLine);
  to_server.write(start_tls);
  to_server.flush();
  responseLine="";
  while(!(responseLine.contains("<proceed xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>")))
      responseLine += (char)d.read();
  System.out.println("Server: " + responseLine);
    }
    catch (UnknownHostException e) {
        e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }
        return new Long(1);

    }
    protected void onProgressUpdate(Integer... progress) {
}
    protected void onPostExecute(Long result) {

    }
}
