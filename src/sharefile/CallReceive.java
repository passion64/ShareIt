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
public class CallReceive extends Thread {
    public void run(){
        Recieve ob = new Recieve();
        ob.setVisible(true);
        String []args={};
        ob.main(args);
        
    }
}
