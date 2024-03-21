/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Manager;

import Controllers.Authentication.BasedAuthorizationController;
import DataAccess.AccountDAO;
import Models.Account;
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
@WebServlet(name = "ViewCustomerServlet", urlPatterns = {"/Manager/ViewCustomer"})
public class ViewCustomerServlet extends BasedAuthorizationController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        AccountDAO aDAO = new AccountDAO();
        HttpSession session = request.getSession();
        List<Account> listA = aDAO.getAllCustomer();
        // pagging
        if (session.getAttribute("page") == null) {
            Pagination Page = new Pagination(listA, 11, 1);
            Pagination<Account> pagination = new Pagination<>(listA, 11, 1);
            session.setAttribute("page", Page);
            request.setAttribute("listCustomer", pagination.getItemsOnPage());
        } else if (request.getParameter("cp") != null) {
            int cp = Integer.parseInt(request.getParameter("cp"));
            Pagination Page = new Pagination(listA, 11, cp);
            Pagination<Account> pagination = new Pagination<>(listA, 11, cp);
            listA = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("listCustomer", listA);
        }
        // set URL
        request.setAttribute("pagging", "ViewCustomer");
        request.getRequestDispatcher("ViewCustomer.jsp").forward(request, response);
    }

}
