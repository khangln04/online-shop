/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import java.sql.ResultSet;

/**
 *
 * @author taisk
 */
public class WishListDao extends MyDAO {

    public int createWishList(int user_id) {
        Account x = null;
        xSql = "INSERT INTO [dbo].[WishList] ([user_id]) VALUES (?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, user_id);
            int result = ps.executeUpdate();
            if (result > 0) {
                return wishListIdTop1();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int wishListIdTop1() {
        Account x = null;
        xSql = "SELECT Top(1) id\n"
                + "FROM [WishList]\n"
                + "ORDER BY id DESC;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
