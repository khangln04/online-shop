/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author datnt
 */
public class OrderDetail {
    private String productName, size, color, url;
    private int quantity, productId, orderId;
    private double productPrice, productTotalPrice;

    public OrderDetail() {
    }

    public OrderDetail(String productName, String size, String color, String url, int quantity, int productId, int orderId, double productPrice, double productTotalPrice) {
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.url = url;
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
        this.productPrice = productPrice;
        this.productTotalPrice = productTotalPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductTotalPrice(double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    
    public String getProductName() {
        return productName;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getUrl() {
        return url;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getProductTotalPrice() {
        return productTotalPrice;
    }
    
}