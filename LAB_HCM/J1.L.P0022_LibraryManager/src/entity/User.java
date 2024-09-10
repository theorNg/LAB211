/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author win
 */
public class User implements Serializable,Comparable<User>{
    private String userID;
    private String name;
    private Date dateOfBirth;
    private String phone;
    private String email;
    private boolean active_user;

    public User() {
    }

    public User(String userID, String name, Date dateOfBirth, String phone, String email, boolean active_user) {
        this.userID = userID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.active_user = active_user;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive_user() {
        return active_user;
    }

    public void setActive_user(boolean active_user) {
        this.active_user = active_user;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        return "User{" + userID + ", " + name + ", " + formatDate.format(dateOfBirth) + ", " + phone + ", " + email + ", " + active_user + '}';
    }

    @Override
    public int compareTo(User o) {
        return userID.compareTo(o.userID);
    }
    
}
