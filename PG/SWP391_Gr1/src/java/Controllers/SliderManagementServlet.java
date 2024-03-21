/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DataAccess.SliderDAO;
import Models.Slider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Datnt
 */
public class SliderManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        HttpSession session = request.getSession();
        try {
            SliderDAO sliderDAO = new SliderDAO();
            int endPage = 0;
            String indexs = request.getParameter("index");
            if (indexs == null) {
                indexs = "1";
            }
            int count = sliderDAO.getTotalSlider();
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }

            int index = Integer.parseInt(indexs);
            if (session.getAttribute("account") != null) {
                ArrayList<Slider> sliders = sliderDAO.getAllSliderList(index);
                request.setAttribute("sliders", sliders);
                url = "indexAdmin.jsp";
            } else {
                response.sendRedirect("../login.jsp");
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("index", index);
            request.setAttribute("totalSlider", count);
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
