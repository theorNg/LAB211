/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import constant.Constant;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author win
 */
public class Flight implements Serializable,Comparable<Flight>{
    private String number;
    private String departure;
    private String destination;
    private Date departureTime;
    private int duration;
    private String Gate;
    private int[] seats;
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);

    public Flight() {
    }

    public Flight(String number, String departure, String destination, Date departureTime, int duration, String Gate, int[] seats) {
        this.number = number;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.Gate = Gate;
        this.seats = seats;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGate() {
        return Gate;
    }

    public void setGate(String Gate) {
        this.Gate = Gate;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return String.format("|%-15s|%-20s|%-20s|%-16s|%-10s|%-10s|\n",
                    number, departure, destination,
                    dateFormat.format(departureTime), duration,Gate);
    }

    @Override
    public int compareTo(Flight t) {
        return -departureTime.compareTo(t.departureTime);
    }
           
}
