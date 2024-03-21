/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static Common.Helper.convertToBase64;
import Models.Slider;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author tuant
 */
public class SliderDAO extends DBContext {

    public ArrayList<Slider> getAllSliderToHome() {
        ArrayList<Slider> slider = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  [sid]\n"
                    + "      ,[content]\n"
                    + "      ,[status]\n"
                    + "      ,[createOn]\n"
                    + "      ,[user_id]\n"
                    + "      ,[title]\n"
                    + "      ,[image]\n"
                    + "  FROM [SWP391_Gr1].[dbo].[Slider]";
            stm = connection.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSid(rs.getInt("sid"));
                s.setContent(rs.getString("content"));
                s.setTitle(rs.getString("title"));
                s.setImg(rs.getString("image"));
                s.setStatus(rs.getInt("status"));
                s.setCreateOn(rs.getDate("createOn"));
                slider.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return slider;
    }

    public ArrayList<Slider> getAllSliderList(int index) {
        ArrayList<Slider> slider = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  [sid]\n"
                    + "      ,[title]\n"
                    + "      ,[content]\n"
                    + "      ,[createOn]\n"
                    + "      ,[image]\n"
                    + "      ,[user_id]\n,"
                    + "       [status]"
                    + "  FROM [dbo].[Slider]\n"
                    + "  order by createOn desc\n "
                    + "OFFSET ? ROW FETCH NEXT 5 ROWS ONLY";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSid(rs.getInt("sid"));
                s.setUser_id(rs.getInt("user_id"));
                s.setContent(rs.getString("content"));
                s.setTitle(rs.getString("title"));
                s.setImg(rs.getString("image"));
                s.setStatus(rs.getInt("status"));
                s.setCreateOn(rs.getDate("createOn"));
                slider.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return slider;
    }

//    public ArrayList<SliderVM> getAllSliderListHome() {
//        ArrayList<SliderVM> slider = new ArrayList<>();
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            String sql = "SELECT  [sid]\n"
//                    + "      ,[title]\n"
//                    + "      ,[content]\n"
//                    + "      ,[createOn]\n"
//                    + "      ,[image]\n"
//                    + "      ,[user_id]\n,"
//                    + "       [status]"
//                    + "  FROM [dbo].[Slider]  WHERE status = 1\n"
//                    + "  order by createOn desc\n ";
//            stm = connection.prepareStatement(sql);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                SliderVM s = new SliderVM();
//                s.setSid(rs.getInt("sid"));
//                s.setUser_id(rs.getInt("user_id"));
//                s.setContent(rs.getString("content"));
//                s.setTitle(rs.getString("title"));
//                byte[] imageData = rs.getBytes("image");
//                String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
//                s.setImage(base64Image);
//                s.setStatus(rs.getInt("status"));
//                s.setCreateOn(rs.getDate("createOn"));
//                slider.add(s);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return slider;
//    }
    public int getTotalSlider() {
        String sql = "select count(*) from [Slider]";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public boolean createSlider(String title, String content, String image, int userId, int status) throws SQLException, IOException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO Slider(title, content, createOn, image, user_id, status)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, content);
            stm.setObject(3, LocalDateTime.now());
            stm.setString(4, image);
            stm.setInt(5, userId);
            stm.setInt(6, status);
            int affectedRow = stm.executeUpdate();
            if (affectedRow > 0) {
                System.out.println("File was store in db");

                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public boolean deleteSliderById(int id) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "DELETE FROM dbo.[Slider] Where sid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            int affectedRow = stm.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }

    public boolean disableSlider(int id) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE dbo.[Slider] SET status = 0 Where sid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            int affectedRow = stm.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }

    public boolean enableSlider(int id) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE dbo.[Slider] SET status = 1 Where sid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            int affectedRow = stm.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }

    public ArrayList<Slider> getAllSlider() {
        ArrayList<Slider> slider = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  [sid]\n"
                    + "      ,[image]\n"
                    + "  FROM [SWP391_Gr1].[dbo].[Slider]";
            stm = connection.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSid(rs.getInt("sid"));

                slider.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return slider;
    }
}
