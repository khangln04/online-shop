/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DataAccess.BrandDAO;
import DataAccess.CategoryDAO;
import DataAccess.ProductDAO;
import DataAccess.SliderDAO;
import Models.Brand;
import Models.Category;
import Models.Product;
import Models.Slider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author tuant
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        SliderDAO sliderDAO = new SliderDAO();
        BrandDAO brandDAO = new BrandDAO();
        
        ArrayList<Slider> sliders = sliderDAO.getAllSliderToHome();
        
        ArrayList<Category> categorys = categoryDAO.getAllCategory();

        for (Category category : categorys) {
            ArrayList<Category> test = categoryDAO.getSubcategory(category.getId());
            category.setSubCategory(test);
        }

        ArrayList<Product> products = productDAO.getRecomendProduct();
        ArrayList<Brand> brand = brandDAO.getBrandNameAndNumber();

        String subCatrgoryId = request.getParameter("sub");
        String bid = request.getParameter("bid");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String sortBy = request.getParameter("sortBy");
        String searchProduct = request.getParameter("searchProduct");

        if (subCatrgoryId == null && bid == null && (from == null || to == null) && searchProduct == null) {
            //pagingnation
            int count = productDAO.getTotalProduct();
            int endPage = count / 6;
            if (count % 6 != 0) {
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
                if (sortBy == null) {
                    ArrayList<Product> list = productDAO.pagingProduct(index);
                    request.setAttribute("product", list);
                    request.setAttribute("tagindex", index);
                    request.setAttribute("HomeActive", true);
                } else if (sortBy.contains("date")) {
                    ArrayList<Product> list = productDAO.pagingProductOrderByDate(index, " desc ");
                    request.setAttribute("product", list);
                    request.setAttribute("tagindex", index);
                    request.setAttribute("sortBy", true);
                    request.setAttribute("isActive", true);
                } else if (sortBy.contains("maxPrice")) {
                    ArrayList<Product> list = productDAO.pagingProductOrderByPrice(index, " desc ");
                    request.setAttribute("product", list);
                    request.setAttribute("tagindex", index);
                    request.setAttribute("sortByMaxPrice", true);
                    request.setAttribute("MaxActive", true);
                } else if (sortBy.contains("minPrice")) {
                    ArrayList<Product> list = productDAO.pagingProductOrderByPrice(index, " asc ");
                    request.setAttribute("product", list);
                    request.setAttribute("tagindex", index);
                    request.setAttribute("sortByMinPrice", true);
                    request.setAttribute("MinActive", true);
                }

            } catch (Exception e) {
            }

        } else if (subCatrgoryId != null) {
            int count = productDAO.getTotalProductByCId(subCatrgoryId);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            } else if (count == 0) {
                endPage = 1;
                request.setAttribute("errUrl", "https://tuha.vn/storage/san-pham-khong-co-san-het-hang.jpg");
            }
            request.setAttribute("endpage", endPage);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index;
            try {
                index = Integer.parseInt(indexPage);
                ArrayList<Product> list = productDAO.getProductsByCateId(subCatrgoryId, index);
                request.setAttribute("product", list);
                request.setAttribute("tagindex", index);
                request.setAttribute("sub", subCatrgoryId);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (bid != null) {
            System.out.println(bid);
            int count = productDAO.getTotalProductByBId(bid);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            } else if (count == 0) {
                endPage = 1;
                request.setAttribute("errUrl", "https://tuha.vn/storage/san-pham-khong-co-san-het-hang.jpg");
            }
            request.setAttribute("endpage", endPage);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index;
            try {
                index = Integer.parseInt(indexPage);
                ArrayList<Product> list = productDAO.getProductsByBId(bid, index);
                request.setAttribute("tagindex", index);
                request.setAttribute("product", list);
                request.setAttribute("bid", bid);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (from != null || to != null) {
            int count = productDAO.getTotalProductByPrice(from, to);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            } else {
                endPage = 1;
                request.setAttribute("errUrl", "https://tuha.vn/storage/san-pham-khong-co-san-het-hang.jpg");
            }
            request.setAttribute("endpage", endPage);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index;
            try {
                index = Integer.parseInt(indexPage);
                ArrayList<Product> list = productDAO.getProductsByPrice(from, to, index);
                request.setAttribute("tagindex", index);
                request.setAttribute("product", list);
                request.setAttribute("from", from);
                request.setAttribute("to", to);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (searchProduct != null) {
            int count = productDAO.getTotalProductBySearch(searchProduct);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            } else {
                endPage = 1;
                request.setAttribute("errUrl", "https://tuha.vn/storage/san-pham-khong-co-san-het-hang.jpg");
            }
            request.setAttribute("endpage", endPage);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index;
            try {
                index = Integer.parseInt(indexPage);
                ArrayList<Product> list = productDAO.SearchProduct(index, searchProduct);
                request.setAttribute("tagindex", index);
                request.setAttribute("product", list);
                request.setAttribute("s", searchProduct);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        request.setAttribute("products", products);
        
        request.setAttribute("slider", sliders);
        request.setAttribute("category", categorys);
        request.setAttribute("brand", brand);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
