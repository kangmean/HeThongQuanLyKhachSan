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
     public boolean themHoaDon(int maDatPhong, Date ngayLap, double tongTien) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "INSERT INTO HoaDon(MaDatPhong, NgayLap, TongTien) VALUES(?,?,?)";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maDatPhong);
          pst.setDate(2, ngayLap);
          pst.setDouble(3, tongTien);
          return pst.executeUpdate() > 0;
     }

    // Lấy hóa đơn theo đặt phòng
     public ResultSet getHoaDonByDatPhong(int maDatPhong) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "SELECT * FROM HoaDon WHERE MaDatPhong = ?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maDatPhong);
          return pst.executeQuery();
     }
}
