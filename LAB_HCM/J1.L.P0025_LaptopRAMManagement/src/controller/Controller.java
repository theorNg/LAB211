/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.RAMManagementSystem;
import entity.RAMItem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Validator;

/**
 *
 * @author win
 */
public class Controller {

    private RAMManagementSystem ramManager;

    public Controller() {
        ramManager = new RAMManagementSystem();
    }

    public RAMItem addRamItem() throws Exception {
        String type = Validator.getString("Enter type: ", "Must be alphabetic and digit!", "[a-zA-Z0-9]+");
        int number = Validator.getInt("Enter bus number(MHz): ", "Must be > 0", "Please enter integer!", 1, Integer.MAX_VALUE);
        String bus = number + "MHz";
        String brand = Validator.getString("Enter brand: ", "Can not empty!", "^(?!\\s*$).+");
        int quantity = Validator.getInt("Enter quantity: ", "Must be >0", "Please enter integer!", 1, Integer.MAX_VALUE);
        Date production_month_year = Validator.getDate("Enter production date: ", "Must be dd/MM/yyyy", "dd/MM/yyyy");
        boolean active = true;
        RAMItem item = new RAMItem(null, type, bus, brand, quantity, production_month_year, active);
        if (ramManager.addItem(item)) {
            return item;
        }
        throw new Exception("Add Fail!");
    }

    public void search(int mode) {
        List<RAMItem> resultSearch;
        switch (mode) {
            case 1://search by type
                String type = Validator.getString("Enter type: ", "Must be alphabetic and digit!", "[a-zA-Z0-9]+");
                resultSearch = ramManager.searchByType(type);
                if (resultSearch.isEmpty()) {
                    System.out.println("Can not found!");
                } else {
                    displaySearchByType(resultSearch);
                }
                break;
            case 2://search by bus
                int number = Validator.getInt("Enter bus number(MHz): ", "Must be > 0", "Please enter integer!", 1, Integer.MAX_VALUE);
                String bus = number + "";
                resultSearch = ramManager.searchByBus(bus);
                if (resultSearch.isEmpty()) {
                    System.out.println("Can not found!");
                } else {
                    displaySearchByBus(resultSearch);
                }
                break;
            case 3://search by brand
                String brand = Validator.getString("Enter brand: ", "Can not empty!", "^(?!\\s*$).+");
                resultSearch = ramManager.searchByBrand(brand);
                if (resultSearch.isEmpty()) {
                    System.out.println("Can not found!");
                } else {
                    displaySearchByBrand(resultSearch);
                }
                break;
        }
    }

    public void displaySearchByType(List<RAMItem> list) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("|%15s|%10s|%25s|%10s|\n", "Code", "Type", "Production_month_year", "quantity");
        for (RAMItem item : list) {
            System.out.printf("|%15s|%10s|%25s|%10s|\n", item.getCode(), item.getType(),
                    formatDate.format(item.getProduction_month_year()), item.getQuantity());
        }
    }

    public void displaySearchByBus(List<RAMItem> list) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("|%15s|%10s|%25s|%10s|\n", "Code", "Bus", "Production_month_year", "quantity");
        for (RAMItem item : list) {
            System.out.printf("|%15s|%10s|%25s|%10s|\n", item.getCode(), item.getBus(),
                    formatDate.format(item.getProduction_month_year()), item.getQuantity());
        }
    }

    public void displaySearchByBrand(List<RAMItem> list) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("|%15s|%10s|%25s|%10s|\n", "Code", "Brand", "Production_month_year", "quantity");
        for (RAMItem item : list) {
            System.out.printf("|%15s|%10s|%25s|%10s|\n", item.getCode(), item.getBrand(),
                    formatDate.format(item.getProduction_month_year()), item.getQuantity());
        }
    }

    public RAMItem updateItem() throws Exception {
        String code = Validator.getString("Enter code you want Update: ", "Can not empty!", "^(?!\\s*$).+");
        RAMItem ramItem = ramManager.getItemByCode(code);
        if (ramItem == null) {
            throw new Exception("Can not found ram Item!");
        }
        System.out.println("Before Update: " + ramItem);
        String newType = Validator.getStringUpdateRegex("Enter new Type: ", "Must be alphabetic and digit!", "[a-zA-Z0-9]+");
        int newNumber = Validator.getIntUpdate("Enter new bus number(MHz): ", "Must be > 0", "Please enter integer!", 1, Integer.MAX_VALUE);
        String newBus = "";
        if (newNumber > 0) {
            newBus = newNumber + "MHz";
        }
        String newBrand = Validator.getStringUpdate("Enter new brand: ");
        int newQuantity = Validator.getIntUpdate("Enter new quantity: ", "Must be >0", "Please enter integer!", 1, Integer.MAX_VALUE);
        if (ramManager.updateItem(code, newType, newBus, newBrand, newQuantity)) {
            return ramItem;
        } else {
            throw new Exception("Update Fail");
        }
    }

    public RAMItem deleteItem() throws Exception {
        String code = Validator.getString("Enter code you want Update: ", "Can not empty!", "^(?!\\s*$).+");
        RAMItem ramItem = ramManager.deleteItem(code);
        if (ramItem == null) {
            throw new Exception("Can not found ram Item!");
        }
        return ramItem;
    }

    public void showAllItem() {
        RAMManagementSystem newRamManager = new RAMManagementSystem();
        //Tao mot ban sao danh sach de khi sap xep va in khong anh huong toi danh sach ban dau
        newRamManager.setList(new ArrayList<>(ramManager.getList()));
        newRamManager.sort(); //Sap xep danh sach truoc
        String result = newRamManager.toString();
        if (result == null) {
            System.out.println("The List is Empty");
        } else {
            System.out.println(result);
        }
    }

    public void saveFileRamItem() {
        try {
            ramManager.saveFile("RAMModules.dat");
            System.out.println("Save file success!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFileRamItem() {
        try {
            ramManager.readFile("RAMModules.dat");
            System.out.println("Load file success: RAMModules.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
