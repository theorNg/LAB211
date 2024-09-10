/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constant.Constant;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Flight;

/**
 *
 * @author win
 */
public class FlightList implements Serializable {
    
    ArrayList<Flight> listFlight = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
    
    public ArrayList<Flight> getListFlight() {
        return listFlight;
    }
    
    public Flight checkFlight(List<Flight> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber().equalsIgnoreCase(id)) {
                return list.get(i);
            }
        }
        return null;
    }
    
    public boolean isDuplicateFlight(String city, String desCity, String Time) {
        for (int i = 0; i < listFlight.size(); i++) {
            if (listFlight.get(i).getDeparture().equalsIgnoreCase(city)
                    && listFlight.get(i).getDestination().equalsIgnoreCase(desCity)
                    && dateFormat.format(listFlight.get(i).getDepartureTime()).equals(Time)) {
                return true;
            }
        }
        return false;
    }
    
    public void addFlight() {
        String choose;
        while (true) {
            String code;
            do {
                code = Validator.getString("Enter Flight number:",
                        "Must be follow as: Fxyzt ", "F\\d{4}");
                if (checkFlight(listFlight, code) == null) {
                    break;
                } else {
                    System.out.println("Flight number is already exist in List");
                }
            } while (true);
            String city = Validator.getString("Enter departure City name:",
                    "City Name can not be blank", "^(?!\\s*$).+");
            String desCity = Validator.getString("Enter Destination City name:",
                    "Destination City Name can not be blank", "^(?!\\s*$).+");

            //DATE MAX can be Input to Departure Time
            Calendar calendar = Calendar.getInstance();
            calendar.set(2100, Calendar.JANUARY, 01);
            Date maxDate = calendar.getTime();
            //CURRENT DATE
            Date currentDate = new Date();
            Date departureTime;
            while (true) {
                departureTime = Validator.getDate("Enter Departure Time date: ",
                        "Departure time must since to(" + dateFormat.format(currentDate) + ")",
                        "Invalid! Format HH:mm dd/mm/yyyy", Constant.FORMAT_DATE,
                        currentDate, maxDate);
                if (departureTime != null) {
                    if (!isDuplicateFlight(city, desCity, dateFormat.format(departureTime))) {
                        break;
                    }
                    System.out.println("Time duplicate.Try again");
                } else {
                    System.err.println("Time can not Empty!");
                }
            }
            
            int duration = Validator.getInt("Enter duration(h):", "Must be >0", "Invalid", 1, Integer.MAX_VALUE);
            String gate = Validator.getString("Enter Gate(A,B,C,D,E):",
                    "just be A ,B ,C ,D ,E ", "[ABCDE]");
            //Truyền Total seat Và in ra theo R_ số seat 
            int totalSeat = Validator.getInt("Enter Total Seat number:",
                    "Must be 50->300 seat",
                    "Invalid!", 50, 300);
            int seatList[] = new int[totalSeat];
            for (int i = 0; i < totalSeat; i++) {
                seatList[i] = (i + 1);
            }
            
            Flight newFlight = new Flight(code, city, desCity, departureTime, duration, gate, seatList);
            listFlight.add(newFlight);
            System.out.println("Add success ! Continue input Flights");
            System.out.println("----------CONTINUE ADD-----------");
            choose = Validator.getString("You want to continue (Y/N): ",
                    "Just Y or N", "[YNyn]");
            if (choose.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
    
    public ArrayList<Flight> searchFlight(String departure, String destination) {
        ArrayList<Flight> list = new ArrayList<>();
        for (int i = 0; i < listFlight.size(); i++) {
            if (listFlight.get(i).getDeparture().equalsIgnoreCase(departure)
                    && listFlight.get(i).getDestination().equalsIgnoreCase(destination)) {
                list.add(listFlight.get(i));
            }
        }
        return list;
    }
    
    public ArrayList<Flight> searchFlight(String departure, String destination, String dateTime) {
        ArrayList<Flight> list = new ArrayList<>();
        for (int i = 0; i < listFlight.size(); i++) {
            if (listFlight.get(i).getDeparture().equalsIgnoreCase(departure)
                    && listFlight.get(i).getDestination().equalsIgnoreCase(destination)
                    && dateFormat.format(listFlight.get(i).getDepartureTime()).equals(dateTime)) {
                list.add(listFlight.get(i));
            }
        }
        return list;
    }
    
    public void saveToFile(String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listFlight);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving Flight data to file: " + file);
        }
    }
    
    public void importFromFile(String file) {
        try {
            System.out.println("Loading file " + file + " to flight list...");
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            // Đọc danh sách từ tệp
            listFlight = (ArrayList<Flight>) oos.readObject();
            oos.close();
            fos.close();
        } catch (Exception i) {
            System.out.println(file + " empty to Flight!");
            return;
        }
    }
    
    public void showListFlight(List<Flight> list) {
        if (list.isEmpty()) {
            System.err.println("The List is empty");
            return;
        } else {
            System.out.println("                  Flight Management                          ");
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("|%-15s|%-20s|%-20s|%-16s|%-10s|%-10s|\n",
                    "Flight number", "Departure city", "Destination city",
                    "Departure time", "Duration", "Gate");
            System.out.println("----------------------------------------------------------------------------");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        }
    }
    
    public void sortAndShowListFlight(List<Flight> list) {
        Collections.sort(list);
        showListFlight(list);
    }
    
    public void upDateFlight() {
        System.out.println("-----------UPDATE FLIGHT-----------");
        String code, choose;
        ArrayList<Flight> newFlight = new ArrayList<>();
        do {
            code = Validator.getString("Enter Flight number:",
                    "Must be follow as: Fxyzt ", "F\\d{4}");
            if (checkFlight(listFlight, code) == null) {
                System.out.println("Not found Flight number !");
                choose = Validator.getString("You want to continue (Y/N): ",
                        "Just Y or N", "[YNyn]");
                if (choose.equalsIgnoreCase("N")) {
                    return;
                }
            } else {
                newFlight.add(checkFlight(listFlight, code));
                showListFlight(newFlight);
                //DATE MAX can be Input to Departure Time
                Calendar calendar = Calendar.getInstance();
                calendar.set(2100, Calendar.JANUARY, 01);
                Date maxDate = calendar.getTime();
                //CURRENT DATE
                Date currentDate = new Date();
                Date departureTime;
                while (true) {
                    departureTime = Validator.getDate("Enter Departure Time date: ",
                            "Departure time must since to(" + dateFormat.format(currentDate) + ")",
                            "Invalid! Format HH:mm dd/mm/yyyy", Constant.FORMAT_DATE,
                            currentDate, maxDate);
                    if (departureTime != null) {
                        if (!isDuplicateFlight(newFlight.get(0).getDeparture(), newFlight.get(0).getDestination(), dateFormat.format(departureTime))) {
                            break;
                        }
                        System.out.println("Time duplicate.Try again");
                    } else {
                        System.err.println("Time can not Empty!");
                    }
                }
                int duration = Validator.getInt("Enter duration(h):", "Must be >0", "Invalid", 1, Integer.MAX_VALUE);
                String gate = Validator.getString("Enter Gate(A,B,C,D,E):",
                        "just be A ,B ,C ,D ,E ", "[ABCDE]");
                choose = Validator.getString("You want to Save Update (Y/N): ",
                        "Just Y or N", "[YNyn]");
                if (choose.equalsIgnoreCase("Y")) {
                    for (int i = 0; i < listFlight.size(); i++) {
                        if (listFlight.get(i).getNumber().equalsIgnoreCase(code)) {
                            listFlight.get(i).setDepartureTime(departureTime);
                            listFlight.get(i).setGate(gate);
                            listFlight.get(i).setDuration(duration);
                            System.err.println("UPDATE SUCCESS!");
                            newFlight.remove(0);
                            newFlight.add(listFlight.get(i));
                            showListFlight(newFlight);
                            return;
                        }
                    }
                }else{
                    System.err.println("Update Fail!");
                    return;
                }
            }
        } while (true);
        
    }
}
