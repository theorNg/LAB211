/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import utils.Validator;

/**
 *
 * @author win
 */
public class RevisedFuelEconomy {

    private DistanceTraveled distanceTraveled;
    private double gasoline;

    public RevisedFuelEconomy(DistanceTraveled distanceTraveled, double gasoline) {
        this.distanceTraveled = distanceTraveled;
        this.gasoline = gasoline;
    }

    public RevisedFuelEconomy() {
    }

    public double calculateRevisedFuelEconomy() {
        double milesTraveled = distanceTraveled.calculateDistance();
        return milesTraveled / gasoline;
    }

    public DistanceTraveled getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(DistanceTraveled distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public double getGasoline() {
        return gasoline;
    }

    public void setGasoline(double gasoline) {
        this.gasoline = gasoline;
    }

    public void input() {
        // Input and Calculate Revised Fuel Economy
        double radius = Validator.getDouble("What is the radius of your tires, in inches? ",
                "Must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        int revolutions = Validator.getInt("How many revolutions did your car's tires make? ",
                "Must be > 0", "Invalid!", 1, Integer.MAX_VALUE);
        distanceTraveled= new DistanceTraveled(radius, revolutions);
        gasoline = Validator.getDouble("How many gallons of gas did your car use? ",
                "Must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
    }
}
