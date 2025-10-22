/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hethongquanlykhachsan;

import java.sql.*;

public class PhongDao {
     SqlConnect connHelper = new SqlConnect();

    // Lấy danh sách tất cả phòng
     public ResultSet getAllPhong() throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "SELECT * FROM Phong";
          PreparedStatement pst = con.prepareStatement(sql);
          return pst.executeQuery();
     }

    // Lấy phòng theo trạng thái
     public boolean themPhong(String tenPhong, String trangThai) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "INSERT INTO Phong(TenPhong, TrangThai) VALUES(?,?)";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setString(1, tenPhong);
          pst.setString(2, trangThai);
          return pst.executeUpdate() > 0;
     }

    // Sửa thông tin phòng
     public boolean suaPhong(int maPhong, String tenPhong, String trangThai) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "UPDATE Phong SET TenPhong = ?, TrangThai = ? WHERE MaPhong = ?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setString(1, tenPhong);
          pst.setString(2, trangThai);
          pst.setInt(3, maPhong);
          return pst.executeUpdate() > 0;
     }

    // Xóa phòng
     public boolean xoaPhong(int maPhong) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "DELETE FROM Phong WHERE MaPhong = ?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maPhong);
          return pst.executeUpdate() > 0;
     }
     
     public boolean capNhatTrangThai(int maPhong, String trangThai) {
        try {
            Connection con = connHelper.getConnection();
            String sql = "UPDATE Phong SET TrangThai = ? WHERE MaPhong = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, trangThai);
            pst.setInt(2, maPhong);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
