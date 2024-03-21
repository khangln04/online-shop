/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Common.Helper;
import Common.VnPay;
import DataAccess.AddressDAO;
import DataAccess.CartDao;
import DataAccess.OrderDAO;
import Models.Account;
import Models.Address;
import Models.CartItem;
import Models.Order;
import Models.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author DoanManhTai
 */
public class CartController extends HttpServlet {

    public static String fisrtAddress = null;

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
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
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
        ArrayList<String> listAddressNamePhone = new ArrayList<>();
        String cartItem_Id = request.getParameter("itemId");
        String product_Id = request.getParameter("productId");
        String msg = request.getParameter("msg");
        String quantity = request.getParameter("quantity");

        CartDao cartDao = new CartDao();
        AddressDAO addressDao = new AddressDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        if (cartItem_Id != null && quantity != null) {
            CartItem getCartItem = cartDao.getCartItemByid(Integer.parseInt(cartItem_Id), account.getCartId());
            int quantityCart = getCartItem.getQuantity();
            if (quantity.equals("1")) {
                quantityCart++;
            } else if (quantity.equals("-1")) {
                quantityCart--;
            }
            cartDao.updateCartItem(quantityCart, Integer.parseInt(cartItem_Id), account.getCartId());
        }

        List<Address> listAddress = addressDao.getAddressByid(account.getId());
        for (Address listAddres : listAddress) {
            String address = account.getFullname() + ", (+84)" + account.getPhone() + "," + listAddres.getStreet() + "," + listAddres.getWard() + "," + listAddres.getDistrict() + "," + listAddres.getCity();
            listAddressNamePhone.add(address);
        }
        fisrtAddress = listAddressNamePhone.get(0);

        String requestJson = Helper.convertToStringArray(listAddressNamePhone).toString();

        if (cartItem_Id != null && quantity == null) {
            cartDao.deleteItem(account.getCartId(), Integer.parseInt(cartItem_Id));
        }
        if (product_Id != null) {
            CartItem checkItemExits = cartDao.checkItemExits(account.getCartId(), Integer.parseInt(product_Id));
            if (checkItemExits != null) {
                checkItemExits.setQuantity(checkItemExits.getQuantity() + 1);
                cartDao.updateCartItem(checkItemExits);
            } else {
                CartItem newCartItem = new CartItem(0, Integer.parseInt(product_Id), account.getCartId(), 1, null, null, null);
                cartDao.insertCartItem(newCartItem);
            }
        }
        List<CartItem> cartItem = cartDao.getCartItem(account.getCartId());
        request.setAttribute("cartItem", cartItem);
        request.setAttribute("msg", msg);
        request.setAttribute("fisrtAddress", fisrtAddress);
        request.setAttribute("requestJson", requestJson);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        CartDao cartDao = new CartDao();
        OrderDAO orderDao = new OrderDAO();
        String msg = "";
        String addressCurrent = request.getParameter("address");
        if (addressCurrent == "") {
            addressCurrent = fisrtAddress;
        }
        String listCartId = request.getParameter("listCartId");
        String total = request.getParameter("totalPrice");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        String[] itemCartIDArray = listCartId.split(",");
        if (itemCartIDArray[0] == "") {
            msg = "Please choose a product";
            response.sendRedirect("cart?msg=" + msg);
            return;
        }
        String[] splitAddress = addressCurrent.split(",");
        String name = splitAddress[0].toString();
        String phone = splitAddress[1].toString();

        Order newOrder = new Order();
        newOrder.setCustomerName(name);
        newOrder.setAddress(addressCurrent);
        newOrder.setPhone(phone);
        newOrder.setTotal(Double.parseDouble(total));
        newOrder.setUserId(account.getId());

        newOrder = cartDao.createOrder(newOrder);

        for (int i = 0; i < itemCartIDArray.length; i++) {
            CartItem newCartItem = cartDao.getCartItemByid(Integer.parseInt(itemCartIDArray[i]), account.getCartId());
            OrderDetail newOrderDetail = new OrderDetail();

            newOrderDetail.setOrderId(newOrder.getOrderId());
            newOrderDetail.setProductId(newCartItem.getProduct_id());
            newOrderDetail.setQuantity(newCartItem.getQuantity());
            orderDao.insertOrderDetail(newOrderDetail);
            cartDao.deleteItem(account.getCartId(), Integer.parseInt(itemCartIDArray[i]));
        }

        //vpn
        String vnp_Returnurl = getServletContext().getInitParameter("vnp_Returnurl");
        String vnp_Url = getServletContext().getInitParameter("vnp_Url");
        String vnp_TmnCode = getServletContext().getInitParameter("vnp_TmnCode");
        String vnp_HashSecret = getServletContext().getInitParameter("vnp_HashSecret");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        VnPay vnpay = new VnPay();

        vnpay.addRequestData("vnp_Version", vnpay.VERSION);
        vnpay.addRequestData("vnp_Command", "pay");
        vnpay.addRequestData("vnp_TmnCode", vnp_TmnCode);
        int amount = (int) newOrder.getTotal() * 100;

        vnpay.addRequestData("vnp_Amount", String.valueOf(amount));
        vnpay.addRequestData("vnp_CreateDate", sdf.format(cld.getTime()));
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = sdf.format(cld.getTime());
        vnpay.addRequestData("vnp_ExpireDate", vnp_ExpireDate);

        vnpay.addRequestData("vnp_CurrCode", "VND");
        vnpay.addRequestData("vnp_IpAddr", vnpay.getIpAddress(request));
        vnpay.addRequestData("vnp_Locale", "vn");
        vnpay.addRequestData("vnp_OrderInfo", "Thanh toan don hang:" + newOrder.getOrderId());
        vnpay.addRequestData("vnp_OrderType", "other");
        vnpay.addRequestData("vnp_ReturnUrl", vnp_Returnurl);
        double test = newOrder.getOrderId() * 100;
        vnpay.addRequestData("vnp_TxnRef", Integer.toString((int) test));
        String paymentUrl = vnpay.createRequestUrl(vnp_Url, vnp_HashSecret);
        response.sendRedirect(paymentUrl);
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
