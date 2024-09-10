/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.DistanceTraveled;
import entity.FuelEconomy;
import entity.RevisedFuelEconomy;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(""
                + "1-Calculating Fuel Economy\n"
                + "2-Calculating Distance Traveled\n"
                + "3-Revised Fuel Economy Calculation");
        while (true) {
            int choice = Validator.getInt("Choice feature: ", "Just be 1->3", "Invalid!", 1, 3);
            switch (choice) {
                case 1:
                    FuelEconomy fuelEconomy = new FuelEconomy();
                    fuelEconomy.input();
                    System.out.printf("Your car averaged %.2f miles per gallon.\n", fuelEconomy.calculateFuelEconomy());
                    break;
                case 2:
                    DistanceTraveled distanceTraveled = new DistanceTraveled();
                    distanceTraveled.input();
                    System.out.printf("Your car traveled %.2f miles\n", distanceTraveled.calculateDistance());
                    break;
                case 3:

                    RevisedFuelEconomy revisedFuelEconomy = new RevisedFuelEconomy();
                    revisedFuelEconomy.input();
                    System.out.printf("Your car averaged %.2f miles per gallon\n", revisedFuelEconomy.calculateRevisedFuelEconomy());
                    break;
            }
        }
    }

}
