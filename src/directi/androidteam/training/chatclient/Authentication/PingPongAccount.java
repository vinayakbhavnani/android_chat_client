package directi.androidteam.training.chatclient.Authentication;

import android.util.Log;
import directi.androidteam.training.chatclient.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 5/10/12
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class PingPongAccount extends Account {
    public PingPongAccount(String username , String passwd){
        this.accountUid =username;
        this.serviceIcon = R.drawable.pingpong_icon;
        //this.serverURL = "10.10.100.163";
        this.serverPort = 5222;
        this.service="pingpong";
        this.passwd = passwd;
        this.xmppLogin = new PingPongLogin(username,passwd);
        loginStatus = LoginStatus.OFFLINE;
    }

    @Override
    public Socket createSocket() throws IOException{
        fetchServerURL(accountUid);
        Socket sock = new Socket(this.serverURL,this.serverPort);
        sock.setSoTimeout(0);
        sock.setKeepAlive(true);
        return sock;
    }

    private String fetchServerURL(String username){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://lookup1.staging.us-east-1d.aws.talk.to:9000/lookup?service-type=tfd-messaging-handler&entity="+username));
            HttpResponse response = client.execute(request);
            Log.d("httpget",response.toString());
            in = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            String page = sb.toString();
            Log.d("httpresponse",page);
            in.close();

            JSONObject jsonObject = new JSONObject(page);
            String  jsoninfo = (String)jsonObject.get("info");
            JSONObject info = new JSONObject(jsoninfo);
            String serveraddr = (String)info.get("tcp-addr");
            this.serverURL = serveraddr.split(":")[0];
            Log.d("serverurl",serverURL);
        }
        catch (Exception e){Log.d("httpget",e.getStackTrace().toString());}
        return null;
    }
}
