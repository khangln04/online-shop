/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Models.Account;
import Models.OrderDetail;
import DataAccess.OrderDetailDAO;
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
public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String orderId = request.getParameter("orderId");
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        if (orderId != null) {
            try {
                int order_id = Integer.parseInt(orderId);
                if (session != null) {
                    Account account = (Account) session.getAttribute("account");
                    int userId = account.getId();
                    OrderDetailDAO dao = new OrderDetailDAO();
                    int count = dao.getOrderDetailCount(userId);
                    if (count > 0) {
                        List<OrderDetail> OrderDetails = dao.getOrderDetailList(order_id, index);
                        int lastPage = count / 4;
                        if (count % 4 != 0) {
                            lastPage++;
                        }
                        request.setAttribute("orderId", orderId);
                        request.setAttribute("lastPage", lastPage);
                        request.setAttribute("OrderDetailList", OrderDetails);
                        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("login.jsp");
                }

        }catch (Exception e) {
                e.printStackTrace();
            }

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
