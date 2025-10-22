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
          String sql = "SELECT * FROM Khach";
          PreparedStatement pst = con.prepareStatement(sql);
          return pst.executeQuery();
     }

     // Thêm khách hàng
     public boolean themKhachHang(String ten, String cmnd, String sdt, int tuoi, int namSinh, String queQuan, String diaChi) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "INSERT INTO Khach(TenKH, CMND, SDT, Tuoi, NamSinh, QueQuan, DiaChi) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, ten);
        pst.setString(2, cmnd);
        pst.setString(3, sdt);
        pst.setInt(4, tuoi);
        pst.setInt(5, namSinh);
        pst.setString(6, queQuan);
        pst.setString(7, diaChi);
        return pst.executeUpdate() > 0;
    }

     // Sửa thông tin khách hàng
     public boolean suaKhachHang(int maKH, String ten, String cmnd, String sdt, int tuoi, int namSinh, String queQuan, String diaChi) throws SQLException {
          Connection con = connHelper.getConnection();
          String sql = "UPDATE Khach SET TenKH=?, CMND=?, SDT=?, Tuoi=?, NamSinh=?, QueQuan=?, DiaChi=? WHERE MaKH=?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setString(1, ten);
          pst.setString(2, cmnd);
          pst.setString(3, sdt);
          pst.setInt(4, tuoi);
          pst.setInt(5, namSinh);
          pst.setString(6, queQuan);
          pst.setString(7, diaChi);
          pst.setInt(8, maKH);
          return pst.executeUpdate() > 0;
     }

    // Xóa khách
     public boolean xoaKhachHang(int maKH) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "DELETE FROM Khach WHERE MaKH=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maKH);
        return pst.executeUpdate() > 0;
    }
}
