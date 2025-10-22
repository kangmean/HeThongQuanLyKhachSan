package hethongquanlykhachsan;

import java.sql.*;

public class UserDAO {

    private DBaccess db;

    public UserDAO() {
        db = new DBaccess();
    }

    // Thêm tài khoản mới
     public boolean insert(String username, String password, String email) {
          try{
               String sql = "INSERT INTO DangNhap(USERNAME, PASS, Email) VALUES('" 
                    + username + "', '" + password + "', '" + email + "')";
               int kq = db.Update(sql);
               return kq > 0;
          } catch (Exception e) {
               System.out.println("Lỗi insert: " + e.getMessage());
               return false;
          }
     }

    // Kiểm tra email có tồn tại không
     public boolean existsByEmail(String email) {
          try {
               String sql = "SELECT * FROM DangNhap WHERE Email='" + email + "'";
               ResultSet rs = db.Query(sql);
               boolean found = rs.next();
               rs.close();
               return found;
          } catch (Exception e) {
               System.out.println("Lỗi existsByEmail: " + e.getMessage());
               return false;
          }
     }

    // Cập nhật mật khẩu theo email
     public boolean updatePassword(String email, String newPassword) {
          try {
               String sql = "UPDATE DangNhap SET PASS='" + newPassword + "' WHERE Email='" + email + "'";
               int kq = db.Update(sql);
               return kq > 0;
          } catch (Exception e) {
               System.out.println("Lỗi updatePassword: " + e.getMessage());
               return false;
          }
     }

    // Đóng kết nối nếu cần
     public void close() {
         db.close();
     }
}
