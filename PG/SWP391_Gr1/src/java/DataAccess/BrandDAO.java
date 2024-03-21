/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Brand;
import Models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tuant
 */
public class BrandDAO extends DBContext {

    public ArrayList<Brand> getBrandNameAndNumber() {
        ArrayList<Brand> brand = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.id,COUNT(x.id) as 'brandNumber',\n"
                    + "c.bname as 'brandName'\n"
                    + "FROM (SELECT c.id,p.name,p.price,p.description\n"
                    + "FROM Brands c join Product p\n"
                    + "on c.id=p.brand_id\n"
                    + "group by c.id,p.id,c.id,c.bname,p.brand_id,\n"
                    + "p.name,p.description,p.price,p.quantity,p.gender) x \n"
                    + "join Brands c \n"
                    + "on x.id=c.id\n"
                    + "group by x.id,c.id,c.bname";
            stm = connection.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                Brand b = new Brand();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("brandName"));
                b.setBrandNumber(rs.getInt("brandNumber"));
                brand.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return brand;
    }
}
