/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Account {

    private int id;
    private String fullname, password, phone, role, mail, country, city, street,district,ward, state, avatar;
    int cartId;
    boolean status;
    private String gender;

     private ArrayList<Role> roles = new ArrayList<>();

    public Account() {
    }

    public Account(int id, String fullname, String password, String phone, String role, String mail, String country, String city, String street, String district, String ward, String state, String avatar, int cartId, boolean status) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.mail = mail;
        this.country = country;
        this.city = city;
        this.street = street;
        this.district = district;
        this.ward = ward;
        this.state = state;
        this.avatar = avatar;
        this.cartId = cartId;
        this.status = status;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Account(int id, String fullname, String password, String phone, String role, String mail, String country, String city, String street, String state, String avatar, int cartId) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.mail = mail;
        this.country = country;
        this.city = city;
        this.street = street;
        this.state = state;
        this.avatar = avatar;
        this.cartId = cartId;
    }

	public void setStatus(boolean status) {
        this.status = status;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
    
    

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Account(int id, String fullname, String password, String phone, String role, String mail, int cartId) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.mail = mail;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", fullname=" + fullname + ", password=" + password + ", phone=" + phone + ", role=" + role + ", mail=" + mail + ", country=" + country + ", city=" + city + ", street=" + street + ", state=" + state + ", avatar=" + avatar + ", cartId=" + cartId + ", status=" + status + '}';
    }
}
