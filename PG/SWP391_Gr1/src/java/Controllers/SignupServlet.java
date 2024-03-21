/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Common.Encoding.encodePassword;
import static Common.SendMail.sendMail;
import DataAccess.AccountDAO;
import DataAccess.CartDao;
import DataAccess.WishListDao;
import Models.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author buima
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {

    public static String systemOTP = "";
    public boolean checkSendMail = false;
    Account accountSignUp = new Account();

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
            out.println("<title>Servlet SignupServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String otp = request.getParameter("otp");
        
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String street = request.getParameter("street");

        AccountDAO x = new AccountDAO();
        Account account = new Account();

        if (checkSendMail && accountSignUp != null) {
            if (otp != null) {
                if (otp.equals(systemOTP)) {
                    accountSignUp.setPassword(encodePassword(accountSignUp.getPassword()));
                    int result = x.signUp(accountSignUp);
                    if (result == 0) {
                        request.setAttribute("alert1", "Error");
                    }
                    request.setAttribute("alert1", "dang nhap thanh cong, dang ki thanh cong");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("alert1", "Vui long nhap lai OTP!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }

        if (!repassword.equals(password)) {
            request.setAttribute("alert1", "Password do not match");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (x.checkAccountExist(email)) {
                request.setAttribute("alert1", "Account already exist");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                if (isValidEmail(email)) {
                    systemOTP = generateOTP(6);
                    accountSignUp.setFullname(fullname);
                    accountSignUp.setMail(email);
                    accountSignUp.setPassword(password);
                    accountSignUp.setPhone(phone);
                    accountSignUp.setCity(city);
                    accountSignUp.setStreet(street);
                    accountSignUp.setDistrict(district);
                    account.setWard(ward);

                    sendMail(email, systemOTP, "OTP:");
                    request.setAttribute("email", email);
                    request.setAttribute("alert1", "check gmail pls!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    checkSendMail = true;
                } else {
                    request.setAttribute("alert1", "Email khong hop le!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String generateOTP(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return otp.toString();
    }
}
