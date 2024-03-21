/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Order;
import Models.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author datnt
 */
public class OrderDAO extends MyDAO {

    public List<Order> getOrderHistory(int userId, int offset) {
        // get list item in cart.
        List<Order> OrderList = new ArrayList<Order>();
        String sql = "Select id, customerName, phone, address, total, date, status, user_id "
                + "from dbo.[Order] where user_id = ? order by date desc offset ? row fetch next 4 rows only";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (offset - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setUserId(rs.getInt("user_id"));
                order.setOrderId(rs.getInt("id"));
                order.setDate(rs.getDate("date"));
                order.setCustomerName(rs.getString("customerName"));
                order.setAddress(rs.getString("address"));
                order.setTotal(rs.getDouble("total"));
                order.setStatus(rs.getString("status"));
                OrderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OrderList;
    }

    public int getOrderHistoryCount(int userId) {
        int count = 0;
        String sql = "Select count(*) from dbo.[Order] where user_id = ?";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void insertOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, orderDetail.getOrderId());
            ps.setInt(2, orderDetail.getProductId());
            ps.setInt(3, orderDetail.getQuantity());
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(int id, String msg) {
        try {
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNString(1, msg);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getTotalOrder() {
        int total = 0;
        String query = "Select count(*) from dbo.[Order]";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    
    public int getSuccessOrder() {
        int count = 0;
        String query = "Select count(*) from dbo.[Order] where status = 'Payment success'";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
