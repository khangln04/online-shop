/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import Models.CartItem;
import Models.Order;
import Models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taisk
 */
public class CartDao extends MyDAO {

    private ArrayList<CartItem> cartList = new ArrayList<>();

    public int createCart(int user_id) {
        Account x = null;
        xSql = "INSERT INTO [dbo].[Cart] ([user_id]) VALUES (?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, user_id);
            int result = ps.executeUpdate();
            if (result > 0) {
                return cartIdTop1();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int cartIdTop1() {
        Account x = null;
        xSql = "SELECT Top(1) id\n"
                + "FROM [Cart]\n"
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

    public ArrayList<CartItem> getCartItem(int cart_id) {
        if (this.cartList.size() != 0) {
            return this.cartList;
        }
        ArrayList<CartItem> cartItem = new ArrayList<>();
        try {
            xSql = "SELECT i.[id]\n"
                    + "      ,i.[product_id]\n"
                    + "      ,i.[cart_id]\n"
                    + "      ,i.[quantity]\n"
                    + "      ,i.[size]\n"
                    + "      ,i.[color]\n"
                    + "	  ,p.id as 'productId'\n"
                    + "	  ,p.name\n"
                    + "	  ,p.price\n"
                    + "  FROM [SWP391_Gr1].[dbo].[Item] i\n"
                    + "  inner join Product p \n"
                    + "  on i.product_id = p.id\n"
                    + "  Where cart_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cart_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem c = new CartItem();
                c.setId(rs.getInt("id"));
                c.setProduct_id(rs.getInt("product_id"));
                c.setCart_id(rs.getInt("cart_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setSize(rs.getString("size"));
                Product p = new Product();
                p.setId(rs.getInt("productId"));
                p.setProductName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                c.setProduct(p);
                cartItem.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        this.cartList = cartItem;
        return cartItem;
    }

    public CartItem getCartItemByid(int id, int cart_id) {
        try {
            xSql = "SELECT [id]\n"
                    + "      ,[product_id]\n"
                    + "      ,[cart_id]\n"
                    + "      ,[quantity]\n"
                    + "      ,[size]\n"
                    + "      ,[color]\n"
                    + "  FROM [SWP391_Gr1].[dbo].[Item]\n"
                    + "  Where id = ? and cart_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.setInt(2, cart_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem c = new CartItem();
                c.setId(rs.getInt("id"));
                c.setProduct_id(rs.getInt("product_id"));
                c.setCart_id(rs.getInt("cart_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setSize(rs.getString("size"));
                return c;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void deleteItem(int cart_id, int id) {

        try {
            xSql = "Delete [SWP391_Gr1].[dbo].[Item] where cart_id = ? and id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cart_id);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public CartItem checkItemExits(int cart_Id, int product_Id) {
        try {
            xSql = "SELECT i.[id]\n"
                    + "      ,i.[product_id]\n"
                    + "      ,i.[cart_id]\n"
                    + "      ,i.[quantity]\n"
                    + "      ,i.[size]\n"
                    + "      ,i.[color]\n"
                    + "	  ,p.id as 'productId'\n"
                    + "	  ,p.name\n"
                    + "	  ,p.price\n"
                    + "  FROM [SWP391_Gr1].[dbo].[Item] i\n"
                    + "  INNER JOIN Product p \n"
                    + "  ON i.product_id = p.id\n"
                    + "  WHERE i.cart_id = ? and i.product_id =?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cart_Id);
            ps.setInt(2, product_Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem c = new CartItem();
                c.setId(rs.getInt("id"));
                c.setProduct_id(rs.getInt("product_id"));
                c.setCart_id(rs.getInt("cart_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setSize(rs.getString("size"));
                Product p = new Product();
                p.setId(rs.getInt("productId"));
                p.setProductName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                c.setProduct(p);
                return c;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void updateCartItem(CartItem cartItem) {
        try {
            String sql = "UPDATE [Item] SET [quantity] = ? WHERE cart_id = ? and product_id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartItem.getQuantity());
            stm.setInt(2, cartItem.getCart_id());
            stm.setInt(3, cartItem.getProduct_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void updateCartItem(int quantity,int cartItem_id , int cart_id) {
        try {
            String sql = "UPDATE [Item] SET [quantity] = ? WHERE cart_id = ? and id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, cart_id);
            stm.setInt(3, cartItem_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCartItem(CartItem cartItem) {
        try {
            String sql = "INSERT INTO [dbo].[Item]\n"
                    + "           ([product_id]\n"
                    + "           ,[cart_id]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartItem.getProduct_id());
            stm.setInt(2, cartItem.getCart_id());
            stm.setInt(3, cartItem.getQuantity());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order createOrder(Order oder) {
        xSql = "INSERT INTO [dbo].[Order]\n"
                + "           ([customerName]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[total]\n"
                + "           ,[date]\n"
                + "           ,[status]\n"
                + "           ,[user_id])\n"
                + "	 OUTPUT Inserted.*\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,SYSDATETIME()\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, oder.getCustomerName());
            ps.setString(2, oder.getPhone());
            ps.setString(3, oder.getAddress());
            ps.setDouble(4, oder.getTotal());
            ps.setString(5, oder.getStatus());
            ps.setDouble(6, oder.getUserId());

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                Order newOrder = new Order();
                newOrder.setOrderId(result.getInt("id"));
                newOrder.setCustomerName(result.getString("customerName"));
                newOrder.setPhone(result.getString("phone"));
                newOrder.setAddress(result.getString("address"));
                newOrder.setTotal(result.getDouble("total"));
                newOrder.setDate(result.getDate("date"));
                newOrder.setStatus(result.getString("status"));
                newOrder.setUserId(result.getInt("user_id"));

                return newOrder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
