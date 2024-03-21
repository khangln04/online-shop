/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Common.Encoding.encodePassword;
import Common.Helper;
import static Common.Helper.convertToBase64;
import DataAccess.AccountDAO;
import Models.Account;
import Models.Address;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;

/**
 *
 * @author toden
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
        } else {
            if (request.getParameter("p") != null) {
                if (request.getParameter("err") != null && request.getParameter("err").equals("ip")) {
                    request.setAttribute("err", "incorrect password");
                }
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            } else {
                AccountDAO dao = new AccountDAO();
                int userId = account.getId();
                account = dao.getAccountById(userId);
                List<Address> listAddress = dao.getUserListAddres(userId);
                request.setAttribute("ListAddress", listAddress);
                request.setAttribute("account", account);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty()) {
            AccountDAO accountDAO = new AccountDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Account account = (Account) session.getAttribute("account");
            switch (action) {
                case "changeInfo":
                    String fullname = request.getParameter("fullname");
                    String phone = request.getParameter("phone");

                    boolean isPhoneValid = Helper.validatePhoneNumber(phone);
                    if (isPhoneValid) {
                        account = accountDAO.changeAccount(id, fullname, phone);
                        if (account != null) {
                            String msg = "Update profile successfully";
                            request.setAttribute("msg", msg);
                        } else {
                            String msgEr = "Update profile failed";
                            request.setAttribute("msgError", msgEr);
                        }
                    } else {
                        String msgEr = "Phone number not valid";
                        request.setAttribute("msgError", msgEr);
                    }

                    List<Address> listAddress = accountDAO.getUserListAddres(id);

                    request.setAttribute("ListAddress", listAddress);
                    session.setAttribute("account", account);
                    request.getRequestDispatcher("profile.jsp").include(request, response);
                    break;
                case "changePassword":
                    String oldpass = request.getParameter("oldpass");
                    String newpass = request.getParameter("newpass");
                    String cfPass = request.getParameter("cfPass");
                    String passEndcode = encodePassword(newpass);

                    account = accountDAO.changePass(id, oldpass, newpass, cfPass, passEndcode);
                    if (account == null) {
                        request.setAttribute("err", "incorrect password");
                        response.sendRedirect("profile?p=1&err=ip");
                    } else {
                        session.setAttribute("account", null);
                        response.sendRedirect("profile?p=1");
                    }
                    break;
                case "changeAvatar":
                    // Get the file part from the request
                    Part filePart = request.getPart("file");

                    // Convert the input stream of the file to base64
                    String base64Image = convertToBase64(filePart.getInputStream());
                    account = accountDAO.changeAvatar(id, base64Image);
                    session.setAttribute("account", account);
                    response.sendRedirect("profile");
                    break;
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
