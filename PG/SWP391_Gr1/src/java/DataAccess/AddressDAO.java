/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Address;
import Models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DoanManhTai
 */
public class AddressDAO extends DBContext{

    public ArrayList<Address> getAddressByid(int id) {
        ArrayList<Address> listAddress = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[city]\n"
                    + "      ,[district]\n"
                    + "      ,[ward]\n"
                    + "      ,[street]\n"
                    + "      ,[user_id]\n"
                    + "  FROM [SWP391_Gr1].[dbo].[Address]\n"
                    + "  WHERE user_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Address a = new Address();
                a.setId(rs.getInt("id"));
                a.setCity(rs.getString("city"));
                a.setDistrict(rs.getString("district"));
                a.setWard(rs.getString("ward"));
                a.setStreet(rs.getString("street"));
                listAddress.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listAddress;
    }

    public void addNewAddress(Address address) {
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO [SWP391_Gr1].[dbo].[Address] \n"
                    + "(user_id, street, ward, district, city) \n"
                    + "VALUES (?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, address.getUserId());
            stm.setString(2, address.getStreet());
            stm.setString(3, address.getWard());
            stm.setString(4, address.getDistrict());
            stm.setString(5, address.getCity());

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public boolean deleteAddressByID(int userID) {
        PreparedStatement stm = null;

        try {
            String sql = "DELETE FROM [SWP391_Gr1].[dbo].[Address] WHERE [user_id] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);

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

    public Address getAddressByIdUser(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select a.user_id, a.street, a.ward, a.district, a.city from Address a\n"
                    + "where a.user_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Address a = new Address();
                a.setUserId(rs.getInt("user_id"));
                a.setStreet(rs.getString("street"));
                a.setWard(rs.getString("ward"));
                a.setDistrict(rs.getString("district"));
                a.setCity(rs.getString("city"));
                return a;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static void main(String[] args) {
        AddressDAO dao = new AddressDAO();
        System.out.println(dao.getAddressByIdUser(21));
    }
}
