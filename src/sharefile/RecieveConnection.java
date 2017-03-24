/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;
import java.io.*;
import java.net.*;
import static sharefile.Send.din;
/**
 *
 * @author Muskan
 */
public class RecieveConnection extends Thread {
    
    static Socket clientsocket;
    DataOutputStream dout;
    DataInputStream din;
    String uname="abcd";    //yeh hatana h 
    
    public RecieveConnection(Socket s)
    {
        clientsocket = s;
    }
    @Override
    public void run()
    {
       try{ 
           din = new DataInputStream(clientsocket.getInputStream());
           String str=(String)din.readUTF();
           if(str.compareTo("accept conn")==0){
           
           dout = new DataOutputStream(clientsocket.getOutputStream());
           dout.writeUTF(uname);
           dout.flush();
           
           sleep(5);
           clientsocket.close();
           }
           
       }
       catch(Exception e){
           e.printStackTrace();
       }
        
        
        
    }
    
    
    
}
