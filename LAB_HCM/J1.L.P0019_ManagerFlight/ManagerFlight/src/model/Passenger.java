/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author win
 */
public class Passenger implements Serializable{
    private String ID;
    private String Name;
    private Date BD;

    public Passenger(String ID, String Name, Date BD) {
        this.ID = ID;
        this.Name = Name;
        this.BD = BD;
    }

    public Passenger() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Date getBD() {
        return BD;
    }

    public void setBD(Date BD) {
        this.BD = BD;
    }
    
}
