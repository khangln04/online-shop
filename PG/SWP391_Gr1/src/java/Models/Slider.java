/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author tuant
 */
public class Slider {
    private int sid;
    private String title ;
    private String img;
    private String content;
    private int user_id;
    private Date createOn;
    private int status;

    public Slider() {
    }

  

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }


    public int getSid() {
        return sid;
    }

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public int getStatus() {
        return status;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
