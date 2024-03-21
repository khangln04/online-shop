/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Manager;

import Controllers.Authentication.BasedAuthorizationController;
import DataAccess.AccountDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Models.Account;
import Models.Product;
import Models.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DoanManhTai
 */
public class Saledash extends BasedAuthorizationController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
         AccountDAO aDao = new AccountDAO();
        Account acount = new Account();
        ProductDAO pDao = new ProductDAO();
        Product product = new Product();
        OrderDAO oDao = new OrderDAO();
        int a = pDao.getTotalProduct();
        int b = aDao.getTotalUser();
        int c = oDao.getTotalOrder();
        int d = oDao.getSuccessOrder();
        List<Product> top5 = pDao.top5Product();
        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("c", c);
        request.setAttribute("d", d);
        request.setAttribute("top5", top5);
        request.getRequestDispatcher("ManagerRole/Sale/dashboard.jsp").forward(request, response);
    }
}
