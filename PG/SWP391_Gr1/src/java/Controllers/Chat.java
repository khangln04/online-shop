package Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DataAccess.MessageDAO;
import Models.Account;
import Models.MessageDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DoanManhTai
 */
public class Chat extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MessageDAO messageDAO = new MessageDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        String receiverRequest = request.getParameter("receiver");
        String productid = request.getParameter("productid");
        if (productid == null) {
            productid = "0";
        }

        List<Account> userMsg = new ArrayList<Account>();
        userMsg = messageDAO.getUserMessages(account.getId());

        int receiver = 32;
        String receiverName = "";
        if (account.getId() == 32) {
            receiver = userMsg.get(0).getId();
            receiverName = userMsg.get(0).getFullname();

            if (receiverRequest != null) {
                receiver = Integer.parseInt(receiverRequest);
                for (Account account1 : userMsg) {
                    if (account1.getId() == receiver) {
                        receiverName = account1.getFullname();
                    }
                }
            }
        }
        request.setAttribute("receiver", receiver);
        request.setAttribute("receiverName", receiverName);
        request.setAttribute("productid", productid);

        request.setAttribute("userMsg", userMsg);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MessageDAO messageDAO = new MessageDAO();
        HttpSession session = request.getSession();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        String receiver = jsonString.substring(12, 14);

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        ArrayList<MessageDTO> listMessageDTO = messageDAO.getMessagesBySenderId(account.getId(), Integer.parseInt(receiver));

        Gson gson = new Gson();
        String json = gson.toJson(listMessageDTO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
