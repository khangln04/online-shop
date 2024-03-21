/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Common.Helper.convertToBase64;
import DataAccess.SliderDAO;
import Models.Account;
import Models.Slider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Datnt
 */
@MultipartConfig()
public class CreateSliderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            String url = "";
            HttpSession session = request.getSession();
            Slider slider = new Slider();
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Part filePart = request.getPart("linkImage");
            String base64Image = convertToBase64(filePart.getInputStream());
            
            int status = 1;
            Account x = (Account) session.getAttribute("account");

            SliderDAO sliderDAO = new SliderDAO();
            boolean result;
            result = sliderDAO.createSlider(title, content, base64Image, x.getId(), status);
            if (result) {
                url = "slider";
            } else {
                System.out.println("Erorr bug in create slider");
            }
            response.sendRedirect(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
