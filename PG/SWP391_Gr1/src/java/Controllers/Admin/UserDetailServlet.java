/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DataAccess.AddressDAO;
import DataAccess.UserDAO;
import Models.Account;
import Models.Address;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author fpt
 */
public class UserDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet UserDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetailServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        UserDAO userDAO = new UserDAO();
        User u = userDAO.getUserDetailById(Integer.parseInt(id));
        AddressDAO addressDAO = new AddressDAO();
        Address a = addressDAO.getAddressByIdUser(Integer.parseInt(id));
        request.setAttribute("a", a);
        request.setAttribute("u", u);
        request.getRequestDispatcher("./UserDetail.jsp").forward(request, response);
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
        try {
            // Retrieve user ID from the request parameter
            String idParam = request.getParameter("accId");

            // Check if the id parameter is null or empty
            if (idParam == null || idParam.isEmpty()) {
                // Handle the case where the ID is missing
                response.sendRedirect("ErrorPage"); // Replace "ErrorPage" with the actual error page
                return;
            }

            // Parse the user ID
            int id = Integer.parseInt(idParam);

            // Retrieve updated values from the form
            String status = request.getParameter("statusRadios");
            String role = request.getParameter("role"); // Corrected parameter name

            // Update the user details in the database
            UserDAO userDao = new UserDAO();
            boolean updateResult = userDao.updateUserDetail(id, Boolean.parseBoolean(status), role);

            // Forward to UserDetail.jsp with success or failure message
            if (updateResult) {
                // Set success attribute and forward to UserDetail.jsp
                request.setAttribute("updateSuccess", true);
                request.setAttribute("messSuccess", "Update user successfully!");
            } else {
                // Set failure attribute and forward to UserDetail.jsp
                request.setAttribute("updateSuccess", false);
                request.setAttribute("messError", "Update post failed!");
            }

            response.sendRedirect("UserDetail?id=" + idParam);
        } catch (Exception e) {
            // Handle exceptions, log or redirect to error page
            
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
