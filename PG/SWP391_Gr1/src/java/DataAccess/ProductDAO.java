/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import Models.Images;
import Models.Product;
import Models.Rating;
import Models.User;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuant
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> getRecomendProduct() {
        ArrayList<Product> product = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT top(3) p.id, p.name,p.description,p.price,i.url,\n"
                    + "                    i.id as 'imageId'\n"
                    + "                     FROM [Product] p join Image i \n"
                    + "                     on p.id=i.product_id\n"
                    + "	 order by p.price desc";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                product.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return product;
    }

    public ArrayList<Product> pagingProduct(int index) {

        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "order by p.id\n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setInt(1, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Product> SearchProduct(int index, String txt) {

        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.date,p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "where p.name like '%" + txt + "%'\n"
                + "order by p.date desc\n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Product> pagingProductOrderByDate(int index, String date) {

        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + " order by p.date " + date + " \n"
                + "offset ? row fetch next 6 rows only";
        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setInt(1, (index - 1) * 6);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Product> pagingProductOrderByPrice(int index, String price) {

        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + " order by p.price " + price + " \n"
                + "offset ? row fetch next 6 rows only";
        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setInt(1, (index - 1) * 6);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Images> getImageByProductID(String id) {

        ArrayList<Images> list = new ArrayList<>();
        String sql = "select * from Image where product_id = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Images i = new Images();
                i.setId(rs.getInt("id"));
                i.setUrl(rs.getString("url"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Rating> getRatingByProductID(String id) {

        ArrayList<Rating> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "  FROM [Rating] r join [User] u on r.user_id=u.id\n"
                + "  where  product_id =?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rating i = new Rating();
                i.setId(rs.getInt("id"));
                i.setContent(rs.getString("content"));
                i.setDate(rs.getDate("date"));
                i.setQuantity(rs.getInt("quantity"));
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFullName(rs.getString("fullName"));
                u.setMail(rs.getString("email"));
                i.setUser(u);
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Rating> getTop3RatingByProductID(String id) {

        ArrayList<Rating> list = new ArrayList<>();
        String sql = "SELECT top 3 *\n"
                + "  FROM [Rating] r join [User] u on r.user_id=u.id\n"
                + "  where  product_id =?\n"
                + " order by r.id";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rating i = new Rating();
                i.setId(rs.getInt("id"));
                i.setContent(rs.getString("content"));
                i.setDate(rs.getDate("date"));
                i.setQuantity(rs.getInt("quantity"));
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFullName(rs.getString("fullName"));
                u.setMail(rs.getString("email"));
                i.setUser(u);
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Rating> getNext3RatingByProductID(int amount, String id) {

        ArrayList<Rating> list = new ArrayList<>();
        String sql = "select * FROM [Rating] r join [User] u on r.user_id=u.id\n"
                + "where  product_id =?\n"
                + "order by r.id\n"
                + "offset ? row fetch next 3 rows only";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, (id));
            st.setInt(2, (amount));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rating i = new Rating();
                i.setId(rs.getInt("id"));
                i.setContent(rs.getString("content"));
                i.setDate(rs.getDate("date"));
                i.setQuantity(rs.getInt("quantity"));
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFullName(rs.getString("fullName"));
                u.setMail(rs.getString("email"));
                i.setUser(u);
                list.add(i);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalProduct() {
        String sql = "select count(*) from [Product]";

        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalProductBySearch(String t) {
        String sql = "SELECT \n"
                + "COUNT(*)\n"
                + "FROM [Product]   \n"
                + "where name like '%" + t + "%'";

        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalProductByCId(String cid) {
        String sql = "SELECT \n"
                + "COUNT(*)\n"
                + "FROM [Product]  where \n"
                + "category_id = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalProductByBId(String bid) {
        String sql = "SELECT \n"
                + "COUNT(*)\n"
                + "FROM [Product]  where \n"
                + "brand_id = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, bid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalProductByPrice(String from, String to) {
        String sql = "SELECT \n"
                + "COUNT(*)\n"
                + "FROM [Product]  where \n"
                + "price >=? and price<=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, to);
            st.setString(2, from);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public ArrayList<Product> getProductsByCateId(String cateID, int index) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "where p.category_id=? \n"
                + "order by p.id\n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cateID);
            st.setInt(2, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Product getProductsById(String id) {
        String sql = "SELECT p.quantity,p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "             FROM Product p\n"
                + "             JOIN (\n"
                + "             SELECT id, product_id, url,\n"
                + "                ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "             FROM Image\n"
                + "             ) i ON p.id = i.product_id AND i.rn = 1\n"
                + "		where p.id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setQuantity(rs.getInt("quantity"));
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                return p;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Product getImageByProduct(String id) {
        String sql = "SELECT p.quantity,p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "             FROM Product p\n"
                + "             JOIN (\n"
                + "             SELECT id, product_id, url,\n"
                + "                ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "             FROM Image\n"
                + "             ) i ON p.id = i.product_id AND i.rn = 1\n"
                + "		where p.id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setQuantity(rs.getInt("quantity"));
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                return p;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Product> getProductsByCateIdOrderByDate(String cateID, int index, String date) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "where p.category_id=? \n"
                + "order by p.date ?\n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cateID);
            st.setString(2, date);
            st.setInt(3, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Product> getProductsByBId(String bid, int index) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "where p.brand_id=? \n"
                + "order by p.id\n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bid);
            st.setInt(2, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Product> getProductsByBIdOrderByDate(String bid, int index, String date) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "where p.brand_id=? \n"
                + "order by p.date ? \n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bid);
            st.setString(2, date);
            st.setInt(3, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Product> getProductsByPrice(String from, String to, int index) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name,p.price,p.description, i.id as 'imageId', i.url\n"
                + "FROM Product p\n"
                + "JOIN (\n"
                + "    SELECT id, product_id, url,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY RAND()) AS rn\n"
                + "    FROM Image\n"
                + ") i ON p.id = i.product_id AND i.rn = 1\n"
                + "where p.price >=? and p.price<=?\n"
                + "order by p.id\n"
                + "offset ? row fetch next 6 rows only";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, to);
            st.setString(2, from);
            st.setInt(3, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                Images i = new Images();
                i.setId(rs.getInt("imageId"));
                i.setUrl(rs.getString("url"));
                p.setImages(i);
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void addReview(String content, String quantity, String pid, int uid) {
        String sql = "INSERT INTO [Rating]\n"
                + "           ([content]\n"
                + "           ,[quantity]\n"
                + "           ,[date]\n"
                + "		   ,product_id\n"
                + "		   ,user_id)\n"
                + "     VALUES\n"
                + "           (?,?,GETDATE(),?,?)";
        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setString(1, content);
            st.setString(2, quantity);
            st.setString(3, pid);
            st.setInt(4, uid);
            st.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Rating getRatingAfterInsert(String content, String quantity, String pid, int uid) {
        String sql = "INSERT INTO [Rating]\n"
                + "           ([content]\n"
                + "           ,[quantity]\n"
                + "           ,[date]\n"
                + "		   ,product_id\n"
                + "		   ,user_id)\n"
                + "	OUTPUT inserted.*\n"
                + "    VALUES\n"
                + "           (?,?,GETDATE(),?,?)";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, content);
            st.setString(2, quantity);
            st.setString(3, pid);
            st.setInt(4, uid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rating i = new Rating();
                i.setId(rs.getInt("id"));
                i.setContent(rs.getString("content"));
                i.setDate(rs.getDate("date"));
                i.setQuantity(rs.getInt("quantity"));
                User u = new User();
                u.setId(rs.getInt("user_id"));
                i.setUser(u);
                return i;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> top5Product() {
        List<Product> top = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT TOP 5\n"
                + "    P.id,\n"
                + "    P.name,\n"
                + "    P.description,\n"
                + "    P.price,\n"
                + "    P.quantity,\n"
                + "    P.gender,\n"
                + "    P.category_id,\n"
                + "    COUNT(OD.product_id) AS total_sold\n"
                + "FROM\n"
                + "    Product P\n"
                + "JOIN\n"
                + "    OrderDetail OD ON P.id = OD.product_id\n"
                + "GROUP BY\n"
                + "    P.id, P.name, P.description, P.price, P.quantity, P.gender, P.category_id\n"
                + "ORDER BY\n"
                + "    total_sold DESC;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setGender(rs.getString("gender"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setTotalSold(rs.getInt("total_sold"));
                top.add(p);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return top;
    }
}
