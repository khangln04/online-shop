/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AD
 */
public class MarketingDAO extends DBContext {

    public int getTotalPost() {
        int totalPost = 0;
        try {
            String sql = "SELECT COUNT(*) AS [totalPost] FROM Blog";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                totalPost = rs.getInt("totalPost");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPost;
    }

    public int getTotalProduct() {
        int totalProduct = 0;
        try {
            String sql = "SELECT COUNT(*) AS [totalProduct] FROM Product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                totalProduct = rs.getInt("totalProduct");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalProduct;
    }

    public int getTotalCustomer() {
        int totalCustomer = 0;
        try {
            //u.role is role of customer
            String sql = "  SELECT COUNT(*) AS [totalCustomer] FROM [User] u WHERE u.role = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                totalCustomer = rs.getInt("totalCustomer");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCustomer;
    }

    public int getTotalOrderLast7day() {
        int totalOrder = 0;
        try {

            String sql = "SELECT COUNT(*) AS [totalOrder] FROM [Order] o WHERE o.[date] BETWEEN DATEADD(DAY, -7, GETDATE()) AND DATEADD(DAY, 7, GETDATE()) ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                totalOrder = rs.getInt("totalOrder");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalOrder;
    }
    
    public ArrayList<Product> getTopSelling(){
        ArrayList<Product> ps = new ArrayList<>();
        
        try {
            String sql = "SELECT TOP 5 p.id, p.name, SUM(o.quantity) AS [totalQuantity] FROM OrderDetail o JOIN Product p ON p.id = o.product_id GROUP BY p.id, p.name ORDER BY [totalQuantity] DESC ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                
                ps.add(p);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    } 

}
