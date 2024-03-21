/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AD
 */
public class UserDAO extends MyDAO {

    public boolean checkEmailExits(String email) {
        try {
            String sql = "SELECT * FROM [User] u WHERE u.email=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public User getUserById(int id) {
        User u = new User();

        try {
            String sql = "SELECT * FROM [User] u WHERE u.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setFullName(rs.getString("fullName"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));
                u.setRole(rs.getString("role"));
                u.setMail(rs.getString("mail"));
                u.setCartId(rs.getInt("cartId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void changePassword(String mail, String password) {
        try {
            String sql = "UPDATE [User] SET password = ? WHERE email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNString(1, password);
            stm.setNString(2, mail);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Account> getAllUser() {
        List<Account> accountList = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select u.id, u.fullName, u.phone, u.email, u.[role], u.[status] from [User] u";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setFullname(rs.getString("fullName"));
                a.setPhone(rs.getString("phone"));
                a.setMail(rs.getString("email"));
                a.setRole(rs.getString("role"));
                a.setStatus(rs.getBoolean("status"));
                accountList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return accountList;
    }

    public User getUserDetailById(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select u.id, u.avatar, u.fullName, u.email, u.phone, u.[role], u.[status], u.gender \n"
                    + "from [User] u\n"
                    + "where u.id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User();
                a.setId(rs.getInt("id"));
                a.setAvatar(rs.getString("avatar"));
                a.setFullName(rs.getString("fullName"));
                a.setPhone(rs.getString("phone"));
                a.setMail(rs.getString("email"));
                a.setRole(rs.getString("role"));
                a.setStatus(rs.getBoolean("status"));
                a.setGender(rs.getString("gender"));
                return a;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean updateUserDetail(int id, boolean status, String role) {
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE [SWP391_Gr1].[dbo].[User] \n"
                    + "SET [status] = ?, [role] = ?  \n"
                    + "WHERE id = ?";
            stm = connection.prepareStatement(sql);

            stm.setBoolean(1, status);
            stm.setString(2, role);
            stm.setInt(3, id);

            int rowsAffected = stm.executeUpdate();
            // Check if the update was successful
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            // Close resources (stm, rs, etc.) in a finally block
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return false;
    }

    public int addNewUser(User user) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO [SWP391_Gr1].[dbo].[User]"
                    + " (fullName, email, phone, gender, [role], [status])"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getFullName());
            stm.setString(2, user.getMail());
            stm.setString(3, user.getPhone());
            stm.setString(4, user.getGender());
            stm.setString(5, user.getRole());
            stm.setBoolean(6, user.isStatus());

            stm.executeUpdate();

            // Retrieve the generated user ID
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public boolean deleteUserByID(int id) {
        PreparedStatement stm = null;

        try {
            String sql = "DELETE FROM [SWP391_Gr1].[dbo].[User] WHERE [id] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            int rowsAffected = stm.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        int userIdToUpdate = 2;
        boolean newStatus = false; // Change this to the desired boolean value
        boolean updateResult = dao.updateUserDetail(userIdToUpdate, newStatus, "user");

        if (updateResult) {
            System.out.println("User details updated successfully.");
        } else {
            System.out.println("Failed to update user details.");
        }
    }

}
