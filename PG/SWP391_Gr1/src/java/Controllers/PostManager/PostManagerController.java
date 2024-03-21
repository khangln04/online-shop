/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.PostManager;

import Controllers.Authentication.BasedAuthorizationController;
import DataAccess.BlogDAO;
import DataAccess.UserDAO;
import Models.Account;
import Models.Blog;
import Models.Order;
import Models.Pagination;
import Models.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tungl
 */
@WebServlet(name = "PostManagerController", urlPatterns = {"/Manager/PostManager"})
public class PostManagerController extends BasedAuthorizationController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
         BlogDAO bDAO = new BlogDAO();
        List<Blog> listBlog = bDAO.getAllBlog();
  
        request.setAttribute("list", listBlog);
        UserDAO uDAO = new UserDAO();
        request.setAttribute("uDAO", uDAO);
        HttpSession session = request.getSession();
        int limitPage = 8;
        
        // pagging
        if (request.getParameter("cp") == null) {
            Pagination Page = new Pagination(listBlog, limitPage, 1);
            Pagination<Blog> pagination = new Pagination<>(listBlog, limitPage, 1);
            listBlog = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("list", pagination.getItemsOnPage());
        } else if (request.getParameter("cp") != null) {
            int cp = Integer.parseInt(request.getParameter("cp"));
            Pagination Page = new Pagination(listBlog, limitPage, cp);
            Pagination<Blog> pagination = new Pagination<>(listBlog, limitPage, cp);
            listBlog = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("list", listBlog);
        }
        // set URL
        request.setAttribute("pagging", "PostManager");
        
        request.getRequestDispatcher("../ManagerRole/MKT/ViewPostList.jsp").forward(request, response);
    }
}
