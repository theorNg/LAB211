/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import constant.Constant;
import controller.FlightList;
import controller.ReservationList;
import controller.ScheduleList;
import controller.Validator;
import model.Flight;

/**
 *
 * @author win
 */
public class Main {

    public static void main(String[] args) {
        FlightList flights = new FlightList();
        ReservationList reservations = new ReservationList();
        ScheduleList schedules = new ScheduleList();
        flights.importFromFile(Constant.FILE);
        reservations.importFromFile(Constant.FILE);
        schedules.importFromFile(Constant.FILE);
        while (true) {
            while (true) {
                System.out.println("===========================================");
                System.out.println("Flight Management System Menu:");
                System.out.println("1. Flight schedule management");
                System.out.println("2. Passenger reservation and booking");
                System.out.println("3. Passenger check-in and seat allocation");
                System.out.println("4. Crew management and assignments");
                System.out.println("5. Print all lists Flight from File");
                System.out.println("6. Data storage for flight details, reservations, and assignments");
                System.out.println("7. Quit");
                System.out.println("===========================================");

                int choice = Validator.getInt("Your choice:", "Choice from 1 to 7!",
                        "Invalid! Please enter form 1 --> 7", 1, 7);
                switch (choice) {
                    case 1:
                        // Add new Flight
                        // Prompt user for Flight information and call addFlight method
                        flights.addFlight();
                        break;
                    case 2:
                        // Ask for booking a ticket for a Flight
                        // Prompt user for ID and call reservation_and_booking method
                        reservations.addReservation(flights);
                        break;
                    case 3:
                        // Check in ID code Flight and ask to choice Seat svailable
                        // Prompt user for ID and updated vehicle information, then call updateVehicle method
                        reservations.check_in();
                        break;
                    case 4:
                        // Create Crew management and assignments
                        // Prompt call management_assignments method
                        while(true){
                            System.out.println("1.Show all in list schedule");
                        System.out.println("2.Add schedule in flight");
                        System.out.println("3.Remove schedule in flight");
                        System.out.println("4.Update flight(Departure Time,duration, Gate)");
                        System.out.println("5.Back");
                        int choice2 = Validator.getInt("Your choice:", "Please enter form 1 --> 5",
                                "Invalid!", 1, 5);
                        if(choice2==5){
                            break;
                        }
                        switch (choice2) {
                            case 1:
                                schedules.showALLSchedule();
                                break;
                            case 2:
                                schedules.addSchedule(flights.getListFlight());
                                break;
                            case 3:
                                schedules.deleSchedule(flights.getListFlight());
                                break;
                            case 4:
                                flights.upDateFlight();
                                break;
                        }
                        }
                        break;
                    case 5:
                        // print all Flight info desc by Date 
                        // Prompt user to choose between searching by name or ID
                        // Depending on choice, prompt for search criteria and call the appropriate search method
                        flights.importFromFile(Constant.FILE);
                        flights.sortAndShowListFlight(flights.getListFlight());
                        break;
                    case 6:
                        // Save Date to File
                        // Call saveAllFIle method
                        System.out.println("Store data to files.");
                        flights.saveToFile(Constant.FILE);
                        reservations.saveToFile(Constant.FILE);
                        schedules.saveToFile(Constant.FILE);
                        System.err.println("Save hava done!");
                        break;
                    case 7:
                        // End run out of Flight Management
                        return;
                }
            }
        }
    }
}
