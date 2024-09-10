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
public class Event implements Serializable,Comparable<Event>{
    private int ID;
    private String name;
    private Date date;
    private String location;
    private int numberOfAttedees;
    private Status status;

    public Event(int ID, String name, Date date, String location, int numberOfAttedees, Status status) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.location = location;
        this.numberOfAttedees = numberOfAttedees;
        this.status = status;
    }

    public Event() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfAttedees() {
        return numberOfAttedees;
    }

    public void setNumberOfAttedees(int numberOfAttedees) {
        this.numberOfAttedees = numberOfAttedees;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Event o) {
        if(date.compareTo(o.date)==0){
            return name.compareTo(o.name);
        }
        return date.compareTo(date);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Event{" + "ID=" + ID + ", name=" + name + ", date=" + dateFormat.format(date) +
                ", location=" + location + ", numberOfAttedees=" + numberOfAttedees + ", status=" + status.getValue() + '}';
    }

    

}
