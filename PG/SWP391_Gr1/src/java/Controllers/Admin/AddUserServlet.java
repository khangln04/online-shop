/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import static Common.Encoding.encodePassword;
import static Common.Helper.convertToBase64;
import static Common.SendMail.sendMail;
import static Controllers.SignupServlet.generateOTP;
import static Controllers.SignupServlet.systemOTP;
import DataAccess.AccountDAO;
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
import jakarta.servlet.http.Part;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fpt
 */
public class AddUserServlet extends HttpServlet {

    public String systemPassword = "";
    public boolean checkSendMail = false;

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
            out.println("<title>Servlet AddUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUserServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("addUserDetail.jsp").forward(request, response);
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
        AccountDAO x = new AccountDAO();
        Account account = new Account();
        Account accountSignUp = new Account();
        UserDAO u = new UserDAO();

        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String addressInput = request.getParameter("address");
        String status = request.getParameter("status");

        // Extract address components
        String[] addressComponents = addressInput.split(",");
        String street = addressComponents[0];
        String ward = addressComponents[1];
        String district = addressComponents[2];
        String city = addressComponents[3];

        Part filePart = request.getPart("file");

        if (x.checkAccountExist(email)) {
            request.setAttribute("alert1", "Email already exist");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (isValidEmail(email)) {
                accountSignUp.setFullname(fullName);
                accountSignUp.setMail(email);
                accountSignUp.setPhone(phone);
                accountSignUp.setGender(gender);
                accountSignUp.setRole(role);
                accountSignUp.setStatus(Boolean.parseBoolean(status));
                accountSignUp.setCity(city);
                accountSignUp.setStreet(street);
                accountSignUp.setDistrict(district);
                accountSignUp.setWard(ward);

                // Convert the image to base64
                String base64Image = convertToBase64(filePart.getInputStream());
                // Save the base64 image to the account object
                accountSignUp.setAvatar(base64Image);

                String newPassword = generateRandomPassword(12);
                String passEndcode = encodePassword(newPassword);
                accountSignUp.setPassword(passEndcode);
                sendMail(email, newPassword, "Password C?a B?n:");
                request.setAttribute("email", email);
                request.setAttribute("alert1", "check gmail pls!");
                int result = x.addNewUser(accountSignUp);
                if (result == 0) {
                    request.setAttribute("alert2", "Error");
                }
                request.setAttribute("alert1", "Add User Successfully");
                request.getRequestDispatcher("addUserDetail.jsp").forward(request, response);
            } else {
                request.setAttribute("alert2", "Email khong hop le!");
                request.getRequestDispatcher("addUserDetail.jsp").forward(request, response);
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

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    private String generateRandomPassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than zero.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

}
