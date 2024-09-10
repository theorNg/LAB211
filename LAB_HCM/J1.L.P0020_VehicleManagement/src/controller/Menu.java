/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author ADMIN
 */
public interface Menu {

    void addNewVehicle();

    void checkExits();

    void updateVehicle();

    void deleteVehicle();

    void searchVehicle();

    void displayAllVehicles();

    void saveAllVehiclesToFile(String file);

    void printAllVehiclesFromFile();
}
