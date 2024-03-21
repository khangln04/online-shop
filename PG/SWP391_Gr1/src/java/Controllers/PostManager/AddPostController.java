/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.PostManager;

import static Common.Helper.convertToBase64;
import DataAccess.BlogDAO;
import DataAccess.UserDAO;
import Models.Account;
import Models.Blog;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet(name = "AddPostController", urlPatterns = {"/Manager/AddPost"})
public class AddPostController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String shortContent = request.getParameter("shortContent");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        HttpSession session = request.getSession();
        Account u = (Account) session.getAttribute("account");
//        User u = new User();
//        u.setId(3);
        BlogDAO bDAO = new BlogDAO();
        String filePath;
        Part filePart = request.getPart("image");


        // Convert the input stream of the file to base64
        String base64Image = convertToBase64(filePart.getInputStream());
        // You may need additional validation here
        // Create a new Blog object and set its properties
        Blog newBlog = new Blog();
        newBlog.setTitle(title);
        newBlog.setShortContent(shortContent);
        newBlog.setContent(content);
        newBlog.setImage(base64Image); // Set the filename as the image property
        newBlog.setUserId(u.getId());

        // Add the new blog entry to the database
        try {
            bDAO.createBlog(newBlog);
            request.setAttribute("messSuccess", "Add post successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messError", "Add post failed!");
        }

        // Redirect to the page displaying all blogs
        response.sendRedirect("PostManager");
    }
}
