/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author datnt
 */
public class Address {
    private String ward, street, district, city;
    private int userId, id;

    public Address() {
    }

    public Address(String ward, String street, String district, String city, int userId) {
        this.ward = ward;
        this.street = street;
        this.district = district;
        this.city = city;
        this.userId = userId;
    }



    public Address(String ward, String street, String district, String city, int userId, int id) {
        this.ward = ward;
        this.street = street;
        this.district = district;
        this.city = city;
        this.userId = userId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public int getUserId() {
        return userId;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address{" + "ward=" + ward + ", street=" + street + ", district=" + district + ", city=" + city + ", userId=" + userId + '}';
    }
    
    public String getUserAddress() {
        return  ward + ", " + street + ", " + district + ", " + city;
    }
}
