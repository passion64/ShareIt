/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Muskan
 */
public class Send extends javax.swing.JFrame {

    /**
     * Creates new form Send
     */
    static String ip;
    static int subnet;
    static Socket sock;
    static Socket fileSock;
    static DataInputStream din;
    static DataOutputStream dout;
    FirstPage fp;
    public Send() {
        initComponents();
        
    }
    public Send(FirstPage ob) {
        initComponents();
        fp=ob;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Receivers = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Receivers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Receivers);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(85, 85, 85))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
       // this.dispose();
        //fp.setVisible(true);
        
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
   
    
    public static String getNetworkAddress(String ip,int subnet){
        int a1[]=new int[5];
        StringTokenizer st = new StringTokenizer(ip,".");
                    
//        System.out.println(arr[0]);
        int in=0;
        while(st.hasMoreTokens()){  
         System.out.println("sdjfkslkdf");
            a1[in++] = Integer.getInteger(st.nextToken());
            
           
            
           
}  
        
        /*a1[0] = Integer.getInteger(arr[0]);
        a1[1] = Integer.getInteger(arr[1]);
        a1[2] = Integer.getInteger(arr[2]);
        a1[3] = Integer.getInteger(arr[3]);
        */
        int diff = subnet;
        int d1 = (diff/8);
        
        String s1 = "";
        int i;
        for( i=0;i<d1;i++)
        {
            s1 = s1 + (Integer.toString(a1[i]))+ "." ;
            
        }
        
        for(;i<=3;i++)
        {
            s1 = s1 +"0." ;
            
        }
       // s1 = s1+ "0";
        return s1;
        
        
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Send().setVisible(true);
            }
        });
        try{
            InetAddress localHost = Inet4Address.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
                     
            ip = localHost.getHostAddress();
            subnet = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
            System.out.println(ip + " "+subnet);
            
            //dekhenge isko shayad :P 
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        //Initializing table
        DefaultTableModel t=(DefaultTableModel)Receivers.getModel();
        t.setRowCount(0);
        
        String [][] userList =new String[20][2];
        //add listener on list
        
        Receivers.addMouseListener(new MouseAdapter(){
            
         public void mouseClicked(MouseEvent me)
         {
              if(me.getClickCount()==2)
              {
                  JTable target = (JTable)me.getSource();
                  int row = target.getSelectedRow();
 
                  String un = userList[row][0];
                  String ip = userList[row][1];
                  System.out.println("Table select"+ip);
                  
                  BrowseFile ob=new BrowseFile(ip,un);
                  ob.setVisible(true);
                  
                  
              }
             
             
         }
        
   
    
    });  
            String partialsubnet = ip.substring(0, ip.lastIndexOf('.'));
            partialsubnet = partialsubnet.substring(0,partialsubnet.lastIndexOf('.'));
            
            System.out.println(partialsubnet);
            int ind = 0;
            for(int j=205;j<=255;j++){
            for (int i = 1; i < 255; i++)
        {
            String host = partialsubnet + "."  +j+"."+ i;
          
            try{
                
            sock = new Socket();
            sock.connect(new InetSocketAddress(host,4444),50);
            
            System.out.println("socket created"+ host);
            t.addRow(new String[]{});  
            dout = new DataOutputStream(sock.getOutputStream());
           dout.writeUTF("accept conn");
           dout.flush();
           String str;
            din = new DataInputStream(sock.getInputStream());
           while(true){
               if(din.available()>0){
                    str=(String)din.readUTF();
                    break;
               }
           }
            System.out.println("recieved name is "+ str);
            userList[ind][0]=str;
            userList[ind][1]=host;
            Receivers.getModel().setValueAt(str,  ind++, 0);
       
           
            }
            catch(SocketTimeoutException se){}
            catch(SocketException se){}
            catch(Exception e)
        {
            e.printStackTrace();
        }
            finally{
                try{
                sock.close();}
                catch(Exception e){}
              
            }
            
        }
            System.out.println("check " + j);
            
            
        }
            
         /*  try{
           //sock = new Socket("192.168.122.2",4444);
           sock = new Socket();
           
                 sock.connect(new InetSocketAddress("192.168.122.1",4444),20);
           System.out.println("done");
           }
           catch(Exception e){System.out.println("nahi");}
           
           */ 
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTable Receivers;
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
