/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.Admin;

import Controllers.Authentication.BasedAuthorizationController;
import DataAccess.UserDAO;
import Models.Account;
import Models.Pagination;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fpt
 */
public class UserList extends BasedAuthorizationController {
   
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        List<Account> listA = userDAO.getAllUser();
        
        // pagging
        if (session.getAttribute("page") == null) {
            Pagination Page = new Pagination(listA, 9, 1);
            Pagination<Account> pagination = new Pagination<>(listA, 9, 1);
            session.setAttribute("page", Page);
            request.setAttribute("listUser", pagination.getItemsOnPage());
        } else if (request.getParameter("cp") != null) {
            int cp = Integer.parseInt(request.getParameter("cp"));
            Pagination Page = new Pagination(listA, 9, cp);
            Pagination<Account> pagination = new Pagination<>(listA, 9, cp);
            listA = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("listUser", listA);
        }
        
        request.setAttribute("pagging", "UserList");
        request.getRequestDispatcher("./UserList.jsp").forward(request, response);
    }

}
