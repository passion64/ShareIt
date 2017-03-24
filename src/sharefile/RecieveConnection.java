/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static sharefile.Send.din;
/**
 *
 * @author Muskan
 */
public class RecieveConnection extends Thread {
    
    static Socket clientsocket;
    DataOutputStream dout;
    DataInputStream din;
    String uname,sendname,s;    
    File f;
    String str;
    
    public RecieveConnection(Socket s)
    {
        clientsocket = s;
    }
    public void run()
    {
        
       try{
           jdbc j = new jdbc();
           ResultSet [] rs=new ResultSet[10];
           String sql="Select `username` from `userinfo`";
           j.search(sql, rs);
           while(rs[0].next()){
                 uname=rs[0].getString("username");
            }
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
               
               String s1 = str.substring(11);
               s = s1.substring(0,s1.lastIndexOf("$$")); // location
               sendname = s1.substring(s1.lastIndexOf("$$")+1);
               
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
                
                int total=0,count;
                try{
                    while ((count = din.read(bytes)) > 0) {
                        fout.write(bytes, 0, count);
                        total+=count;
                    }
                    fout.close();
                    System.out.println("recieved file");
                    JOptionPane jp=new JOptionPane("File Received");
                    jp.setVisible(true);
                    
                    //updating the database
                    
                   //String query= "Insert into `fileinfo 
                   String s = str.substring(11);
                   String filename = s.substring(s.lastIndexOf(File.separator)+1);
                   String type = filename.substring(filename.lastIndexOf('.')+1);
                   String query="Insert into `fileinfo`(`name`,`type`,`size`) values('"+filename+"','"+type+"','"+total+"')";
                   System.out.println(query);
                  jdbc j=new jdbc();
                   j.execute(query);
                   query="Select `id` from `fileinfo` where `name` like '"+filename+"' and `size`='"+total+"'";
            ResultSet []rset1= new ResultSet[10];
            j.search(query, rset1);
            int fileid=0;
            while(rset1[0].next()){
                 fileid=rset1[0].getInt("id");
            }

            String dt=java.time.LocalDate.now().toString();
           query="INSERT INTO `receive`(`fileid`, `rec_name`, `date`,`location`) VALUES ('"+fileid+"','"+sendname+"','"+dt+"','"+s+"')"; 
           System.out.println("sec query "+query);
            j.execute(query);
                    
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

