/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constant.Constant;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.Flight;
import model.Passenger;
import model.Reservation;

/**
 *
 * @author win
 */
public class ReservationList implements Serializable {

    ArrayList<Reservation> listReservation = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.BIRTHDAY);
    SimpleDateFormat dateFormat1 = new SimpleDateFormat(Constant.FORMAT_DATE);

    public void addReservation(FlightList listFlight) {
        Reservation reservation;
        String choose, city, desCity;
        Flight findFlight = null;
        ArrayList<Flight> list, list2;
        listFlight.showListFlight(listFlight.getListFlight());
        while (true) {
            city = Validator.getString("Enter Departure City :",
                    "City Name can not be blank", "^(?!\\s*$).+");
            desCity = Validator.getString("Enter Destination City name:",
                    "City Name can not be blank", "^(?!\\s*$).+");
            list = listFlight.searchFlight(city, desCity);
            if (list.isEmpty()) {
                System.err.println("No Flight available!!");
                choose = Validator.getString(
                        "Countinue?(Y/N): ",
                        "Just Y or N", "[YNyn]");
                if (choose.equalsIgnoreCase("N")) {
                    return;
                }
            } else {
                break;
            }
        }
        listFlight.showListFlight(list);
        //DATE MAX can be Input to Departure Time
        Calendar calendar = Calendar.getInstance();
        calendar.set(2100, Calendar.JANUARY, 01);
        Date maxDate = calendar.getTime();
        calendar.set(1930, Calendar.JANUARY, 01);
        Date minDate = calendar.getTime();
        //CURRENT DATE
        Date currentDate = new Date();
        Date departureTime;
        while (true) {
            while (true) {
                departureTime = Validator.getDate("Enter Departure Time date: ",
                        "Departure time must since to(" + dateFormat1.format(currentDate) + ")",
                        "Invalid! Format HH:mm dd/mm/yyyy", Constant.FORMAT_DATE,
                        currentDate, maxDate);
                if (departureTime != null) {
                    break;
                } else {
                    System.err.println("Time can not Empty!");
                }
            }
            list2 = listFlight.searchFlight(city, desCity, dateFormat1.format(departureTime));
            if (list2.isEmpty()) {
                System.err.println("No Flight available!!");
                choose = Validator.getString(
                        "Countinue?(Y/N): ",
                        "Just Y or N", "[YNyn]");
                if (choose.equalsIgnoreCase("N")) {
                    return;
                }
            } else {
                break;
            }
        }
        listFlight.showListFlight(list2);
        findFlight = list2.get(0);

        System.out.println("                   Enter your infomation                         ");
        //Tạo vé Flight mỗi lần booking orderCode++ -->R*
        String orderCode = "R" + (listReservation.size() + 1);
        String idPasssenger;
        boolean isExited;
        while (true) {
            isExited = false;
            idPasssenger = Validator.getString("Enter ID:", " ID can not be blank",
                    "^(?!\\s*$).+");
            for (int i = 0; i < listReservation.size(); i++) {
                if (listReservation.get(i).getSelectedFlight().equals(findFlight)
                        && listReservation.get(i).getTraveller().getID().equalsIgnoreCase(idPasssenger)) {
                    isExited = true;
                    System.out.println("This ID is exited in this Flight!");
                }
            }
            if (!isExited) {
                break;
            }
        }
        String namePasssenger = Validator.getString("Enter name:", " Name can not be blank",
                "^(?!\\s*$).+");
        Date birthDay;
        while (true) {
            birthDay = Validator.getDate("Enter birth day: ",
                    "birth day must before (" + dateFormat.format(currentDate) + ")",
                    "Invalid! Format dd/mm/yyyy", "dd/MM/yyyy",
                    minDate, currentDate);
            if (birthDay != null) {
                break;
            } else {
                System.err.println("Time can not Empty!");
            }
        }
        // Create new passenger
        Passenger passenger = new Passenger(idPasssenger, namePasssenger, birthDay);
        //Get list seat in reservation before
        int[] oldSelectedSeats = null;
        for (int i = 0; i < listReservation.size(); i++) {
            if (listReservation.get(i).getSelectedFlight().getNumber().equals(findFlight.getNumber())) {
                oldSelectedSeats = listReservation.get(i).getSelectedSeats();
                break;
            }
        }
        reservation = new Reservation(orderCode, findFlight, passenger, "", Constant.NOT_CHECK_IN, oldSelectedSeats);
        listReservation.add(reservation);
        System.err.println("Reservation successful. Your booking code is " + orderCode);
    }

    public Reservation getReservation_NOT_CHECK_IN(String orderID) {
        for (int i = 0; i < listReservation.size(); i++) {
            if (listReservation.get(i).getID().equalsIgnoreCase(orderID) && listReservation.get(i).getStatus() == Constant.NOT_CHECK_IN) {
                return listReservation.get(i);
            }
        }
        return null;
    }

    public void check_in() {
        int choiceSeat;
        System.out.println("\tCheck-in process and seat allocation for passengers");
        if (listReservation.isEmpty()) {
            System.err.println("list Reservation is empty !!");
            return;
        }

        //Nhập mã R* id đã đặt vé check in
        String orderCode = Validator.getString("Enter Order Code Flight [R*] :",
                "Invalid ! Code must R_ form", "^[Rr]\\d+$");

        // Tìm đặt chỗ dựa trên mã đặt chỗ
        Reservation newOrderFlight = getReservation_NOT_CHECK_IN(orderCode);
        if (newOrderFlight == null) {
            System.err.println("Not available code!");
            return;
        } else {
            for (int i = 0; i < listReservation.size(); i++) {
                if (listReservation.get(i).equals(newOrderFlight)) {
                    if (listReservation.get(i).getNotSelectedSeats() == null) {
                        System.out.println("All seats have been booked ! "
                                + "Cannot perform check-in !");
                        return;
                    } else {
                        int lenNotSelectedSeats = listReservation.get(i).getNotSelectedSeats().length;
                        int[] NotSelectedSeats = listReservation.get(i).getNotSelectedSeats();
                        listReservation.get(i).showListSeat(NotSelectedSeats);
                        System.out.println("");
                        while (true) {
                            choiceSeat = Validator.getInt("Enter Seat: ", "Must be >0", "Invalid!", 0, Integer.MAX_VALUE);
                            boolean isExistedChoiceSeat = false;
                            //Find choiceSeat in not selected seat
                            for (int j = 0; j < lenNotSelectedSeats; j++) {
                                if (choiceSeat == NotSelectedSeats[j]) {
                                    isExistedChoiceSeat = true;
                                    //Create new list Seat
                                    int sizeSelectedSeats;
                                    int[] newlistSeat;
                                    if (listReservation.get(i).getSelectedSeats() == null) {
                                        newlistSeat = new int[1];
                                        newlistSeat[0] = choiceSeat;
                                    } else {
                                        sizeSelectedSeats = listReservation.get(i).getSelectedSeats().length;
                                        newlistSeat = new int[sizeSelectedSeats + 1];
                                        for (int k = 0; k < sizeSelectedSeats; k++) {
                                            newlistSeat[k] = listReservation.get(i).getSelectedSeats()[k];
                                        }
                                        //Add new Seat in last position
                                        newlistSeat[sizeSelectedSeats] = choiceSeat;
                                    }
                                    //Update new list seat
                                    for (int k = 0; k < listReservation.size(); k++) {
                                        if (listReservation.get(k).getSelectedFlight().getNumber().equals(listReservation.get(i).getSelectedFlight().getNumber())) {
                                            listReservation.get(k).setSelectedSeats(newlistSeat);
                                        }
                                    }
                                    break;
                                }
                            }
                            if (isExistedChoiceSeat == true) {
                                break;
                            } else {
                                System.out.println("Not found seat!");
                            }
                        }
                    }
                    listReservation.get(i).setStatus(Constant.CHECK_IN);
                    listReservation.get(i).setGate(listReservation.get(i).getSelectedFlight().getGate());
                    // Tạo thẻ lên máy bay với thông tin của hành khách và ghế
                    System.out.println("\tInfomation of Ticket booking in Flight:");
                    System.out.println("\tPassenger Name: " + listReservation.get(i).getTraveller().getName());
                    System.out.println("\tFlight Code: " + listReservation.get(i).getSelectedFlight().getNumber());
                    System.out.println("\tGate: " + listReservation.get(i).getGate());
                    System.out.println("\tSeat Number: " + choiceSeat);
                    System.out.println("\nCheck-in success !");
                }
            }
        }
    }

    public void saveToFile(String file) {
        try {
            ArrayList<Flight> listFlight;
            FileInputStream fosx = new FileInputStream(file);
            ObjectInputStream oosx = new ObjectInputStream(fosx);
            listFlight = (ArrayList<Flight>) oosx.readObject();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listFlight);
            oos.writeObject(listReservation);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.err.println("Error saving data to file: " + file);
        }
    }

    public void importFromFile(String file) {
        try {
            System.out.println("Loading file " + file + " to schedule list...");
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            // Đọc danh sách từ tệp
            oos.readObject();
            listReservation = (ArrayList<Reservation>) oos.readObject();
            oos.close();
            fos.close();
        } catch (Exception i) {
            System.out.println(file + " empty to Reservation");
            return;
        }
    }
}
