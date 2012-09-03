package directi.androidteam.training.lib.TCPHandler;

//import org.jivesoftware.smack.*;
//import org.jivesoftware.smack.packet.Message;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 31/8/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class smackLogin {
    /*
    String pass = "androidchat";
    public void execute(){
        ConnectionConfiguration config = new ConnectionConfiguration("talk.google.com", 5222,"gmail.com");
        //config.setCompressionEnabled(true);

        //config.setSASLAuthenticationEnabled(true);

        Connection connection = new CustomXmpp(config);
// Connect to the server
        try {
            connection.connect();
            connection.login("brian.gingers@gmail.com",pass);

        } catch (XMPPException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Message message = new Message("vinayak.bhavnani@gmail.com",Message.Type.chat);
        message.setBody("newtest");
        //connection.sendPacket(message);
        Log.d("packetxml",message.toXML());
        final CustomXmpp xmpp = (CustomXmpp)connection;
        String str = "<message\n" +
                "    from='vinayak.bhavnani@gmail.com'\n" +
                "    id="+xmpp.getConnectionID()+"\n" +
                "    to='vinayakhappening@gmail.com'\n" +
                "    type='chat'\n" +
                "    xml:lang='en'>\n" +
                "  <body>Wherefore art thou, Romeo?</body>\n" +
                "</message>";
        final String str1 = "<message to=\"vinayak.bhavnani@gmail.com\" type=\"chat\"><body>testhi</body></message>";

        final XMLHelper xml = new XMLHelper();
        String xmlstring = xml.buildPacket(new MessageStanza("vinayak.bhavnani@gmail.com","talk.to","vinayak.bhavnani@gmail.com").getTag());
        xmpp.writexmlMessage(xmlstring);
        Thread t = new Thread(){
            public void run(){
               // xml.tearxmlPacket(xmpp.getreader());
                Socket socket = xmpp.getSock();

                try {
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.write(str1.getBytes());
                    out.flush();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    while (socket.isConnected()){
                        Integer integer = in.read();

                        Log.d("getmesss", integer.toString());

                        if(in.available()!=0){
                            Log.d("newmess","newmess");
                            byte[] buf = new byte[in.read()];
                            in.read(buf);
                            Log.d("read",new String(buf));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }  ;
        t.start();
       // Log.d("rmess",xml.buildPacket(xml.tearxmlPacket(xmpp.getreader())));
        Log.d("packetxml",xmlstring);

// Log into the server

    } */
}
