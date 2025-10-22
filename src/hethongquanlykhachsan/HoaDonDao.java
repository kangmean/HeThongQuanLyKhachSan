/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hethongquanlykhachsan;

import java.sql.*;
public class HoaDonDao {
     SqlConnect connHelper = new SqlConnect();

    // Lấy tất cả hóa đơn
     public ResultSet getAllHoaDon() throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "SELECT * FROM HoaDon";
          PreparedStatement pst = con.prepareStatement(sql);
          return pst.executeQuery();
     }

    // Tạo hóa đơn
     public boolean themHoaDon(int maKH, int maPhong, String ngayLap, double tongTien) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "INSERT INTO HoaDon(MaKH, MaPhong, NgayLap, TongTien) VALUES(?,?,?,?)";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maKH);
          pst.setInt(2, maPhong);
          pst.setString(3, ngayLap);
          pst.setDouble(4, tongTien);
          return pst.executeUpdate() > 0;
      }

    // Lấy hóa đơn theo đặt phòng
     public boolean suaHoaDon(int maHD, int maKH, int maPhong, String ngayLap, double tongTien) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "UPDATE HoaDon SET MaKH=?, MaPhong=?, NgayLap=?, TongTien=? WHERE MaHD=?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maKH);
          pst.setInt(2, maPhong);
          pst.setString(3, ngayLap);
          pst.setDouble(4, tongTien);
          pst.setInt(5, maHD);
          return pst.executeUpdate() > 0;
      }
     
     public boolean xoaHoaDon(int maHD) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "DELETE FROM HoaDon WHERE MaHD=?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maHD);
          return pst.executeUpdate() > 0;
     }
}
