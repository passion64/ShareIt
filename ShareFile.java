/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;

/**
 *
 * @author Muskan
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sharefile.jdbc;
public class ShareFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            jdbc j=new jdbc();
            //Connection con = j.getConnection();
            String query="Select * from userinfo";
            ResultSet[] rs=new ResultSet[50];
            j.search(query,rs);
                    if(!rs[0].next()){
            //if (!rs.isBeforeFirst() ) { 
                SignUpPage ob=new SignUpPage();
                ob.setVisible(true);
            }
            else{
                String username = rs[0].getString("username");
                FirstPage ob=new FirstPage(username);
                ob.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShareFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
