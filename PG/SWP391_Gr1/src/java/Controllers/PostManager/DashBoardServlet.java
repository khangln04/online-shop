/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.PostManager;

import Controllers.Authentication.BasedAuthorizationController;
import DataAccess.BlogDAO;
import DataAccess.CommonDAO;
import DataAccess.ProductDAO;
import Models.Account;
import Models.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author tungl
 */
@WebServlet(name = "DashBoardServlet", urlPatterns = {"/Manager/DashBoard"})
public class DashBoardServlet extends BasedAuthorizationController {


   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
        CommonDAO cDAO = new CommonDAO();
        BlogDAO bDAO = new BlogDAO();
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        Date startDate, endDate;
        if (date1 == null) {
            startDate = Date.valueOf("1999-01-01");
            endDate = Date.valueOf("2222-01-01");
        } else {
            startDate = Date.valueOf(date1);
            endDate = Date.valueOf(date2);
        }
        request.setAttribute("Product", cDAO.getProductTotal());
        request.setAttribute("Customer", cDAO.getTotalCustomer());
        request.setAttribute("Post", bDAO.getAllBlog().size());
        request.setAttribute("listP", cDAO.getTopProductsByWeeklyOrderCount(startDate, endDate));

        request.getRequestDispatcher("../ManagerRole/MKT/DashBoard.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser, ArrayList<Role> roles) throws ServletException, IOException {
         ProductDAO pDAO = new ProductDAO();
        CommonDAO cDAO = new CommonDAO();
        BlogDAO bDAO = new BlogDAO();
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        Date startDate, endDate;
        if (date1 == null) {
            startDate = Date.valueOf("1999-01-01");
            endDate = Date.valueOf("2222-01-01");
        } else {
            startDate = Date.valueOf(date1);
            endDate = Date.valueOf(date2);
        }
        request.setAttribute("Product", cDAO.getProductTotal());
        request.setAttribute("Customer", cDAO.getTotalCustomer());
        request.setAttribute("Post", bDAO.getAllBlog().size());
        request.setAttribute("listP", cDAO.getTopProductsByWeeklyOrderCount(startDate, endDate));

        request.getRequestDispatcher("../ManagerRole/MKT/DashBoard.jsp").forward(request, response);
    }
}
