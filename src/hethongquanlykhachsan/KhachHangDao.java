/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hethongquanlykhachsan;

import java.sql.*;
public class KhachHangDao {
     SqlConnect connHelper = new SqlConnect();

    // Lấy tất cả khách hàng
     public ResultSet getAllKhachHang() throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "SELECT * FROM KhachHang";
          PreparedStatement pst = con.prepareStatement(sql);
          return pst.executeQuery();
     }

     // Thêm khách hàng mới
     public boolean themKhachHang(String tenKH, String CMND, String SDT, String email) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "INSERT INTO KhachHang(TenKH, CMND, SDT) VALUES(?,?,?)";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setString(1, tenKH);
          pst.setString(2, CMND);
          pst.setString(3, SDT);
          return pst.executeUpdate() > 0;
     }

     // Cập nhật thông tin khách
     public boolean capNhatKhachHang(int maKH, String tenKH, String CMND, String SDT) throws SQLException {
         Connection con = connHelper.getConnection();
         String sql = "UPDATE KhachHang SET TenKH=?, CMND=?, SDT=? WHERE MaKH=?";
         PreparedStatement pst = con.prepareStatement(sql);
         pst.setString(1, tenKH);
         pst.setString(2, CMND);
         pst.setString(3, SDT);
         pst.setInt(4, maKH);
         return pst.executeUpdate() > 0;
     }

    // Xóa khách
     public boolean xoaKhachHang(int maKH) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "DELETE FROM KhachHang WHERE MaKH=?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maKH);
          return pst.executeUpdate() > 0;
     }
}
