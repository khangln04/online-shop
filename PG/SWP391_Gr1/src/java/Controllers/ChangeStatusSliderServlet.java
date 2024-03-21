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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Datnt
 */
public class ChangeStatusSliderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String url = "";
            HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
            String action = request.getParameter("action") == null ? "" : request.getParameter("action");
            if (session != null && session.getAttribute("account") != null) {
                // Lấy thông tin tài khoản từ session

                switch (action) {
                    case "":
                        // Chuyển hướng sang trang hiển thị thông tin cá nhân
                        url = "indexAdmin.jsp";
                        break;
                    case "disableSlider":
                        disableSlider(request, response);
                        break;
                    case "enableSlider":
                        enableSlider(request, response);
                        break;
                }
            } else {
                // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
                url = "login";
            }
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

    private void disableSlider(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "";
            String id = request.getParameter("id");
            if (id != null) {
                int sid = Integer.parseInt(id);
                SliderDAO sliderDAO = new SliderDAO();
                boolean result = sliderDAO.disableSlider(sid);
                url = "slider";

                if (result) {
                    String msg = "Da an bai viet id = " + id;
                    request.setAttribute("msg", msg);
                } else {
                     String msg = "Khong the an bai viet = " + id;
                     request.setAttribute("msgError", msg);
                }
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableSlider(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "";
            String id = request.getParameter("id");
            if (id != null) {
                int sid = Integer.parseInt(id);
                SliderDAO sliderDAO = new SliderDAO();
                boolean result = sliderDAO.enableSlider(sid);
                url = "slider";

                if (result) {
                    String msg = "Da an bai viet id = " + id;
                    request.setAttribute("msg", msg);
                } else {
                     String msg = "Khong the an bai viet = " + id;
                     request.setAttribute("msgError", msg);
                }
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
