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

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet(name = "UpdatePostServlet", urlPatterns = {"/Manager/UpdatePost"})
public class UpdatePostServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));

        BlogDAO bdao = new BlogDAO();
        Blog b = bdao.getBlogById(id);
        HttpSession session = request.getSession();
        Account u = (Account) session.getAttribute("account");
        UserDAO uDAO = new UserDAO();

        // Set the blog attributes in the request for the JSP to access
        request.setAttribute("blogId", b.getId());
        request.setAttribute("title", b.getTitle());
        request.setAttribute("author", uDAO.getUserById(b.getUserId()).getFullName()); // Assuming there's a getAuthor() method in your Blog class
        request.setAttribute("shortContent", b.getShortContent());
        request.setAttribute("content", b.getContent());
        request.setAttribute("image", b.getImage());
        request.setAttribute("userId", b.getUserId());
        request.setAttribute("status", b.getIsActive());
        request.setAttribute("update", "update");
        request.getRequestDispatcher("UpdatePost.jsp").forward(request, response);
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
//        int userId = 3;
        int blogId = Integer.parseInt(request.getParameter("blogId")); // Assuming blogId is an integer
        String status = request.getParameter("status");
        int isActive = Integer.parseInt(status);
        BlogDAO bDAO = new BlogDAO();

        // File upload handling
        Part filePart = request.getPart("image");
        String base64Image = convertToBase64(filePart.getInputStream());

        // You may need additional validation here
        // Create a new Blog object and set its properties
        Blog updatedBlog = new Blog();
        updatedBlog.setId(blogId);
        updatedBlog.setTitle(title);
        updatedBlog.setShortContent(shortContent);
        updatedBlog.setContent(content);
        updatedBlog.setImage(base64Image); // Set the filename as the image property
        updatedBlog.setUserId(u.getId());
        updatedBlog.setIsActive(isActive);

        // Update the blog entry in the database
        try {
            bDAO.updateBlog(updatedBlog);
            request.setAttribute("messSuccess", "Update post successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messError", "Update post failed!");
        }

        // Redirect to the page displaying all blogs
        response.sendRedirect("PostManager");
    }

}
