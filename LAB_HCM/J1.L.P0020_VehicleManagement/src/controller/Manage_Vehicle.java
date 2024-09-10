/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Vehicle;

/**
 *
 * @author ADMIN
 */
public class Manage_Vehicle implements Menu {

    List<Vehicle> vehiclesList = new ArrayList<>();
    List<Vehicle> vehiclesList2 = new ArrayList<>();

    public Vehicle checkVehicle(String id) {
        for (int i = 0; i < vehiclesList.size(); i++) {
            if (vehiclesList.get(i).getId().equalsIgnoreCase(id)) {
                return vehiclesList.get(i);
            }
        }
        return null;
    }

    /**
     * Add new Vehicle to File
     */
    @Override
    public void addNewVehicle() {
        String choose;
        String id;
        Date currentDate = new Date();
        while (true) {
            do {
                id = Validator.getString("Enter product Id:", "Can not empty! ", "^(?!\\s*$).+");
                if (checkVehicle(id) != null) {
                    System.err.println("ID products is areadly input !");
                } else {
                    break;
                }
            } while (true);

            String vehicleName = Validator.getString("Enter Vehicle name:",
                    "Can not empty!", "^(?!\\s*$).+");
            String color = Validator.getString("Enter color vehicle:", "Can not empty!", "^(?!\\s*$).+");
            double price = Validator.getDouble("Enter Price Vehicle:", "Just be >0",
                    "Invalid", Double.MIN_VALUE, Double.MAX_VALUE);
            String brand = Validator.getString("Enter brand: ", "Can not empty!", "^(?!\\s*$).+");
            String type = Validator.getString("Enter Vehicle type: ", "Can not empty!", "^(?!\\s*$).+");
            Calendar canlendar = Calendar.getInstance();
            canlendar.setTime(currentDate);
            //lay ra nam  hien tai
            int year = canlendar.get(Calendar.YEAR);
            int year1 = Validator.getInt("Enter Year:", "Invalid! 2000<=Year<=" + year, "Invalid", 2000, year);
            Vehicle newVehicle = new Vehicle(id, vehicleName, color, price, brand, type, year1);
            vehiclesList.add(newVehicle);
            System.out.println("Add success ! Continue input Products");
            System.out.println("----------CONTINUE ADD-----------");
            choose = Validator.getString("You want to continue (Y/N): ", "Just Y or N", "[YNyn]");
            if (choose.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    /**
     *
     */
    @Override
    public void checkExits() {
        if (vehiclesList.isEmpty()) {
            System.err.println("The list Vehicle is emty");
            return;
        }
        String checkID = Validator.getString("Enter Vehicle Id:", "Wrong fomat Id! ", "^(?!\\s*$).+");

        while (true) {
            if (checkVehicle(checkID) == null) {
                System.err.println("No Vehicle Found!");
                return;
            } else {
                List<Vehicle> listVehicle = new ArrayList<>();
                listVehicle.add(checkVehicle(checkID));
                showListVehicle(listVehicle);
                System.out.println("Existed Vehicle!");
                return;
            }
        }
    }

    public void showListVehicle(List<Vehicle> list) {
        if (list.isEmpty()) {
            System.err.println("The list is emty");
            return;
        }
        System.out.println("                               Vehicle Management                          ");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "ID_Vehicle", "Name_Vehicle", "Color_Vehicle",
                "Price_Vehicle", "Brand_Vehicle", "Type", "ProductYear");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    @Override
    public void updateVehicle() {
        Scanner scanner = new Scanner(System.in);
        if (vehiclesList.isEmpty()) {
            System.err.println("You have not enter Information of Products");
            return;
        } else {
            String id = Validator.getString("Enter Id to update:", "ID vehicle is unavailable!", "^(?!\\s*$).+");
            Vehicle productUpdate = checkVehicle(id);
            if (productUpdate == null) {
                System.err.println("Vehicle does not exist");
                return;
            } else {
                List<Vehicle> oldProduct = new ArrayList<>();
                oldProduct.add(checkVehicle(id));
                showListVehicle(oldProduct);
                for (int i = 0; i < vehiclesList.size(); i++) {
                    if (vehiclesList.get(i).getId().equalsIgnoreCase(productUpdate.getId())) {
                        System.out.print("Enter NEW product name(Press blank(Enter) now to keep the old information):");
                        String newName = scanner.nextLine().trim();
                        System.out.print("Enter NEW color(Press blank(Enter) now to keep the old information):");
                        String newColor = scanner.nextLine().trim();
                        String newPrice = Validator.getString(
                                "Enter NEW quanlity(Press blank(Enter) now to keep the old information):",
                                "Please enter price and must be >0",
                                "^([1-9][0-9]*)?");
                        System.out.print("Enter NEW brand(Press blank(Enter) now to keep the old information):");
                        String newBrand = scanner.nextLine().trim();
                        System.out.print("Enter NEW type(Press blank(Enter) now to keep the old information):");
                        String newType = scanner.nextLine().trim();
                        String newYear = Validator.getString(
                                "Enter NEW Year(Press blank(Enter) now to keep the old information):",
                                "Please enter interger and must be >=2000 and <= 2024",
                                "^(2000|20[01][0-9]|202[0-4]|)$");

                        if (!newName.isEmpty()) {
                            vehiclesList.get(i).setName(newName);
                        }
                        if (!newColor.isEmpty()) {
                            vehiclesList.get(i).setColor(newColor);
                        }
                        if (!newPrice.isEmpty()) {
                            vehiclesList.get(i).setPrice(Integer.parseInt(newPrice));
                        }
                        if (!newBrand.isEmpty()) {
                            vehiclesList.get(i).setBrand(newBrand);
                        }
                        if (!newType.isEmpty()) {
                            vehiclesList.get(i).setType(newType);
                        }
                        if (!newYear.isEmpty()) {
                            vehiclesList.get(i).setProductYear(Integer.parseInt(newYear));
                        }
                        List<Vehicle> newProduct = new ArrayList<>();
                        newProduct.add(vehiclesList.get(i));
                        showListVehicle(newProduct);
                        System.out.println("Update Vehicle success !");
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void deleteVehicle() {
        if (vehiclesList.isEmpty()) {
            System.err.println("You have not enter Information of Vehicle");
            return;
        } else {
            String id = Validator.getString("Enter Id to delete:", "ID products is unavailable!", "^(?!\\s*$).+");
            if (checkVehicle(id) == null) {
                System.err.println("Vehicle does not exist");
                return;
            } else {
                List<Vehicle> oldProduct = new ArrayList<>();
                oldProduct.add(checkVehicle(id));
                showListVehicle(oldProduct);
                for (int i = 0; i < vehiclesList.size(); i++) {
                    if (vehiclesList.get(i).getId().equalsIgnoreCase(id)) {

                        String choice = Validator.getString("Are you sure delete?(Y or N)", "Just Y or N", "[YNyn]");
                        if (choice.equalsIgnoreCase("N")) {
                            System.err.println("DELETE FAIL!");
                            return;
                        } else {
                            vehiclesList.remove(i);
                        }

                    }
                }
                System.out.println("Product have been removed !");
                return;
            }
        }
    }

    @Override
    public void searchVehicle() {
        if (vehiclesList.isEmpty()) {
            System.out.println("List is emty");
            return;
        }
        do {
            System.out.println("1. Search by ID_Vehicle");
            System.out.println("2. Search by Name_Vehicle");
            System.out.println("3. Exit");
            int choice = Validator.getInt("Enter choice:",
                    "Invalid!Choose 1 to 3", "Enter a digit 1 to 3", 1, 3);
            switch (choice) {
                case 1:
                    String id = Validator.getString("Enter code to search:",
                            "Code can not to blank", "^(?!\\s*$).+");

                    if (checkVehicle(id) == null) {
                        System.out.println("Id Vehicle can not found");
                        break;
                    } else {
                        for (int i = 0; i < vehiclesList.size(); i++) {
                            if (vehiclesList.get(i).getId().equalsIgnoreCase(id)) {
                                List<Vehicle> oldVehicle = new ArrayList<>();
                                oldVehicle.add(vehiclesList.get(i));
                                showListVehicle(oldVehicle);
                            }
                        }
                    }
                    break;
                case 2:
                    List<Vehicle> listVehicleDecs = new ArrayList<>();
                    String vehicleNameChar = Validator.getString("Enter Vehicle name:",
                            "Invalid!", "^(?!\\s*$).+");
                    for (int i = 0; i < vehiclesList.size(); i++) {
                        if (vehiclesList.get(i).getName().contains(vehicleNameChar)) {
                            listVehicleDecs.add(vehiclesList.get(i));
                        }
                    }

                    Collections.sort(listVehicleDecs,
                            (vehicle1, vehicle2) -> vehicle2.getName().compareTo(vehicle1.getName()));
                    showListVehicle(listVehicleDecs);
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    @Override
    public void displayAllVehicles() {
        if (vehiclesList.isEmpty()) {
            System.err.println("You have not enter Information of Vehicle");
            return;
        }
        do {
            System.out.println("1. Display all");
            System.out.println("2. Display by price");
            System.out.println("3. Exit");
            int choice = Validator.getInt("Enter choice:",
                    "Invalid!Choose 1 to 3", "Enter a digit 1 to 3", 1, 3);
            switch (choice) {
                case 1:
                    showListVehicle(vehiclesList);
                    break;
                case 2:
                    List<Vehicle> listVehicleDecs = new ArrayList<>();
                    int price = Validator.getInt("Enter Price Vehicle::", "Price can not be blank!",
                            "Invalid", 0, Integer.MAX_VALUE);
                    for (int i = 0; i < vehiclesList.size(); i++) {
                        if (vehiclesList.get(i).getPrice() <= price) {
                            listVehicleDecs.add(vehiclesList.get(i));
                        }
                    }
                    Collections.sort(listVehicleDecs,
                            (vehicle1, vehicle2) -> Double.compare(vehicle2.getPrice(), vehicle1.getPrice()));
                    showListVehicle(listVehicleDecs);
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    @Override
    public void saveAllVehiclesToFile(String file) {
        // Save product data
        if (vehiclesList.isEmpty()) {
            System.err.println("Empty Vehicle list");
            return;
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (int i = 0; i < vehiclesList.size(); i++) {
                    oos.writeObject(vehiclesList.get(i));
                }
                oos.close();
                fos.close();
            } catch (IOException e) {
                System.err.println("Error saving product data to file: " + file);
            }
        }
    }

    public void readFile(String file) {
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    // Đọc một đối tượng và ép kiểu thành Product
                    Vehicle vehicle = (Vehicle) oos.readObject();
                    vehiclesList.add(vehicle);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception i) {
            System.out.println("Error to read File:" + file);
        }
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    // Đọc một đối tượng và ép kiểu thành Product
                    Vehicle vehicle = (Vehicle) oos.readObject();
                    vehiclesList2.add(vehicle);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception i) {
            System.out.println("Error to read File:" + file);
        }
    }

    @Override
    public void printAllVehiclesFromFile() {
        if (vehiclesList2.isEmpty()) {
            System.err.println("Empty Vehicle list");
            return;
        } else {
            do {
                System.out.println("1. Print all");
                System.out.println("2. Print by Year");
                System.out.println("3. Exit");
                int choice = Validator.getInt("Enter choice:",
                        "Invalid!Choose 1 to 3", "Enter a digit 1 to 3", 1, 3);
                switch (choice) {
                    case 1:
//                        for (int i = 0; i < vehiclesList2.size(); i++) {
//                            List<Vehicle> listVehicle = new ArrayList<>();
//                            listVehicle.add(vehiclesList2.get(i));
//                            showListVehicle(listVehicle);
//                        }
                        showListVehicle(vehiclesList2);
                        break;
                    case 2:
                        List<Vehicle> listVehicleDecs = new ArrayList<>();
                        int year = Validator.getInt("Enter Year Vehicle:", "Year can not be blank!Year >=2000",
                                "Invalid", 2000, Integer.MAX_VALUE);
                        for (int i = 0; i < vehiclesList2.size(); i++) {
                            if (vehiclesList2.get(i).getProductYear() >= year) {
                                listVehicleDecs.add(vehiclesList2.get(i));
                            }
                        }
                        Collections.sort(listVehicleDecs,
                                (vehicle1, vehicle2) -> Integer.compare(vehicle2.getProductYear(), vehicle1.getProductYear()));
                        showListVehicle(listVehicleDecs);
                        break;
                    case 3:
                        return;
                }
            } while (true);
        }
    }

}
