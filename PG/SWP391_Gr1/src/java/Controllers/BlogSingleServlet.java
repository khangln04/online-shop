/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DataAccess.BlogDAO;
import DataAccess.CommentDAO;
import Models.Account;
import Models.Blog;
import Models.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuant
 */
@WebServlet(name = "BlogSingleServlet", urlPatterns = {"/blog-single"})
public class BlogSingleServlet extends HttpServlet {

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
            out.println("<title>Servlet BlogSingleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogSingleServlet at " + request.getContextPath() + "</h1>");
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
        BlogDAO blogDAO = new BlogDAO();
        String id = request.getParameter("id");
        Blog blog = blogDAO.getBlogById(Integer.parseInt(id));
        CommentDAO commentDAO = new CommentDAO();

        // pagination  
        int count = commentDAO.getTotalCmt();
        int endPage = count / 3;
        if (count % 3 != 0) {
            endPage++;
        }
        request.setAttribute("endpage", endPage);
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index;
        try {

            index = Integer.parseInt(indexPage);

            ArrayList<Comment> comment = commentDAO.getPaginationCmt(id, index);
            for (Comment commen : comment) {
                ArrayList<Comment> sub = commentDAO.getSubComment(commen.getId());
                commen.setComments(sub);
            }
            request.setAttribute("comment", comment);
            request.setAttribute("tagindex", index);

        } catch (Exception e) {
            System.out.println(e);
        }

        request.setAttribute("blog", blog);
        request.getRequestDispatcher("blog-single.jsp").forward(request, response);
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
        CommentDAO commentDAO = new CommentDAO();
        BlogDAO blogDAO = new BlogDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        String message = request.getParameter("message");
        String blogId = request.getParameter("blogId");

        String cmtId = request.getParameter("cmtId");
        Blog blog = blogDAO.getBlogById(Integer.parseInt(blogId));

        if (a == null) {
            request.setAttribute("err", "Hãy đăng nhập để có thể bình luận");
        }else
        if (cmtId == null) {
            request.setAttribute("err", "Bạn đã bình luận, phản hồi thành công");
            commentDAO.addComment(message, a.getId(), blogId);
        } else
        if (cmtId != null) {
            request.setAttribute("err", "Bạn đã bình luận, phản hồi thành công");
            commentDAO.addReplyComment(message, a.getId(), blogId, cmtId);
        }

        // pagination  
        int count = commentDAO.getTotalCmt();
        int endPage = count / 3;
        if (count % 3 != 0) {
            endPage++;
        }
        request.setAttribute("endpage", endPage);
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index;
        try {

            index = Integer.parseInt(indexPage);

            ArrayList<Comment> comment = commentDAO.getPaginationCmt(blogId, index);
            for (Comment commen : comment) {
                ArrayList<Comment> sub = commentDAO.getSubComment(commen.getId());
                commen.setComments(sub);
            }
            request.setAttribute("comment", comment);
            request.setAttribute("tagindex", index);

        } catch (Exception e) {
            System.out.println(e);
        }

        request.setAttribute("blog", blog);
        request.getRequestDispatcher("blog-single.jsp").forward(request, response);
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
