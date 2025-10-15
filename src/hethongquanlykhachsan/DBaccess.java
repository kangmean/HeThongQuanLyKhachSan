/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hethongquanlykhachsan;

import java.sql.*;

public class DBaccess {
     
     private Connection con;
     private Statement stmt;
     
     public DBaccess(){
          try{
               SqlConnect myCon = new SqlConnect(); 
               con = myCon.getConnection();
               if(con != null){
                    stmt = con.createStatement();
               }else{
                    System.out.println("Không thể kết nối đến CSDL");
               }
          }catch(Exception e){
               System.out.println("Lỗi khi khởi tạo DBaccess" + e.getMessage());
          }
     }
     
     public int Update(String sql){
          if(stmt == null) return -1;
          try{
               System.out.println("Thực thi lệnh" + sql);
               return stmt.executeUpdate(sql);
          }catch(SQLException e){
               System.out.println("Lỗi khi thực thi Update: " + e.getMessage());
               return -1;
          }
     } 
     
     public ResultSet Query(String sql){
          if(stmt == null) return null;
          try{
               System.out.println("Thực thi truy vấn: " + sql);
               return stmt.executeQuery(sql);
          }catch(SQLException e){
               System.out.println("Lỗi khi thực thi Query: " + e.getMessage());
               return null;
          }
     }
     
     public void close(){
          try{
               if(stmt != null && !stmt.isClosed()) stmt.close();
               if(con != null && !con.isClosed()) con.close();
               System.out.println("Đã đóng kết nối cơ sở dữ liệu.");
          }catch(SQLException e){
               System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
          }
     }
}
