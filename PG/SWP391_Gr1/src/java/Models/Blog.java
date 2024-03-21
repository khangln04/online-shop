/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author tuant
 */
public class Blog {

    private int id;
    private String title, shortContent, content, image, author;
    private Date publishedDate;
    private Time publishedTime;
    private int userId;
    private int isActive;

    public Blog() {
    }

    public int getId() {
        return id;
    }

    public Time getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Time publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getTitle() {
        return title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

}
