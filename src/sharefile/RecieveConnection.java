/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    File f;
    
    public RecieveConnection(Socket s)
    {
        clientsocket = s;
    }
    public void run()
    {
        String str;
       try{ 
           din = new DataInputStream(clientsocket.getInputStream());
            
           while(true){
            if(din.available()>0)   {
                str=(String)din.readUTF();
                break;
            }
           }
           System.out.println(str);
           if(str.compareTo("accept conn")==0){
           dout = new DataOutputStream(clientsocket.getOutputStream());
           
           dout.writeUTF(uname);
           dout.flush();
           
           sleep(5);
           clientsocket.close();
           }
           else if(str.contains("recieve file")==true){
          // else if(str.compareTo("recieve file")==0){
               Thread t=new Thread(new ReceiveFile());
               String s = str.substring(11);
                f = new File("E:"+File.separator+"ShareFiles"+File.separator+s.substring(s.lastIndexOf(File.separator)+1));
                System.out.println("download lcatin E:"+File.separator+"ShareFiles"+File.separator+s.substring(s.lastIndexOf(File.separator)+1));
                
               //File f = new File("E://");
               t.start();
           }
           
       }
       catch(Exception e){
           e.printStackTrace();
       }
        
        
        
    }
    class ReceiveFile extends Thread{
    
    
        public void run(){
            FileOutputStream fout = null;
            try{
                //Receive file karne wala code
                
                fout = new FileOutputStream(f);
                byte[] bytes = new byte[16*1024];
                int count;
                try{
                    while ((count = din.read(bytes)) > 0) {
                        fout.write(bytes, 0, count);
                    }
                    fout.close();
                    System.out.println("recieved file");
                    
                }
                catch(Exception e){}
                
            }
            catch(FileNotFoundException ex){Logger.getLogger(RecieveConnection.class.getName()).log(Level.SEVERE, null, ex);
} finally {
                try {
                    fout.close();
                } catch (IOException ex) {
                    Logger.getLogger(RecieveConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
            
            
            
        }
    }
    
    
}

