/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Manage_Vehicle;
import controller.Validator;

/**
 *
 * @author ADMIN
 */
public class Vehicle_Management {

    public static void main(String[] args) {
        Manage_Vehicle myVehicle = new Manage_Vehicle();
        myVehicle.readFile("vehicle.dat");
        while (true) {
            while (true) {
                System.out.println("===========================================");
                System.out.println("\tVehicle Management System Menu:");
                System.out.println("(1) Add new vehicle");
                System.out.println("(2) Check exits Vehicle");
                System.out.println("(3) Update vehicle.");
                System.out.println("(4) Delete vehicle.");
                System.out.println("(5) Search vehicle");
                System.out.println("(6) Display all Vehicle");
                System.out.println("(7) Save all vehicles to file");
                System.out.println("(8) Print all vehicle from the file.");
                System.out.println("(9) Quit");
                System.out.println("===========================================");

                int choice = Validator.getInt("Your choice:", "Choice from 1 to 9!",
                        "Invalid enter!", 1, 9);
                switch (choice) {
                    case 1:
                        // Add new vehicle
                        // Prompt user for vehicle information and call addVehicle method
                        myVehicle.addNewVehicle();
                        break;
                    case 2:
                        // Check existing vehicle
                        // Prompt user for ID and call checkExistVehicle method
                        myVehicle.checkExits();
                        break;
                    case 3:
                        // Update vehicle
                        // Prompt user for ID and updated vehicle information, then call updateVehicle method
                        myVehicle.updateVehicle();

                        break;
                    case 4:
                        // Delete vehicle
                        // Prompt user for ID and call deleteVehicle method
                        myVehicle.deleteVehicle();
                        break;
                    case 5:
                        // Search vehicle
                        // Prompt user to choose between searching by name or ID
                        // Depending on choice, prompt for search criteria and call the appropriate search method
                        myVehicle.searchVehicle();
                        break;
                    case 6:
                        // Display all vehicles
                        // Call displayAllVehicles method
                        myVehicle.displayAllVehicles();
                        break;
                    case 7:
                        // Save data to file
                        // Prompt user for the filename and call saveDataToFile method
                        myVehicle.saveAllVehiclesToFile("vehicle.dat");
                        System.out.println("Save have done");
                        break;
                    case 8:
                        // Print vehicle list
                        // Call printAllVehicles method
                        myVehicle.printAllVehiclesFromFile();
                        break;
                    case 9:
                        return;
                }
            }
        }
    }

}
