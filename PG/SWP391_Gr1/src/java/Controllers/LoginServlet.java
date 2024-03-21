package Controllers;

import static Common.Encoding.encodePassword;
import DataAccess.AccountDAO;
import Models.Account;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO account = new AccountDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String passEndcode = encodePassword(password);

        Account x = account.getAccount(username, passEndcode);
        
        if (x == null) {
            request.setAttribute("alert", "Wrong user or password");
            request.getRequestDispatcher("login.jsp").include(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("account", x);
        
        if (x.getRole() == null) {
            response.sendRedirect("home");
        } else if(x.getRole().equals("Admin")){
            response.sendRedirect("indexAdmin");
        }
    }

    private boolean isValidAdmin(String username, String password) {
        AccountDAO a = new AccountDAO();
        Account x = a.getAccount(username, password);

        // Check if the username and password match a valid admin account
        // Return true if they match, false otherwise
        return x != null; //&& x.getRole().equals("admin") ;
    }

    private boolean isValidUser(String username, String password) {
        AccountDAO a = new AccountDAO();
        Account x = a.getAccount(username, password);

        // Check if the username and password match a valid user account
        // Return true if they match, false otherwise
        return x != null; // && x.getRole().equals("user") ;
    }
//////
}
