/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sharefile.jdbc;
/**
 *
 * @author JUHI AGRAWAL
 */

public class SendFile extends Thread {
    
    Socket sock;
    String ip;
    String loc;
    FileInputStream finp;
    DataOutputStream dout;
    
    public SendFile(String i,String l){
        ip=i;
        loc=l;
    }
    
    public void run(){
        
        try{
            //send file ka code
            sock = new Socket(ip,4444);
            
            dout = new   DataOutputStream(sock.getOutputStream());
            dout.writeUTF("recieve file "+loc);
            dout.flush();
            
            File f = new File(loc);
            
            finp = new FileInputStream(f);
            int count;
            byte[] bytes = new byte[16 * 1024];
            while ((count = finp.read(bytes)) > 0) {
                dout.write(bytes, 0, count);
            }
            System.out.println("File sent");

            finp.close();   
        
           // jdbc j =new jdbc();
           // String query="Insert into "
        
        
        }
        catch(Exception e)
        {}
        finally{
            try {
                sock.close();
            } catch (IOException ex) {
                Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
    }
}
