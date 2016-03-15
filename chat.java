

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatMain extends javax.swing.JFrame {
    String username, serverIP = "192.168.43.187";
    int Port = 5000;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    ArrayList<String> userList = new ArrayList();
    Boolean isConnected = false;


    /** Creates new form Chat */
    public ChatMain() {
        initComponents();
    }

    public class IncomingReader implements Runnable{

        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {

                    data = stream.split(":");

                     if (data[2].equals(chat)) {

                        chatTextArea.append(data[0] + ": " + data[1] + "\n");
                        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());

                    } else if (data[2].equals(connect)){

                        chatTextArea.removeAll();
                        userAdd(data[0]);

                    } else if (data[2].equals(disconnect)) {


                        userRemove(data[0]);

                    } else if (data[2].equals(done)) {


                        usersList.setText("");
                        writeUsers();
                        userList.clear();

                    }
                 
                }
           }catch(Exception ex) {
           }
        }
    }

    public void ListenThread() {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }

    public void userAdd(String data) {
         userList.add(data);

     }

    public void userRemove(String data) {
         chatTextArea.append(data + " has disconnected.\n");

     }

    public void writeUsers() {
         String[] tempList = new String[(userList.size())];
         userList.toArray(tempList);
         for (String token:tempList) {

             usersList.append(token + "\n");

         }

     }

    public void sendDisconnect() {

       String bye = (username + ": :Disconnect");
        try{
            writer.println(bye); // Sends server the disconnect signal.
            writer.flush(); // flushes the buffer
        } catch (Exception e) {
            chatTextArea.append("Could not send Disconnect message.\n");
        }

      }

    public void Disconnect() {

        try {
               chatTextArea.append("Disconnected.\n");
               sock.close();
        } catch(Exception ex) {
               chatTextArea.append("Failed to disconnect. \n");
        }
        isConnected = false;
        usernameField.setEditable(true);
        usersList.setText("");

}
