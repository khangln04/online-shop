/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DoanManhTai
 */
public class CartItem {
    private int id;
    private int product_id;
    private int cart_id;
    private int quantity;
    private String size;
    private Cart cart;
    private Product product;
    public CartItem() {
    }

    public CartItem(int id, int product_id, int cart_id, int quantity, String size, Cart cart, Product product) {
        this.id = id;
        this.product_id = product_id;
        this.cart_id = cart_id;
        this.quantity = quantity;
        this.size = size;
        this.cart = cart;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public float getTotalPrice() {
        return product.getPrice()*quantity;
    }
}
