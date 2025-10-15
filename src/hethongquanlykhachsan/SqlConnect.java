/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hethongquanlykhachsan;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class SqlConnect {
     public Connection getConnection(){
          try{
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String URL = "jdbc:sqlserver://localhost:1234;Database=DoAnQLKS;user=doan;password=123";
                    Connection con = DriverManager.getConnection(URL);
                    return con;
          }catch(Exception e){
               JOptionPane.showMessageDialog(null, e.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
               return null;
          }
     }
}
