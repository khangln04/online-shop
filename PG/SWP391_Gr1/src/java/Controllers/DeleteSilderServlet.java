/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DataAccess.SliderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Datnt
 */
public class DeleteSilderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String url = "";
            String sliderS = request.getParameter("id");
            int sliderId = 0;
            if (sliderS != null) {
                sliderId = Integer.parseInt(sliderS);
            }
            SliderDAO sliderDAO = new SliderDAO();
            boolean result = sliderDAO.deleteSliderById(sliderId);
            if (result) {
                url = "slider";
                String msg = "Xoa thanh cong slider";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                // Neu xoa bi loi. ve lai trang login.
                url = "login";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
