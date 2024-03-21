package Controllers.Manager;

import static Common.Encoding.encodePassword;
import static Common.Helper.convertToBase64;
import DataAccess.AccountDAO;
import Models.Account;
import Models.Address;
import Models.ChangeHistory;
;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;



@WebServlet(name = "CustomerDetailServlet", urlPatterns = {"/Manager/CustomerDetail"})

public class CustomerDetailServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BanCustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BanCustomerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        AccountDAO dao = new AccountDAO();
        Account account = new Account();
        account = dao.getAccountById(id);
        List<Address> listAddress = dao.getUserListAddres(id);
        List<ChangeHistory> listHistory = dao.getChangeHistoryByUserId(id);
        request.setAttribute("ListAddress", listAddress);
        request.setAttribute("listHistory", listHistory);
        request.setAttribute("account", account);
        request.getRequestDispatcher("../ManagerRole/MKT/customerdetail.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty()) {
            AccountDAO accountDAO = new AccountDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Account account = accountDAO.getAccountById(id);
            if (action.equals("changeInfo")) {
                String fullname = request.getParameter("fullname");
                String phone = request.getParameter("phone");
                account = accountDAO.changeAccount(id, fullname, phone);
                List<Address> listAddress = accountDAO.getUserListAddres(id);
                request.setAttribute("ListAddress", listAddress);
                session.setAttribute("account", account);
                request.setAttribute("mess", "Update sucessfully");
                List<ChangeHistory> listHistory = accountDAO.getChangeHistoryByUserId(id);
                request.setAttribute("listHistory", listHistory);
                request.getRequestDispatcher("CustomerDetail.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("../ManagerRole/MKT/customerdetail.jsp").forward(request, response);
            }

        }

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
