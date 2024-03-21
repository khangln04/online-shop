/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Order;
import Models.OrderDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author datnt
 */
public class OrderDetailDAO extends MyDAO {
     public List<OrderDetail> getOrderDetailList(int orderId, int offset) {
        // get list item in cart.
        List<OrderDetail> OrderDetailList = new ArrayList<OrderDetail>();
        String sql = "Select od.[order_id],"
                + " p.[id],"
                + "p.[name],"
                + " od.[quantity],"
                + " p.[price],"
                + " od.[price] as totalPrice,"
                + " im.[url] "
                + "from dbo.[OrderDetail] od join dbo.[Product] p on od.product_id = p.id "
                + "join dbo.[Image] im on p.id =  im.product_id where od.order_id = ? "
                + "order by p.[id] desc offset ? row fetch next 4 rows only";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, orderId);            
            ps.setInt(2, (offset - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductId(rs.getInt("id"));                
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setProductName(rs.getString("name"));
                orderDetail.setProductPrice(rs.getDouble("price"));
                orderDetail.setProductTotalPrice(rs.getDouble("totalPrice"));
                orderDetail.setUrl(rs.getString("url"));
                OrderDetailList.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OrderDetailList;
    }
    
    public int getOrderDetailCount(int orderId) {
        int count = 0;
        String sql = "Select count(*) from dbo.[OrderDetail] od join dbo.[Product] p on od.product_id = p.id where od.order_id = ?";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, orderId);
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
