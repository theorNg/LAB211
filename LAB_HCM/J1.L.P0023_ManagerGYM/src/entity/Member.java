/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author win
 */
public class Member implements Serializable,Comparable<Member>{
    private String id; // GYXXXXX (x ís digit ) 
    private String name;
    private String address;
    private String phone; //contain 10 digit , and begin 0
    private String type;

    public Member() {
    }

    public Member(String id, String name, 
            String address, String phone, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Member{"  + id + ", " + name + ", " + address + ", " + phone + ", " + type + '}';
    }

    @Override
    public int compareTo(Member o) {
        String []array1 = name.split(" ");
        String str1 = array1[array1.length-1].substring(0,1); 
        String []array2 = o.name.split(" ");
        String str2 = array2[array2.length-1].substring(0,1); 
        return str1.compareTo(str2);
    }
    
}
