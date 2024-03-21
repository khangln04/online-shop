/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Blog;
import Models.Images;
import Models.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommonDAO extends DBContext {

    public int getTotalCustomer() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rls = 0;
        try {
            String sql = "SELECT COUNT([id]) as customer\n"
                    + "FROM dbo.[User] \n"
                    + "WHERE role = 'user';";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                rls = rs.getInt("customer");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rls;
    }

    public int getProductTotal() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rls = 0;
        try {
            String sql = "SELECT COUNT([id]) as product\n"
                    + "  FROM [dbo].[Product]";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                rls = rs.getInt("product");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rls;
    }

    public ArrayList<Product> getTopProductsByWeeklyOrderCount(Date startDate, Date endDate) {
        ArrayList<Product> products = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT TOP 5 "
                    + "p.[id] AS productId, "
                    + "p.[name] AS productName, "
                    + "COUNT(od.[order_id]) AS orderCount "
                    + "FROM [dbo].[Product] p "
                    + "JOIN [dbo].[OrderDetail] od ON p.[id] = od.[product_id] "
                    + "JOIN [dbo].[Order] o ON od.[order_id] = o.[id] "
                    + "WHERE o.[date] BETWEEN ? AND ? "
                    + "GROUP BY p.[id], p.[name] "
                    + "ORDER BY orderCount DESC";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, new java.sql.Date(startDate.getTime()));
            stm.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                products.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return products;
    }

    public static void main(String[] args) {
        CommonDAO dao = new CommonDAO();
        Date startDate = java.sql.Date.valueOf("2024-01-01");
        Date endDate = java.sql.Date.valueOf("2024-01-31");
        System.out.println(dao.getProductTotal());
    }
}
