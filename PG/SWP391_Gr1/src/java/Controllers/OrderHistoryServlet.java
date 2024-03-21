/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DataAccess.OrderDAO;
import Models.Account;
import Models.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author datnt
 */
public class OrderHistoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
            HttpSession session = request.getSession(false);
            String indexPage = request.getParameter("index");
            if(indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
        try {
            if (session != null) {
                Account account = (Account) session.getAttribute("account");
                int userId = account.getId();
                OrderDAO dao = new OrderDAO();
                int count = dao.getOrderHistoryCount(userId);
                if (count > 0) {
                    List<Order> Orders = dao.getOrderHistory(userId, index);
                    int lastPage = count / 4;
                    if (count % 4 != 0) {
                        lastPage++;
                    }
                    request.setAttribute("lastPage", lastPage);
                    request.setAttribute("OrdersList", Orders);
                    request.getRequestDispatcher("historyOrder.jsp").forward(request, response);
                } else {
                    String msg = "Ban chua co don hang";
                    request.setAttribute("errorMsg", msg);
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }
                       
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
