/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import Models.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tuant
 */
public class CommentDAO extends DBContext {

    public void addComment(String content, int userId, String blogId) {
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([content]\n"
                + "           ,[user_id]\n"
                + "           ,[blog_id]\n"
                + "           ,[date]\n"
                + "           ,[parent_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,NULL)";
        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setString(1, content);
            st.setInt(2, userId);
            st.setString(3, blogId);
            st.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addReplyComment(String content, int userId, String blogId, String parent) {
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([content]\n"
                + "           ,[user_id]\n"
                + "           ,[blog_id]\n"
                + "           ,[date]\n"
                + "           ,[parent_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setString(1, content);
            st.setInt(2, userId);
            st.setString(3, blogId);
            st.setString(4, parent);
            st.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Comment> getAllComment(String bid) {
        ArrayList<Comment> comment = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  c.[id]\n"
                    + ",c.[content]\n"
                    + ",c.[user_id]\n"
                    + ",c.[blog_id]\n"
                    + ",c.[date]\n"
                    + ",c.[parent_id],\n"
                    + "u.fullName\n"
                    + "FROM [Comment] c join [User] u\n"
                    + "on u.id=c.[user_id]\n"
                    + "where c.parent_id is null and blog_id = ?\n"
                    + "order by c.date\n";

            stm = connection.prepareStatement(sql);
            stm.setString(1, bid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setContent(rs.getString("content"));
                c.setUserId(rs.getInt("user_id"));
                c.setBlogId(rs.getInt("blog_id"));
                c.setDate(rs.getDate("date"));
                Account a = new Account();
                a.setFullname(rs.getString("fullName"));
                c.setUser(a);
                comment.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return comment;
    }

    public ArrayList<Comment> getPaginationCmt(String bid, int index) {
        ArrayList<Comment> comment = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.[id]\n"
                    + ",c.[content]\n"
                    + ",c.[user_id]\n"
                    + ",c.[blog_id]\n"
                    + ",c.[date]\n"
                    + ",c.[parent_id],\n"
                    + "u.fullName\n"
                    + "FROM [Comment] c join [User] u\n"
                    + "on u.id=c.[user_id]\n"
                    + "where c.parent_id is null and blog_id = ?\n"
                    + "order by c.date\n"
                    + "offset ? row fetch next 3 rows only";

            stm = connection.prepareStatement(sql);
            stm.setString(1, bid);
            stm.setInt(2, (index - 1) * 3);
            rs = stm.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setContent(rs.getString("content"));
                c.setUserId(rs.getInt("user_id"));
                c.setBlogId(rs.getInt("blog_id"));
                c.setDate(rs.getDate("date"));
                Account a = new Account();
                a.setFullname(rs.getString("fullName"));
                c.setUser(a);
                comment.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return comment;
    }

    public int getTotalCmt() {
        String sql = "select count(*) from [Comment]";

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

    public ArrayList<Comment> getSubComment(int cid) {
        
        ArrayList<Comment> comment = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT TOP (1000) [id]\n"
                    + "      ,[content]\n"
                    + "      ,[user_id]\n"
                    + "      ,[blog_id]\n"
                    + "      ,[date]\n"
                    + "      ,[parent_id]\n"
                    + "  FROM [Comment]\n"
                    + "  where parent_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setContent(rs.getString("content"));
                c.setUserId(rs.getInt("user_id"));
                c.setBlogId(rs.getInt("blog_id"));
                c.setDate(rs.getDate("date"));
                c.setParentId(rs.getInt("parent_id"));
                comment.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return comment;
    }
}
