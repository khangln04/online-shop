package DataAccess;

import Models.Account;
import Models.Address;
import Models.ChangeHistory;
import Models.Feature;
import Models.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for handling Account-related operations.
 */
public class AccountDAO extends MyDAO {

    /**
     * Retrieves an Account from the database based on the provided email and
     * password.
     *
     * @param email The email of the account.
     * @param password The password of the account.
     * @return An Account object if found, or null otherwise.
     */
    public Account getAccount(String email, String password) {
        Account x = null;
        xSql = "SELECT [id]\n"
                + "      ,[fullName]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[status]\n"
                + "      ,[role]\n"
                + "      ,[wishlist_id]\n"
                + "      ,[cart_id]\n"
                + "  FROM [SWP391_Gr1].[dbo].[User]\n"
                + "  WHERE email=? AND password=?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                x = new Account();
                x.setId(rs.getInt("id"));
                x.setFullname(rs.getString("fullName"));
                x.setMail(rs.getString("email"));
                x.setPassword(rs.getString("password"));
                x.setPhone(rs.getString("phone"));
                x.setRole(rs.getString("role"));
                x.setCartId(rs.getInt("cart_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public boolean checkAccountExist(String email) {

        xSql = "SELECT * FROM [User] WHERE email=?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public int signUp(Account acc) {
        CartDao carDaO = new CartDao();
        WishListDao wishListDao = new WishListDao();
        AddressDAO adrDao = new AddressDAO();
        Address address = new Address();
        
        xSql = "INSERT INTO [dbo].[User] ([fullName], [email], [password], [phone], [role]) VALUES (?, ?, ?, ?, 'user');";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, acc.getFullname());
            ps.setString(2, acc.getMail());
            ps.setString(3, acc.getPassword());
            ps.setString(4, acc.getPhone());

            int result = ps.executeUpdate();

            if (result > 0) {
                int userIdNew = getAccountIdByEmail(acc.getMail());
                if (userIdNew > 0) {
                    address.setUserId(userIdNew);
                    address.setCity(acc.getCity());
                    address.setDistrict(acc.getDistrict());
                    address.setWard(acc.getWard());
                    address.setStreet(acc.getStreet());
                    
                    int idC = carDaO.createCart(userIdNew);
                    int idW = wishListDao.createWishList(userIdNew);
                    if (idC != 0 && idW != 0) {
                        adrDao.addNewAddress(address);
                        return updateAccount(idW, idC, userIdNew);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateAccount(int idW, int idC, int idAcount) {
        xSql = "UPDATE [dbo].[User]\n"
                + "   SET [wishlist_id] = ?\n"
                + "      ,[cart_id] = ?\n"
                + " WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, idW);
            ps.setInt(2, idC);
            ps.setInt(3, idAcount);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Address> getUserListAddres(int userId) {
        List<Address> listAddreses = new ArrayList<Address>();
        String sql = "Select id, city, district, ward, street, user_id from dbo.[Address] where user_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setWard(rs.getString("ward"));
                address.setStreet(rs.getString("street"));
                address.setDistrict(rs.getString("district"));
                address.setCity(rs.getString("city"));
                address.setUserId(rs.getInt("user_id"));
                listAddreses.add(address);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAddreses;
    }

    public Account getAccountById(int id) {
        Account x = null;
        xSql = "select u.[id],"
                + "u.[fullName],"
                + "u.[email],"
                + "u.[password],"
                + "u.[phone],"
                + "u.[status],"
                + "u.[role],"
                + "u.[cart_id],"
                + "u.[avatar],"
                + "a.[city],"
                + "a.[district],"
                + "a.[ward],"
                + "a.[street]"
                + " from [User] u join [Address] a on u.id = a.user_id\n"
                + "where u.id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                x = new Account();
                x.setId(rs.getInt("id"));
                x.setFullname(rs.getString("fullName"));
                x.setPassword(rs.getString("password"));
                x.setMail(rs.getString("email"));
                x.setPhone(rs.getString("phone"));
                x.setState(rs.getString("status"));
                x.setRole(rs.getString("role"));
                x.setCartId(rs.getInt("cart_id"));
                x.setCity(rs.getString("city"));
                x.setStreet(rs.getString("street"));
                x.setAvatar(rs.getString("avatar"));
                x.setStatus(rs.getBoolean("status"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public List<Account> getAllCustomer() {
        List<Account> accountList = new ArrayList<>();
        xSql = "SELECT u.[id],"
                + "u.[fullName],"
                + "u.[email],"
                + "u.[password],"
                + "u.[phone],"
                + "u.[status],"
                + "u.[role],"
                + "u.[cart_id],"
                + "u.[avatar],"
                + "a.[city],"
                + "a.[district],"
                + "a.[ward],"
                + "a.[street]"
                + " FROM [User] u JOIN [Address] a ON u.id = a.user_id"
                + " WHERE u.[role] = 'user'";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account x = new Account();
                x.setId(rs.getInt("id"));
                x.setFullname(rs.getString("fullName"));
                x.setPassword(rs.getString("password"));
                x.setMail(rs.getString("email"));
                x.setPhone(rs.getString("phone"));
                x.setState(rs.getString("status"));
                x.setRole(rs.getString("role"));
                x.setCartId(rs.getInt("cart_id"));
                x.setCity(rs.getString("city"));
                x.setStreet(rs.getString("street"));
                x.setAvatar(rs.getString("avatar"));
                x.setStatus(rs.getBoolean("status"));
                accountList.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accountList;
    }

    public void updateStatus(int accountId, boolean newStatus) {
        String updateSql = "UPDATE [User] SET [status] = ? WHERE [id] = ?";

        try {
            ps = con.prepareStatement(updateSql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, accountId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Status updated successfully for account with ID: " + accountId);
            } else {
                System.out.println("Failed to update status for account with ID: " + accountId);
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account changeAccount(int id, String fullname, String phone) {
        Account x = null;

        xSql = "update [User] set fullname = ?, phone = ? where id = ?\n";
        try {
            ps = con.prepareStatement(xSql);
            ps.setNString(1, fullname);
            ps.setNString(2, phone);
            ps.setInt(3, id);
            ps.executeUpdate();
            x = getAccountById(id);
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public Account changePass(int id, String oldpass, String newpass, String cfPass, String passEndcode) {
        Account x = null;

        x = getAccountById(id);

        if (!newpass.equals(cfPass)) {
            return null;
        }

        xSql = "update [User] set password = ? where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, passEndcode);
            ps.setInt(2, id);

            ps.executeUpdate();
            x = getAccountById(id);
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void changePassM(String mail, String passEndcode) {

        xSql = "update [User] set password = ? where email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, passEndcode);
            ps.setString(2, mail);

            ps.executeUpdate();
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account changeAvatar(int id, String avatar) {
        Account x = null;

        if (avatar.isEmpty() || avatar == null) {
            avatar = "/9j/4AAQSkZJRgABAQEBLAEsAAD/4QBWRXhpZgAATU0AKgAAAAgABAEaAAUAAAABAAAAPgEbAAUAAAABAAAARgEoAAMAAAABAAIAAAITAAMAAAABAAEAAAAAAAAAAAEsAAAAAQAAASwAAAAB/+0ALFBob3Rvc2hvcCAzLjAAOEJJTQQEAAAAAAAPHAFaAAMbJUccAQAAAgAEAP/hDIFodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0n77u/JyBpZD0nVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkJz8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0nYWRvYmU6bnM6bWV0YS8nIHg6eG1wdGs9J0ltYWdlOjpFeGlmVG9vbCAxMS44OCc+CjxyZGY6UkRGIHhtbG5zOnJkZj0naHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyc+CgogPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9JycKICB4bWxuczp0aWZmPSdodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyc+CiAgPHRpZmY6UmVzb2x1dGlvblVuaXQ+MjwvdGlmZjpSZXNvbHV0aW9uVW5pdD4KICA8dGlmZjpYUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpYUmVzb2x1dGlvbj4KICA8dGlmZjpZUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpZUmVzb2x1dGlvbj4KIDwvcmRmOkRlc2NyaXB0aW9uPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6eG1wTU09J2h0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8nPgogIDx4bXBNTTpEb2N1bWVudElEPmFkb2JlOmRvY2lkOnN0b2NrOmU2MWZlYTMwLWRjMTktNDQ0Ny1iMzIzLTI3ZGNkNWE5OGY4NTwveG1wTU06RG9jdW1lbnRJRD4KICA8eG1wTU06SW5zdGFuY2VJRD54bXAuaWlkOmQ5NzQ4NzRhLWUzNmYtNGVhYy05ZWM2LTExMDBhZmE3ZGRjMjwveG1wTU06SW5zdGFuY2VJRD4KIDwvcmRmOkRlc2NyaXB0aW9uPgo8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSd3Jz8+/9sAQwAFAwQEBAMFBAQEBQUFBgcMCAcHBwcPCwsJDBEPEhIRDxERExYcFxMUGhURERghGBodHR8fHxMXIiQiHiQcHh8e/8AACwgBaAFoAQERAP/EABwAAQACAwEBAQAAAAAAAAAAAAAFBgIDBAEHCP/EADoQAQACAQMABwUHAQcFAAAAAAABAgMEBREGEyEiMUFREmFxgcEUMlKRobHR4SMzQmJygvEWJENEk//aAAgBAQAAPwD9BgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA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";
        }

        xSql = "update [User] set avatar = ? where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, avatar);
            ps.setInt(2, id);

            ps.executeUpdate();
            x = getAccountById(id);
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public List<ChangeHistory> getChangeHistoryByUserId(int userId) {
        List<ChangeHistory> changeHistoryList = new ArrayList<>();
        String query = "SELECT TOP (1000) [change_id]\n"
                + "      ,[user_id]\n"
                + "      ,[changed_column]\n"
                + "      ,[old_value]\n"
                + "      ,[new_value]\n"
                + "      ,[change_date]\n"
                + "  FROM [SWP391_Gr1].[dbo].[UserChangeHistory] where user_id=?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChangeHistory changeHistory = new ChangeHistory();
                changeHistory.setChangeId(rs.getInt("change_id"));
                changeHistory.setUserId(rs.getInt("user_id"));
                changeHistory.setChangedColumn(rs.getString("changed_column"));
                changeHistory.setOldValue(rs.getString("old_value"));
                changeHistory.setNewValue(rs.getString("new_value"));
                changeHistory.setChangeDate(rs.getDate("change_date"));
                changeHistoryList.add(changeHistory);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return changeHistoryList;
    }

    public int getTotalUser() {
        int totalUser = 0;
        String query = "select count(*) from [User] where role='user'";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalUser = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return totalUser;
    }

    public ArrayList<Role> getRolesAndFeatures(String username, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT a.email,r.role_id,r.role_name,f.fid,f.url  FROM \n"
                    + "[User] a INNER JOIN [Role_Account] ra \n"
                    + "ON a.id = ra.uid\n"
                    + "INNER JOIN [Role] r\n"
                    + "ON ra.rid = r.role_id\n"
                    + "INNER JOIN Role_Feature rf\n"
                    + "ON rf.rid= r.role_id\n"
                    + "INNER JOIN Feature f\n"
                    + "ON f.fid = rf.fid\n"
                    + "WHERE\n"
                    + "a.email = ? AND f.url = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("role_id"));
                r.setName(rs.getString("role_name"));

                Feature f = new Feature();
                f.setId(rs.getInt("fid"));
                f.setUrl(rs.getString("url"));

                r.getFeatures().add(f);
                roles.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

    private int getAccountIdByEmail(String email) {

        xSql = "SELECT [id]\n"
                + "      ,[fullName]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[status]\n"
                + "      ,[role]\n"
                + "      ,[wishlist_id]\n"
                + "      ,[cart_id]\n"
                + "      ,[avatar]\n"
                + "      ,[gender]\n"
                + "  FROM [SWP391_Gr1].[dbo].[User]\n"
                + "  WHERE email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int addNewUser(Account acc) {
        CartDao carDaO = new CartDao();
        WishListDao wishListDao = new WishListDao();
        AddressDAO adrDao = new AddressDAO();
        Address address = new Address();
        
        xSql = "INSERT INTO [dbo].[User] ([fullName], [email], [password], [phone], [role], [gender], [status], [avatar]) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, acc.getFullname());
            ps.setString(2, acc.getMail());
            ps.setString(3, acc.getPassword());
            ps.setString(4, acc.getPhone());
            ps.setString(5, acc.getRole());
            ps.setString(6, acc.getGender());
            ps.setBoolean(7, acc.isStatus());
            ps.setString(8, acc.getAvatar());

            int result = ps.executeUpdate();

            if (result > 0) {
                int userIdNew = getAccountIdByEmail(acc.getMail());
                if (userIdNew > 0) {
                    address.setUserId(userIdNew);
                    address.setCity(acc.getCity());
                    address.setDistrict(acc.getDistrict());
                    address.setWard(acc.getWard());
                    address.setStreet(acc.getStreet());
                    
                    int idC = carDaO.createCart(userIdNew);
                    int idW = wishListDao.createWishList(userIdNew);
                    if (idC != 0 && idW != 0) {
                        adrDao.addNewAddress(address);
                        return updateAccount(idW, idC, userIdNew);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void generatePassW(String mail, String passEndcode) {

        xSql = "INSERT INTO [SWP391_Gr1].[dbo].[User] ([password]) VALUES (?) where email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, passEndcode);
            ps.setString(2, mail);

            ps.executeUpdate();
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalMember() {
        int totalMember = 0;
        String query = "SELECT COUNT(id) AS totalUsers\n"
                + "FROM [SWP391_Gr1].[dbo].[User];";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalMember = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return totalMember;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
//        System.out.println(dao.getAllCustomer());
        System.out.println(dao.getAccountById(2).isStatus());
    }
}
