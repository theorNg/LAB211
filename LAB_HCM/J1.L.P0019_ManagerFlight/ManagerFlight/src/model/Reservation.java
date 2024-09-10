/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class Reservation implements Serializable {

    private String ID;
    private Flight selectedFlight;  //Primary key 
    private Passenger traveller;
    private String Gate;
    private int status;
    private int[] selectedSeats;

    public Reservation() {
    }

    public Reservation(String ID, Flight selectedFlight, Passenger traveller, String Gate, int status, int[] selectedSeats) {
        this.ID = ID;
        this.selectedFlight = selectedFlight;
        this.traveller = traveller;
        this.Gate = Gate;
        this.status = status;
        this.selectedSeats = selectedSeats;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public Passenger getTraveller() {
        return traveller;
    }

    public void setTraveller(Passenger traveller) {
        this.traveller = traveller;
    }

    public String getGate() {
        return Gate;
    }

    public void setGate(String Gate) {
        this.Gate = Gate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int[] getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(int[] selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public int[] getNotSelectedSeats() {
        if (selectedSeats == null) {
            return selectedFlight.getSeats();
        }
        int sizeArray = selectedFlight.getSeats().length - selectedSeats.length;
        if (sizeArray == 0) {
            return null;
        }
        int notSelectedSeats[] = new int[sizeArray];
        //System.out.println("xxxxxxxxxx: "+ sizeArray);
        int k = 0;
        for (int elementA : selectedFlight.getSeats()) {
            boolean foundInB = false;
            for (int elementB : selectedSeats) {
                if (elementA == elementB) {
                    foundInB = true;
                    break;
                }
            }
            if (!foundInB) {
                notSelectedSeats[k] = elementA;
                k++;
            }
        }
        return notSelectedSeats;
    }

    public void showListSeat(int listSeat[]) {
        for (int i = 0; i < listSeat.length; i++) {
            System.out.print(listSeat[i] + "    ");
            if ((i+1) % 5 == 0) {
                System.out.println("");
            }
        }
    }
}
