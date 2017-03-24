/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;

/**
 *
 * @author JUHI AGRAWAL
 */
public class CallSend extends Thread {
    FirstPage fp;
    public CallSend(FirstPage ob){
       fp=ob;
   }
    public void run(){
        Send ob=new Send(fp);
        String []args={};
        ob.main(args);
       
    }
}
