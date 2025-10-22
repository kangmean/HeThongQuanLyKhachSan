/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hethongquanlykhachsan;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class DatPhongDao {
     SqlConnect connHelper = new SqlConnect();

    // Lấy tất cả đặt phòng
     public ResultSet getAllDatPhong() throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "SELECT * FROM DatPhong";
          PreparedStatement pst = con.prepareStatement(sql);
          return pst.executeQuery();
     }

    // Thêm đặt phòng
     public boolean themDatPhong(int maKhach, int maPhong, String ngayDat, String ngayNhan, String ngayTra, String trangThai) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "INSERT INTO DatPhong(MaKhachHang, MaPhong, NgayDat, NgayNhan, NgayTra, TrangThai) VALUES(?,?,?,?,?,?)";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maKhach);
          pst.setInt(2, maPhong);
          pst.setString(3, ngayDat);
          pst.setString(4, ngayNhan);
          pst.setString(5, ngayTra);
          pst.setString(6, trangThai);
          return pst.executeUpdate() > 0;
     }

    // Sửa đặt phòng
     public boolean suaDatPhong(int maDatPhong, int maPhong, int maKhach, String ngayDat, String ngayNhan, String ngayTra, String trangThai) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "UPDATE DatPhong SET MaPhong=?, MaKhachHang=?, NgayDat=?, NgayNhan=?, NgayTra=?, TrangThai=? WHERE MaDatPhong=?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maPhong);
          pst.setInt(2, maKhach);
          pst.setString(3, ngayDat);
          pst.setString(4, ngayNhan);
          pst.setString(5, ngayTra);
          pst.setString(6, trangThai);
          pst.setInt(7, maDatPhong);
          return pst.executeUpdate() > 0;
     }

    // Xóa đặt phòng
     public boolean xoaDatPhong(int maDatPhong) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "DELETE FROM DatPhong WHERE MaDatPhong = ?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1, maDatPhong);
          return pst.executeUpdate() > 0;
     }
     
     public boolean capNhatTrangThai(int maPhong, String trangThai) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "UPDATE Phong SET TrangThai = ? WHERE MaPhong = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, trangThai);
        pst.setInt(2, maPhong);
        return pst.executeUpdate() > 0;
    }
}
