/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Blog;
import Models.Category;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuant
 */
public class BlogDAO extends DBContext {

    public ArrayList<Blog> getAllBlog(int index) {
        ArrayList<Blog> blog = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  [id]\n"
                    + "      ,[title]\n"
                    + "      ,[shortContent]\n"
                    + "      ,[content]\n"
                    + "      ,[publishedDate]\n"
                    + "      ,[image]\n"
                    + "      ,[user_id]\n"
                    + "  FROM [Blog] WHERE isActive = 1\n"
                    + "  order by publishedDate\n"
                    + "	offset ? row fetch next 3 rows only";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 3);
            rs = stm.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setContent(rs.getString("content"));
                b.setShortContent(rs.getString("shortContent"));
                b.setPublishedDate(rs.getDate("publishedDate"));
                b.setImage(rs.getString("image"));
                b.setPublishedTime(rs.getTime("publishedDate"));
                blog.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return blog;
    }

    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> blog = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  [id]\n"
                    + "      ,[title]\n"
                    + "      ,[shortContent]\n"
                    + "      ,[content]\n"
                    + "      ,[publishedDate]\n"
                    + "      ,[image]\n"
                    + "      ,[user_id],[isActive]\n"
                    + "  FROM [Blog]\n";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setContent(rs.getString("content"));
                b.setShortContent(rs.getString("shortContent"));
                b.setPublishedDate(rs.getDate("publishedDate"));
                b.setImage(rs.getString("image"));
                b.setPublishedTime(rs.getTime("publishedDate"));
                b.setUserId(rs.getInt("user_id"));
                b.setIsActive(rs.getInt("isActive"));
                blog.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return blog;
    }

    public Blog getBlogById(int blogId) {
        Blog blog = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT  [id]\n"
                    + "      ,[title]\n"
                    + "      ,[shortContent]\n"
                    + "      ,[content]\n"
                    + "      ,[publishedDate]\n"
                    + "      ,[image]\n"
                    + "      ,[user_id],[isActive]\n"
                    + "  FROM [Blog]\n"
                    + "  WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, blogId);
            rs = stm.executeQuery();

            if (rs.next()) {
                blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setShortContent(rs.getString("shortContent"));
                blog.setPublishedDate(rs.getDate("publishedDate"));
                blog.setImage(rs.getString("image"));
                blog.setPublishedTime(rs.getTime("publishedDate"));
                blog.setUserId(rs.getInt("user_id"));
                blog.setIsActive(rs.getInt("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            // Close resources in a 'finally' block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return blog;
    }

    public int getTotalBlog() {
        String sql = "  select count(*) from [Blog]";

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

    public ArrayList<Blog> getBlogByUser(String user_name) {

        ArrayList<Blog> bs = new ArrayList<>();

        try {
            String sql = "select b.* from [Blog] b join [User] u on b.user_id = u.id where u.fullName = '?'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user_name);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Blog b = new Blog();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setContent(rs.getString("content"));
                b.setShortContent(rs.getString("shortContent"));
                b.setPublishedDate(rs.getDate("publishedDate"));
                b.setImage(rs.getString("image"));
                b.setPublishedTime(rs.getTime("publishedDate"));
                bs.add(b);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bs;

    }

    public ArrayList<Blog> getBlogByTitle(String title) {

        ArrayList<Blog> bs = new ArrayList<>();

        try {
            String sql = "select * from [Blog] b where title like '%?%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Blog b = new Blog();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setContent(rs.getString("content"));
                b.setShortContent(rs.getString("shortContent"));
                b.setPublishedDate(rs.getDate("publishedDate"));
                b.setImage(rs.getString("image"));
                b.setPublishedTime(rs.getTime("publishedDate"));
                bs.add(b);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bs;

    }

    public boolean createBlog(Blog newBlog) {
        PreparedStatement stm = null;

        try {
            String sql = "INSERT INTO [Blog] "
                    + "([title], [shortContent], [content], [publishedDate], [image], [user_id]) "
                    + "VALUES (?, ?, ?, GETDATE(), ?, ?)";
            stm = connection.prepareStatement(sql);

            stm.setString(1, newBlog.getTitle());
            stm.setString(2, newBlog.getShortContent());
            stm.setString(3, newBlog.getContent());
            stm.setString(4, newBlog.getImage());
            // Assuming user_id is an integer
            stm.setInt(5, newBlog.getUserId());

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

    public boolean updateBlog(Blog updatedBlog) {
        PreparedStatement stm = null;

        try {
            String sql = "UPDATE [Blog] "
                    + "SET [title] = ?, [shortContent] = ?, [content] = ?, "
                    + "[publishedDate] = GETDATE(), [image] = ?, [user_id] = ?, [isActive] = ? "
                    + "WHERE [id] = ?";
            stm = connection.prepareStatement(sql);

            stm.setString(1, updatedBlog.getTitle());
            stm.setString(2, updatedBlog.getShortContent());
            stm.setString(3, updatedBlog.getContent());
            stm.setString(4, updatedBlog.getImage());
            // Assuming user_id is an integer
            stm.setInt(5, updatedBlog.getUserId());
            stm.setInt(6, updatedBlog.getIsActive());
            stm.setInt(7, updatedBlog.getId());

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

    public boolean deleteBlog(int blogId) {
        PreparedStatement stm = null;

        try {
            String sql = "DELETE FROM [Blog] WHERE [id] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, blogId);

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
        BlogDAO dao = new BlogDAO();
        Blog newBlog = new Blog();
        newBlog.setTitle("Test Blog Title");
        newBlog.setShortContent("Test Short Content");
        newBlog.setContent("Test Content");
        newBlog.setImage("test.jpg");
        newBlog.setUserId(1); // Assuming 1 is a valid user_id

//        dao.createBlog(newBlog);
        System.out.println(dao.deleteBlog(3));
    }

}
