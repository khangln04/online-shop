/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AD
 */
public class User {
    private int id;
    private String fullName;
    private String password;
    private String phone;
    private String role;
    private String mail;
    private int cartId;
    private ArrayList<Role> roles = new ArrayList<>();
    private List<Address> address;
    private String avatar;
    private String gender;
    boolean status;

    public User() {
    }
    
    public int getId() {
        return id;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
    
    

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getMail() {
        return mail;
    }

    public int getCartId() {
        return cartId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }  

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
    
     public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullName=" + fullName + ", password=" + password + ", phone=" + phone + ", role=" + role + ", mail=" + mail + ", cartId=" + cartId + ", status=" + status + ", address=" + address + ", avatar=" + avatar + ", gender=" + gender + '}';
    }

}
