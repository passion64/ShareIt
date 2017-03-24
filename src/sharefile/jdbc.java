/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharefile;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seema
 */
public class jdbc {

    /**
     * @param args the command line arguments
     */
   

private String url = "jdbc:mysql://localhost:3306/fileshare";
private String driver = "com.mysql.jdbc.Driver";
private String userName = "root";
private String password = "";
private Connection con = null;
private Statement stmt = null;
public jdbc(){
   con = this.getConnection();
}
public Connection getConnection() {

     try {
         Class.forName(driver);
         if(con == null){
             con = DriverManager.getConnection(url,userName,password);
         }
         System.out.print("Connection estd");
     }catch (Exception e) {
         System.out.print("Error : " +e.getMessage());
    }
     return con;
}
public void execute(String sql)
{
    try
    {
     System.out.println("Creating statement...");
      stmt = con.createStatement();
      
      //sql = "SELECT id, first, last, age FROM Employees";
      stmt.executeUpdate(sql);
      stmt.close();
                    
    }
    catch(SQLException s)
    {
        s.printStackTrace();
    }
}
public void search(String sql,ResultSet[]rset1)
{
    ResultSet rs = null;
    try
    {
       stmt=con.createStatement();
       
        rs=stmt.executeQuery(sql);
       rset1[0]=rs;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
        //return rs;
}


      
}

