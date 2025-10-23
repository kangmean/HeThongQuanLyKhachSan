package hethongquanlykhachsan;

import java.sql.*;

public class PhongDao {
    SqlConnect connHelper = new SqlConnect();

    // Lấy danh sách tất cả phòng
    public ResultSet getAllPhong() throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "SELECT * FROM Phong ORDER BY MaPhong";
        PreparedStatement pst = con.prepareStatement(sql);
        return pst.executeQuery();
    }

    // Thêm phòng mới (phải có LoaiPhong)
    public boolean themPhong(String loaiPhong) throws SQLException {
        Connection con = connHelper.getConnection();
        // Chỉ cần truyền LoaiPhong, trigger sẽ tự động tạo TenPhong
        String sql = "INSERT INTO Phong (LoaiPhong) VALUES (?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, loaiPhong);
        return pst.executeUpdate() > 0;
    }

    // Thêm phòng với đầy đủ thông tin
    public boolean themPhongDayDu(String tenPhong, String loaiPhong, String trangThai) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "INSERT INTO Phong (TenPhong, LoaiPhong, TrangThai) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tenPhong);
        pst.setString(2, loaiPhong);
        pst.setString(3, trangThai);
        return pst.executeUpdate() > 0;
    }

    // Sửa thông tin phòng
    public boolean suaPhong(int maPhong, String tenPhong, String loaiPhong, String trangThai) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "UPDATE Phong SET TenPhong = ?, LoaiPhong = ?, TrangThai = ? WHERE MaPhong = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tenPhong);
        pst.setString(2, loaiPhong);
        pst.setString(3, trangThai);
        pst.setInt(4, maPhong);
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

    // Cập nhật trạng thái phòng
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

    // Lấy phòng theo mã
    public ResultSet getPhongByMa(int maPhong) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "SELECT * FROM Phong WHERE MaPhong = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maPhong);
        return pst.executeQuery();
    }

    // Lấy phòng theo trạng thái
    public ResultSet getPhongByTrangThai(String trangThai) throws SQLException {
        Connection con = connHelper.getConnection();
        String sql = "SELECT * FROM Phong WHERE TrangThai = ? ORDER BY MaPhong";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, trangThai);
        return pst.executeQuery();
    }
    
    // Phương thức đơn giản hơn - sửa lỗi window functions
public boolean capNhatTenPhongChoAll() {
    try {
        Connection con = connHelper.getConnection();
        
        // Cập nhật phòng đơn
        String sqlDon = "UPDATE p SET TenPhong = 'PĐ' + RIGHT('000' + CAST(d.STT AS NVARCHAR(3)), 3) " +
                       "FROM Phong p " +
                       "INNER JOIN ( " +
                       "    SELECT MaPhong, ROW_NUMBER() OVER (ORDER BY MaPhong) AS STT " +
                       "    FROM Phong WHERE LoaiPhong = N'Đơn' " +
                       ") d ON p.MaPhong = d.MaPhong " +
                       "WHERE p.TenPhong IS NULL OR p.TenPhong = ''";
        
        // Cập nhật phòng đôi  
        String sqlDoi = "UPDATE p SET TenPhong = 'PĐoi' + RIGHT('000' + CAST(d.STT AS NVARCHAR(3)), 3) " +
                       "FROM Phong p " +
                       "INNER JOIN ( " +
                       "    SELECT MaPhong, ROW_NUMBER() OVER (ORDER BY MaPhong) AS STT " +
                       "    FROM Phong WHERE LoaiPhong = N'Đôi' " +
                       ") d ON p.MaPhong = d.MaPhong " +
                       "WHERE p.TenPhong IS NULL OR p.TenPhong = ''";
        
        PreparedStatement pstDon = con.prepareStatement(sqlDon);
        PreparedStatement pstDoi = con.prepareStatement(sqlDoi);
        
        int resultDon = pstDon.executeUpdate();
        int resultDoi = pstDoi.executeUpdate();
        
        System.out.println("Đã cập nhật " + resultDon + " phòng đơn và " + resultDoi + " phòng đôi");
        return true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}